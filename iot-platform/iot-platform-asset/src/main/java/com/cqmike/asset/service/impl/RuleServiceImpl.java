package com.cqmike.asset.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cqmike.asset.convert.RuleConvert;
import com.cqmike.asset.entity.Rule;
import com.cqmike.asset.repository.RuleRepository;
import com.cqmike.asset.service.RuleService;
import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.common.platform.form.RuleForm;
import com.cqmike.common.platform.form.search.RuleSearchForm;
import com.cqmike.core.exception.BusinessException;
import com.cqmike.core.service.AbstractCurdService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: 
 * @Interface: RuleServiceImpl
 * @Description: RuleServiceImpl实现类
 * @Author: chen qi
 * @Date: 2020-17-01 17:24
 * @Version: 1.0
**/

@Service
public class RuleServiceImpl extends AbstractCurdService<Rule, String, RuleSearchForm, RuleForm> implements RuleService {

    private final RuleRepository repository;

    private final RuleConvert convert;

    public RuleServiceImpl(RuleRepository repository,
                           RuleConvert convert) {
        super(repository, convert);
        this.repository = repository;
        this.convert = convert;
    }

    @Override
    public Map<String, Map<String, RuleFormForFront>> findRuleFrontList() {

        List<RuleForm> ruleFormList = this.listAll();

        List<RuleFormForFront> resultList = new ArrayList<>();
        RuleFormForFront front;
        for (RuleForm ruleForm : ruleFormList) {
            front = new RuleFormForFront();
            front.setRuleType(ruleForm.getType());
            BeanUtil.copyProperties(ruleForm, front);
            resultList.add(front);
        }

        return resultList.stream().collect(Collectors.groupingBy(RuleFormForFront::getProductId,
                Collectors.toMap(RuleFormForFront::getId,
                        f -> f, (f1, f2) -> f1)));
    }


    @Override
    public void removeInBatch(@NonNull Collection<String> strings) {
        throw new BusinessException("不允许批量删除");
    }

    @Override
    public void removeAll(@NonNull Collection<RuleForm> forms) {
        throw new BusinessException("不允许批量删除");
    }

    @Override
    public RuleForm removeById(String s) {
        return super.removeById(s);
    }

    @Override
    public RuleForm create(RuleForm form) {
        return super.create(form);
    }

    @Override
    public List<RuleForm> createInBatch(Collection<RuleForm> forms) {
        return super.createInBatch(forms);
    }

    @Override
    public RuleForm update(RuleForm form) {
        return super.update(form);
    }

    @Override
    public List<RuleForm> updateInBatch(Collection<RuleForm> forms) {
        return super.updateInBatch(forms);
    }
}