package com.cqmike.front.netty.decoder;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
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
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptException;
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

        byteBuf.retain();
        int readableBytes = byteBuf.readableBytes();
        Connection connection = ctx.channel().attr(Const.CONNECTION).get();
        if (readableBytes <= Const.DEVICE_DATA_MIN_LENGTH) {
            log.debug("ClientId为 ({}), deviceSn为 ({}) 设备数据报小于设备上报最小长度", ctx.channel().id(), connection.getDeviceSn());
            return;
        }

        byte[] deviceSnBytes = new byte[Const.DEVICE_DATA_MIN_LENGTH];
        byteBuf.readBytes(deviceSnBytes);

        byte[] dataBytes = new byte[readableBytes - Const.DEVICE_DATA_MIN_LENGTH];
        byteBuf.readBytes(dataBytes);

        String deviceSn = HexUtil.encodeHexStr(deviceSnBytes);
        DeviceFormForFront deviceFormForFront = connection.getDeviceFormForFront();
        ProductForm currentProductForm = deviceFormForFront.getCurrentProductForm();

        String productId;
        ProductTypeEnum productType = currentProductForm.getType();

        if (productType == ProductTypeEnum.GATEWAY) {

            Map<String, DeviceForm> childDeviceFormMap = deviceFormForFront.getChildDeviceFormMap();
            if (CollectionUtils.isEmpty(childDeviceFormMap)) {
                return;
            }
            DeviceForm form = childDeviceFormMap.get(deviceSn);
            if (form == null) {
                log.debug("ClientId为 ({})的通道，deviceId为 ({})的设备没有对应的设备信息", ctx.channel().id(), deviceSn);
                return;
            }
            productId = form.getProductId();

        } else {

            if (!deviceSn.equals(connection.getDeviceSn())) {
                log.error("ClientId为 ({})的设备非网关, 且数据报deviceSn为({}), 通道deviceSn为({}), 两者不相等",ctx.channel().id(),
                        deviceSn, connection.getDeviceSn());
                return;
            }
            productId = currentProductForm.getId();

        }

        String dataHexStr = HexUtil.encodeHexStr(dataBytes);
        String result = scriptExecute(dataHexStr, productId);
        if (StringUtils.isEmpty(result)) {
            return;
        }

        Map<String, List<ProductPropertyForm>> propertyMap = deviceFormForFront.getPropertyMap();
        if (CollectionUtils.isEmpty(propertyMap)) {
            return;
        }
        List<ProductPropertyForm> productProperties = propertyMap.get(productId);
        if (CollectionUtils.isEmpty(productProperties)) {
            return;
        }
        Map<String, Object> parse = JsonUtils.parse(result, new TypeReference<Map<String, Object>>(){});

        Map<String, Object> resultMap = getResultMapForVerify(deviceSn, parse, productProperties);
        if (CollectionUtil.isEmpty(resultMap)) {
            return;
        }

        AnalyseDataDTO dto = new AnalyseDataDTO(deviceSn, productId, resultMap);
        list.add(dto);

    }

    public static Map<String, Object> getResultMapForVerify(String deviceSn, Map<String, Object> result, List<ProductPropertyForm> productProperties) {
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

    private String scriptExecute(String dataHexStr, String productId) throws ScriptException {

        // 十六进制字符串
        boolean hexNumber = HexUtil.isHexNumber(dataHexStr);
        if (!hexNumber) {
            return null;
        }
        String[] dataHexStrArray = StrUtil.split(dataHexStr, 2);

        // 获取已编译的js
        CompiledScript script = CompiledScriptMap.get(productId);
        Bindings bindings = CompiledScriptMap.getBindings();
        bindings.put("data", dataHexStrArray);
        String result = (String) script.eval(bindings);

        if (StringUtils.isEmpty(result)) {
            log.error("productId为({})的产品解析数据为空", productId);
            return null;
        }
        return result;
    }


    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
//        String scriptStr = "var ass = function(v) {\n" +
//                "   return v + '啦啦啦我是卖报的小当家';\n" +
//                "};";
////        scriptEngine.eval(scriptStr);
//        CompiledScript compiledScript = ((Compilable) scriptEngine).compile(scriptStr);
//        CompiledScript script = ((Compilable) scriptEngine).compile("ass(v)");
//        ScriptContext context = new SimpleScriptContext();
//        compiledScript.eval(context);
//        TimeInterval timer = DateUtil.timer();
//
////        for (int i = 0; i < 5000000; i++) {
//
////            Invocable invocable = (Invocable) scriptEngine;
////            String analyse = (String) invocable.invokeFunction("analyse", "as");
//
//            Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
//            bindings.put("v", "sadsada");
//            String result;
//            result = ((String) script.eval(context));
//            System.out.println(result);
////        }
//
//        System.out.println(timer.interval());
//        System.out.println(timer.intervalSecond());


    }
}
