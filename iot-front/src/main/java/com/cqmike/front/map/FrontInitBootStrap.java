package com.cqmike.front.map;

import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.common.platform.form.ProductPropertyParserForm;
import com.cqmike.core.result.ReturnForm;
import com.cqmike.front.client.PlatformClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: iot
 * @ClassName: FrontInitBootStrap
 * @Description: 启动时初始化参数
 * @Author: chen qi
 * @Date: 2020/4/12 9:31
 * @Version: 1.0
 **/
@Component
public class FrontInitBootStrap implements ApplicationRunner {

    @Resource
    private PlatformClient platformClient;

    /**
     *  启动的时候初始化 规则和脚本
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ReturnForm<Map<String, Map<String, RuleFormForFront>>> ruleReturnForm = platformClient.findRuleFrontList();
        Map<String, Map<String, RuleFormForFront>> ruleFrontMap = ruleReturnForm.getMessage();
        RuleFormMap.init(ruleFrontMap);

        ReturnForm<List<ProductPropertyParserForm>> parserReturnForm = platformClient.listAll();
        List<ProductPropertyParserForm> parserForms = parserReturnForm.getMessage();
        Map<String, String> scriptMap = parserForms.stream().collect(Collectors
                .toMap(ProductPropertyParserForm::getProductId, ProductPropertyParserForm::getScript));
        CompiledScriptMap.init(scriptMap);

        ReturnForm<Map<String, Map<String, ProductPropertyForm>>> propertyList = platformClient.findPropertyList();
        Map<String, Map<String, ProductPropertyForm>> propertyMap = propertyList.getMessage();
        PropertyFormMap.init(propertyMap);
    }
}
