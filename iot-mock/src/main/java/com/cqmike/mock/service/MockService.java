package com.cqmike.mock.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.HexUtil;
import com.cqmike.common.platform.enums.AccessTypeEnum;
import com.cqmike.common.platform.enums.DataTypeEnum;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.core.result.ReturnForm;
import com.cqmike.core.util.JsonUtils;
import com.cqmike.mock.client.PlatformClient;
import com.cqmike.common.dto.Mock;
import com.cqmike.common.dto.MockProductDTO;
import com.cqmike.mock.netty.ChannelMap;
import com.cqmike.mock.netty.MockChannel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @program: iot
 * @ClassName: MockService
 * @Description: 模拟数据类
 * @Author: chen qi
 * @Date: 2020/4/16 18:45
 * @Version: 1.0
 **/
@Component
public class MockService {

    private static final Logger log = LoggerFactory.getLogger(MockService.class);

    @Resource
    private MqttSender mqttSender;

    @Resource
    private PlatformClient platformClient;

    @PostConstruct
    public void init() {
        ReturnForm<List<MockProductDTO>> returnForm = platformClient.findAllProductMockList();
        List<MockProductDTO> message = returnForm.getMessage();
        ChannelMap.initMockList(message);
    }

    @Scheduled(fixedRate = 1000, fixedDelay = 5000)
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
            deviceFormList.forEach(deviceForm -> {
                String sn = deviceForm.getSn();
                MockChannel mockChannel = ChannelMap.get(sn);
                long lastTime = mockChannel.getLastTime();
                long currentTimeMillis = System.currentTimeMillis();

                if (lastTime != 0 && currentTimeMillis - lastTime < cycleLong) {
                    return;
                }

                AccessTypeEnum accessType = p.getAccessType();
                // mqtt接入
                if (accessType == AccessTypeEnum.MQTT) {
                    Mock mock = new Mock();
                    if (type.equals(ProductTypeEnum.CHILD_DEVICE)) {
                        String gatewaySn = childDeviceMapperMap.get(deviceForm.getId());
                        mock.setDeviceSn(gatewaySn);
                    } else {
                        mock.setDeviceSn(sn);
                    }

                    List<ProductPropertyForm> propertyFormList = p.getPropertyFormList();
                    propertyFormList.forEach(property -> {
                        DataTypeEnum dataTypeEnum = property.getType();
                        Object o = dataTypeEnum.mock(property);
                        String identifier = property.getIdentifier();
                        mock.put(identifier, o);
                    });
                    // mqtt发送
                    mqttSender.sendData("/mock/" + sn, mock);
                    log.info("mock-mqtt数据发送({})", JsonUtils.toJson(mock));

                    // socket接入
                } else if (accessType == AccessTypeEnum.SOCKET){

                    ByteBuf buf = Unpooled.buffer();
                    List<ProductPropertyForm> propertyFormList = p.getPropertyFormList();
                    propertyFormList.forEach(property -> {
                        DataTypeEnum dataTypeEnum = property.getType();
                        Object o = dataTypeEnum.mock(property);
                        writeData(buf, dataTypeEnum, o);
                    });

                    Channel channel = mockChannel.getChannel();
                    channel.writeAndFlush(buf);
                    log.info("mock-socket数据发送({})", JsonUtils.toJson(buf));

                }

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
        byte[] s1 = new byte[byteBuf.readableBytes() ];
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
