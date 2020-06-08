package com.cqmike.mock.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.HexUtil;
import com.cqmike.common.dto.Message;
import com.cqmike.common.dto.Mock;
import com.cqmike.common.dto.MockProductDTO;
import com.cqmike.common.platform.enums.AccessTypeEnum;
import com.cqmike.common.platform.enums.DataTypeEnum;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.core.result.ReturnForm;
import com.cqmike.core.util.JsonUtils;
import com.cqmike.mock.client.PlatformClient;
import com.cqmike.mock.netty.ChannelMap;
import com.cqmike.mock.netty.MockChannel;
import com.cqmike.mock.netty.NettyClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: iot
 * @ClassName: MockService
 * @Description: 模拟数据类
 * @Author: chen qi
 * @Date: 2020/4/16 18:45
 * @Version: 1.0
 **/
@Order(10000000)
@Component
public class MockService implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MockService.class);

    @Resource
    private MqttSender mqttSender;

    @Resource
    private PlatformClient platformClient;


    @Override
    public void run(String... args) throws Exception {
        aaa();
    }

    public void aaa() {
        ReturnForm<List<MockProductDTO>> returnForm = platformClient.findAllProductMockList();
        List<MockProductDTO> message = returnForm.getMessage();
        ChannelMap.initMockList(message);
    }

    @Scheduled(fixedRate = 10000, initialDelay = 3000)
    public void init() throws InterruptedException {
        ReturnForm<List<MockProductDTO>> returnForm = platformClient.findAllProductMockList();
        List<MockProductDTO> message = returnForm.getMessage();
        ChannelMap.initMockList(message);
        Set<String> keySet = ChannelMap.getChannelMap().keySet();
        for (MockProductDTO mockProductDTO : message) {
            List<DeviceForm> deviceFormList = mockProductDTO.getDeviceFormList();
            if (CollUtil.isEmpty(deviceFormList)) {
                continue;
            }
            for (DeviceForm deviceForm : deviceFormList) {
                if (!keySet.contains(deviceForm.getSn())) {
                    ChannelFuture future = NettyClient.bootstrap.connect("localhost", 8888).sync();
                    ByteBuf byteBuf = Unpooled.buffer();
                    byteBuf.writeBytes(deviceForm.getSn().getBytes());
                    byteBuf.writeBytes(Unpooled.copiedBuffer("\r\n".getBytes()));
                    future.channel().writeAndFlush(byteBuf);
                    ChannelMap.put(deviceForm.getSn(), new MockChannel(future.channel()));
                }
            }
        }
    }


    @Scheduled(fixedRate = 1000, initialDelay = 5000)
    public void polling() {
        List<MockProductDTO> mockProductDTOList = ChannelMap.getPropertyFormList();
        if (CollectionUtil.isEmpty(mockProductDTOList)) {
            return;
        }

        // 产品信息
        mockProductDTOList.forEach(p -> {
            ProductTypeEnum type = p.getType();
            Integer cycle = p.getCycle();
            long cycleLong = cycle * 1000;

            // 设备信息
            Map<String, String> childDeviceMapperMap = p.getChildDeviceMapperMap();
            List<DeviceForm> deviceFormList = p.getDeviceFormList();
            if (CollUtil.isEmpty(deviceFormList)) {
                return;
            }
            deviceFormList.forEach(deviceForm -> {
                String deviceFormSn = deviceForm.getSn();
                String sn = deviceFormSn;
                if (type.equals(ProductTypeEnum.CHILD_DEVICE)) {
                    sn = childDeviceMapperMap.get(deviceForm.getId());
                }
                MockChannel mockChannel = ChannelMap.get(sn);
                if (mockChannel == null) {
                    return;
                }
                long lastTime = mockChannel.getLastTime(deviceFormSn);
                long currentTimeMillis = System.currentTimeMillis();

                if (lastTime != 0) {
                    if (currentTimeMillis - lastTime < cycleLong) {
                        return;
                    }
                }

                AccessTypeEnum accessType = p.getAccessType();

                List<ProductPropertyForm> propertyFormList = p.getPropertyFormList();
                if (CollUtil.isEmpty(propertyFormList)) {
                    return;
                }

                // mqtt接入
                if (accessType == AccessTypeEnum.MQTT) {
                    Mock mock = new Mock();

                    mock.setDeviceSn(deviceFormSn);

                    propertyFormList.forEach(property -> {
                        DataTypeEnum dataTypeEnum = property.getType();
                        Object o = dataTypeEnum.mock(property);
                        String identifier = property.getIdentifier();
                        mock.put(identifier, o);
                    });
                    try {
                        // mqtt发送
                        Message message = new Message(mock);
                        mqttSender.sendToMqtt("testReceiveTopic/" + sn, JsonUtils.toJson(message));
                        log.info("mock-mqtt, 设备sn({}), 通道sn({})数据发送({})", deviceFormSn, sn, JsonUtils.toJson(message));
                    } catch (Exception e) {
                        log.error("mqtt发送消息异常, 设备sn为[{}]", sn, e);
                    }

                    // socket接入
                } else if (accessType == AccessTypeEnum.SOCKET) {

                    ByteBuf buf = Unpooled.buffer();
                    String snEncode = encodeSn(deviceFormSn);
                    buf.writeBytes(snEncode.getBytes());

                    propertyFormList.forEach(property -> {
                        DataTypeEnum dataTypeEnum = property.getType();
                        Object o = dataTypeEnum.mock(property);
                        writeData(buf, dataTypeEnum, o);
                    });

                    Channel channel = mockChannel.getChannel();
                    buf.writeBytes(Unpooled.copiedBuffer("\r\n".getBytes()));
                    byte[] bytes = new byte[buf.readableBytes()];
                    buf.getBytes(0, bytes);
                    log.info("mock-socket的channel({}), 设备sn({}), 通道sn({})数据发送({})", channel.id(), deviceFormSn, sn, Arrays.toString(bytes));
                    channel.writeAndFlush(buf);
                }

                mockChannel.setLastTime(deviceFormSn, System.currentTimeMillis());
            });
        });


    }

    private void writeData(ByteBuf buf, DataTypeEnum dataTypeEnum, Object value) {

        switch (dataTypeEnum) {
            case INTEGER: {
                buf.writeInt((Integer) value);
                break;
            }
            case LONG: {
                buf.writeLong((Long) value);
                break;
            }
            case DOUBLE: {
                buf.writeDouble((Double) value);
                break;
            }
            case FLOAT: {
                buf.writeFloat((Float) value);
                break;
            }
            case STRING: {
                buf.writeBytes(value.toString().getBytes(Charset.defaultCharset()));
                break;
            }
            default:
        }

    }


    // 20 个字符
    private String encodeSn(String deviceSn) {

        int length = deviceSn.length();
        StringBuilder stringBuilder = new StringBuilder();
        int flag = 20 - length;
        while (stringBuilder.length() < flag) {
            stringBuilder.append(" ");
        }

        return stringBuilder.append(deviceSn).toString();
    }

    public static void main(String[] args) {

        Integer a = 0x0a;
        Double d = 11D;
        System.out.println(d);
        ByteBuf byteBuf = Unpooled.buffer();
//        byteBuf.writeInt(a);
        byteBuf.writeDouble(d);
        byte[] ss = new byte[byteBuf.readableBytes()];
        byteBuf.getBytes(0, ss);
        System.out.println(Arrays.toString(ss));
//        byte[] s = new byte[4];
        byte[] s1 = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(s);
//        byteBuf.readBytes(s1);
        System.out.println(byteBuf.readDouble());
//        System.out.println(Arrays.toString(s));
//        System.out.println(HexUtil.encodeHexStr(s));
//        System.out.println(HexUtil.toBigInteger(HexUtil.encodeHexStr(s)));
        System.out.println("________________________________");
        System.out.println(Arrays.toString(s1));
        System.out.println(new String(s1));
        System.out.println(HexUtil.encodeHexStr(s1));
        System.out.println(HexUtil.toBigInteger(HexUtil.encodeHexStr(s1)));
//        HexUtil.encodeHexStr();
    }


}
