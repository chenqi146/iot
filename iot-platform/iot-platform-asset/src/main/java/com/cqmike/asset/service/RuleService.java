package com.cqmike.asset.service;

import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.common.platform.form.RuleForm;
import com.cqmike.common.platform.form.search.RuleSearchForm;
import com.cqmike.core.service.CurdService;

import java.util.Map;

/**
 * @program: 
 * @Interface: RuleService
 * @Description: RuleService接口类
 * @Author: chen qi
 * @Date: 2020-17-01 17:34
 * @Version: 1.0
**/
public interface RuleService extends CurdService<RuleForm, String, RuleSearchForm> {

    /**
     *  前置机初始化规则
     *  k -> 产品id   v -> (k -> 规则id, v -> 规则)
     * @return
     */
    Map<String, Map<String, RuleFormForFront>> findRuleFrontList();
}