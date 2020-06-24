package com.cqmike.front.netty.decoder;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.cqmike.common.dto.AnalyseDataDTO;
import com.cqmike.common.front.form.DeviceFormForFront;
import com.cqmike.common.platform.enums.DataTypeEnum;
import com.cqmike.common.platform.enums.ProductTypeEnum;
import com.cqmike.common.platform.form.DeviceForm;
import com.cqmike.common.platform.form.ProductForm;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.core.util.JsonUtils;
import com.cqmike.front.map.CompiledScriptMap;
import com.cqmike.front.map.Connection;
import com.cqmike.front.netty.Const;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.script.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @program: iot
 * @ClassName: AnalyseDecoder
 * @Description: 解析处理类
 * @Author: chen qi
 * @Date: 2020/3/7 10:51
 * @Version: 1.0
 **/
public class AnalyseDecoder extends ByteToMessageDecoder {

    private static final Logger log = LoggerFactory.getLogger(AnalyseDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {

        try {
            byteBuf.retain();
            int readableBytes = byteBuf.readableBytes();
            Connection connection = ctx.channel().attr(Const.CONNECTION).get();
            if (readableBytes <= Const.DEVICE_DATA_MIN_LENGTH) {
                log.debug("ClientId为 ({}), 通道的deviceSn为 ({}) 设备数据报小于设备上报最小长度", ctx.channel().id(), connection.getDeviceSn());
                byteBuf.skipBytes(byteBuf.readableBytes());
                return;
            }

            // 这一段为打印数据展示  无其他用途
            byte[] bytes1 = new byte[readableBytes];
            byteBuf.getBytes(0, bytes1);
            String str = new String(bytes1);
            log.info("收到的数据为: [{}]", str);

            byte[] deviceSnBytes = new byte[Const.DEVICE_DATA_MIN_LENGTH];
            byteBuf.readBytes(deviceSnBytes);

            // 现在的sn是以20的长度来填充空格的  所以要去除空格
            String deviceSn = new String(deviceSnBytes);
            deviceSn = deviceSn.trim();
            DeviceFormForFront deviceFormForFront = connection.getDeviceFormForFront();
            ProductForm currentProductForm = deviceFormForFront.getCurrentProductForm();

            String productId;
            ProductTypeEnum productType = currentProductForm.getType();

            // 设备为网关
            if (productType == ProductTypeEnum.GATEWAY) {

                // 获取子设备
                Map<String, DeviceForm> childDeviceFormMap = deviceFormForFront.getChildDeviceFormMap();
                if (CollectionUtils.isEmpty(childDeviceFormMap)) {
                    byteBuf.skipBytes(byteBuf.readableBytes());
                    return;
                }
                DeviceForm form = childDeviceFormMap.get(deviceSn);
                if (form == null) {
                    log.debug("ClientId为 ({})的通道，deviceId为 ({})的设备没有对应的设备信息", ctx.channel().id(), deviceSn);
                    byteBuf.skipBytes(byteBuf.readableBytes());
                    return;
                }
                productId = form.getProductId();
                deviceSn = form.getSn();
                log.info("通道sn为({}), 设备sn为({})", connection.getDeviceSn(), deviceSn);

            } else {

                if (!deviceSn.equals(connection.getDeviceSn())) {
                    log.error("ClientId为 ({})的设备非网关, 且数据报deviceSn为({}), 通道deviceSn为({}), 两者不相等",ctx.channel().id(),
                            deviceSn, connection.getDeviceSn());
                    byteBuf.skipBytes(byteBuf.readableBytes());
                    return;
                }
                productId = currentProductForm.getId();

            }
            log.info("设备sn为: [{}]", deviceSn);

            // 执行解析脚本
            String result = scriptExecute(byteBuf, productId);
            if (StringUtils.isEmpty(result)) {
                byteBuf.skipBytes(byteBuf.readableBytes());
                return;
            }

            // 获取产品属性数据
            Map<String, List<ProductPropertyForm>> propertyMap = deviceFormForFront.getPropertyMap();
            if (CollectionUtils.isEmpty(propertyMap)) {
                byteBuf.skipBytes(byteBuf.readableBytes());
                return;
            }
            List<ProductPropertyForm> productProperties = propertyMap.get(productId);
            if (CollectionUtils.isEmpty(productProperties)) {
                byteBuf.skipBytes(byteBuf.readableBytes());
                return;
            }
            Map<String, Object> parse = JsonUtils.parse(result, new TypeReference<Map<String, Object>>(){});

            // 数据校验 清洗
            Map<String, Object> resultMap = getResultMapForVerify(deviceSn, parse, productProperties);
            if (CollectionUtil.isEmpty(resultMap)) {
                byteBuf.skipBytes(byteBuf.readableBytes());
                return;
            }

            // 构建传输对象到分发handle
            AnalyseDataDTO dto = new AnalyseDataDTO(deviceSn, productId, resultMap);
            list.add(dto);
        } catch (ScriptException e) {
            byteBuf.skipBytes(byteBuf.readableBytes());
            log.error("Netty分析Decoder异常: ", e);
        }

    }

    /**
     *  根据校验规则校验数据
     * @param deviceSn
     * @param result
     * @param productProperties
     * @return
     */
    public static Map<String, Object> getResultMapForVerify(String deviceSn, Map<String, Object> result,
                                                            List<ProductPropertyForm> productProperties) {
        int errorSum = 0;
        List<String> errorList = new ArrayList<>();

        Map<String, Object> resultMap = Maps.newHashMap();
        for (ProductPropertyForm productProperty : productProperties) {
            // 根据属性标识符找对应属性解析json
            String identifier = productProperty.getIdentifier();
            Object o = result.get(identifier);
            DataTypeEnum dataType = productProperty.getType();
            boolean verify = dataType.verify(productProperty, o);
            if (!verify) {
                errorSum++;
                errorList.add(identifier);
            }
            Class<?> aclass = dataType.getAclass();
            Object value = dataType.transformValue(aclass, o);
            resultMap.put(identifier, value);
        }
        if (errorSum > 0) {
            log.error("({})的设备数据校验失败({})", deviceSn, JsonUtils.toJson(errorList));
            return Collections.emptyMap();
        }
        return resultMap;
    }

    private String scriptExecute(ByteBuf buf, String productId) throws ScriptException {

        // 封装一个静态方法类提供给js调用
        // 获取已编译的js
        CompiledScript script = CompiledScriptMap.get(productId);
        Bindings bindings = CompiledScriptMap.getBindings();
        bindings.put("value", buf);
        String result = (String) script.eval(bindings);

        if (StringUtils.isEmpty(result)) {
            log.error("productId为({})的产品解析数据为空", productId);
            buf.skipBytes(buf.readableBytes());
            return null;
        }
        return result;
    }


    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        String scriptStr = "var ass = function(v) {\n" +
                " var MyClass = Java.type('com.cqmike.front.util.ByteBufUtil');" +
                "var result = MyClass.readInteger(v);  " +
                "var result1 = MyClass.readDouble(v);  " +
                "return result + '-' + result1;\n" +
                "};";
//        scriptEngine.eval(scriptStr);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(3);
        buf.writeDouble(3.14);
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("js");
        CompiledScript compiledScript = ((Compilable) scriptEngine).compile(scriptStr);
        CompiledScript script = ((Compilable) scriptEngine).compile("ass(v)");
        ScriptContext context = new SimpleScriptContext();
        compiledScript.eval(context);
        Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put("v", buf);

        TimeInterval timer = DateUtil.timer();
//        for (int i = 0; i < 1000000; i++) {

//            Invocable invocable = (Invocable) scriptEngine;
//            String analyse = (String) invocable.invokeFunction("analyse", "as");

            Object result;
            result = script.eval(context);
//            Map<String, Object> map = JsonUtils.parse(result, new TypeReference<Map<String, Object>>(){});
            System.out.println(result);
//        }

        System.out.println(timer.interval());


    }
}
