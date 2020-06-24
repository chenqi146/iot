package com.cqmike.front.map;

import cn.hutool.core.collection.CollectionUtil;
import com.cqmike.common.front.enums.OperateTypeEnum;
import com.cqmike.common.front.form.ParserFormForFront;
import com.cqmike.core.exception.BusinessException;

import javax.script.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: iot
 * @ClassName: CompiledScriptMap
 * @Description: 编译脚本Map
 * @Author: chen qi
 * @Date: 2020/3/10 20:22
 * @Version: 1.0
 **/
public class CompiledScriptMap {

    /**
     *  k -> 产品id
     */
    private static Map<String, CompiledScript> compiledScriptMap = null;

    private static ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private static ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("js");

    /**
     *     todo 可能需要ThreadLocal  有待测试多线程
     */
    private static ScriptContext context = new SimpleScriptContext();

    /**
     *  初始化
     * @param analyseScriptMap
     * @throws ScriptException
     */
    public static void init(Map<String, String> analyseScriptMap) throws ScriptException {

        compiledScriptMap = new HashMap<>(analyseScriptMap.size());

        for (Map.Entry<String, String> entry : analyseScriptMap.entrySet()) {
            compile(entry.getKey(), entry.getValue());
        }

    }

    /**
     *  预编译js脚本  根据产品id
     * @param productId
     * @param script
     * @throws ScriptException
     */
    private static void compile(String productId, String script) throws ScriptException {
        CompiledScript compile = ((Compilable) scriptEngine).compile(script);
        CompiledScript compiledScript = ((Compilable) scriptEngine).compile("dataScript" + productId + "dataScript(value)");
        compiledScriptMap.put(productId, compiledScript);
        compile.eval(context);
    }

    public static void removeCompiledScript(String productId) {
        compiledScriptMap.remove(productId);
    }

    private static void removeAll() {
        compiledScriptMap.clear();
    }

    public static CompiledScript get(String productId) {
        return compiledScriptMap.get(productId);
    }

    public static void put(String productId, String script) throws ScriptException {
        compile(productId, script);
    }

    /**  更新数据
     * @param parserForm  脚本类
     * @param operateType 操作类型
     */
    public static void update(ParserFormForFront parserForm, OperateTypeEnum operateType) throws ScriptException {

        if (CollectionUtil.isEmpty(compiledScriptMap)) {
            return;
        }

        switch (operateType) {
            case UPDATE:
            case CREATE:
                String script = parserForm.getScript();
                put(parserForm.getProductId(), script);
                break;
            case DELETE:
                removeCompiledScript(parserForm.getProductId());
                break;
            case DELETE_ALL:
                removeAll();
                break;
            default:
                throw new BusinessException("没有对应的枚举类型" + operateType);
        }
    }

    public static Bindings getBindings() {
        return context.getBindings(ScriptContext.ENGINE_SCOPE);
    }

    public static Map<String, CompiledScript> getScriptMap() {
        return compiledScriptMap;
    }

    public static ScriptContext getContext() {
        return context;
    }

    public static ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

}
