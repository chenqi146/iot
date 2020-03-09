package com.cqmike.front.netty;

import cn.hutool.script.ScriptUtil;
import com.cqmike.asset.form.front.DeviceFormForFront;
import com.cqmike.front.form.DeviceDataTimeForm;
import io.netty.channel.Channel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.script.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: iot
 * @ClassName: Connection
 * @Description: TODO
 * @Author: chen qi
 * @Date: 2020/3/7 15:56
 * @Version: 1.0
 **/
@Slf4j
@Data
public class Connection {

    private Channel channel;

    private String deviceSn;

    private DeviceFormForFront deviceFormForFront;

    private Map<String, DeviceDataTimeForm> dataTimeFormMap;

    private Map<String, CompiledScript> scriptMap;

    private static ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private static ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("js");
    /**
     *     todo 可能需要ThreadLocal  有待测试多线程
      */
    private static ScriptContext context = new SimpleScriptContext();


    public Connection(Channel channel, String deviceSn, DeviceFormForFront deviceFormForFront) throws ScriptException {
        this.channel = channel;
        this.deviceSn = deviceSn;
        this.setDeviceFormForFront(deviceFormForFront);
    }

    public void register(Connection conn) {
        conn.getChannel().attr(Const.CONNECTION).set(conn);
        DeviceChannelRel.addConnection(conn);
    }

    public ScriptContext getContext() {
        return context;
    }

    public ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    public void logOut() {
        DeviceChannelRel.removeConnection(this);
        this.getChannel().attr(Const.CONNECTION).set(null);
        this.channel.close();
    }

    public boolean containsConnection(String deviceSn) {
        return DeviceChannelRel.containsConnection(deviceSn);
    }

    /**
     *     todo 重写  设置的时候 生成时间  在此处编译js
      */
    public void setDeviceFormForFront(DeviceFormForFront deviceFormForFront) throws ScriptException {
        this.deviceFormForFront = deviceFormForFront;
        Map<String, String> analyseScriptMap = deviceFormForFront.getAnalyseScriptMap();
        if (CollectionUtils.isEmpty(analyseScriptMap)) {
            return;
        }

        scriptMap = new HashMap<>(analyseScriptMap.size());

        for (Map.Entry<String, String> entry : analyseScriptMap.entrySet()) {
            CompiledScript compile = ScriptUtil.compile(scriptEngine, entry.getValue());
            CompiledScript script = (CompiledScript) scriptEngine.eval(entry.getKey() + "(data)");
            scriptMap.put(entry.getKey(), script);
            compile.eval(context);
        }

    }
}
