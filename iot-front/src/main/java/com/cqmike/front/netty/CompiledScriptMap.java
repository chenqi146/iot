package com.cqmike.front.netty;

import cn.hutool.script.ScriptUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
@Component
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

    @PostConstruct
    public void init() throws ScriptException {

        //todo 调接口
        Map<String, String> analyseScriptMap = new HashMap<>();
        compiledScriptMap = new HashMap<>(analyseScriptMap.size());

        for (Map.Entry<String, String> entry : analyseScriptMap.entrySet()) {
            compile(entry.getKey(), entry.getValue());
        }

    }

    private static void compile(String productId, String script) throws ScriptException {
        CompiledScript compile = ScriptUtil.compile(scriptEngine, script);
        CompiledScript compiledScript = (CompiledScript) scriptEngine.eval(productId + "(data)");
        compiledScriptMap.put(productId, compiledScript);
        compile.eval(context);
    }

    public static void removeCompiledScript(String productId) {
        compiledScriptMap.remove(productId);
    }

    public static CompiledScript get(String productId) {
        return compiledScriptMap.get(productId);
    }

    // todo 维护
    public static void put(String productId, String script) throws ScriptException {
        compile(productId, script);
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
