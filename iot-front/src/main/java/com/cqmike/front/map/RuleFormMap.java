package com.cqmike.front.map;

import cn.hutool.core.collection.CollectionUtil;
import com.cqmike.common.front.enums.OperateTypeEnum;
import com.cqmike.common.front.form.RuleFormForFront;
import com.cqmike.core.exception.BusinessException;

import java.util.*;

/**
 * @program: iot
 * @ClassName: RuleFormMap
 * @Description: 规则map todo 现在分发数据 如果是网关设备的话  子设备分发数据跟随对应的产品规则
 * @Author: chen qi
 * @Date: 2020/3/19 20:58
 * @Version: 1.0
 **/
public class RuleFormMap {

    /**
     * k -> 产品id   v -> (k -> 规则id, v -> 规则)
     */
    private static Map<String, Map<String, RuleFormForFront>> ruleMap = null;

    public static void init(Map<String, Map<String, RuleFormForFront>> rule) {
        ruleMap = rule;
    }

    public static List<RuleFormForFront> get(String productId) {
        Map<String, RuleFormForFront> frontMap = ruleMap.get(productId);
        if (CollectionUtil.isEmpty(frontMap)) {
            return Collections.emptyList();
        }

        Collection<RuleFormForFront> values = frontMap.values();
        return new ArrayList<>(values);
    }

    public static void put(String productId, String ruleId, RuleFormForFront value) {
        Map<String, RuleFormForFront> map = ruleMap.get(productId);
        if (map == null) {
            map = new HashMap<>(4);
        }
        map.put(ruleId, value);
        ruleMap.putIfAbsent(productId, map);
    }

    public static void remove(String productId, String ruleId) {
        Map<String, RuleFormForFront> map = ruleMap.get(productId);
        if (CollectionUtil.isEmpty(map)) {
            return;
        }
        map.remove(ruleId);
    }


    /**
     * @param value       变动的规则
     * @param operateType 操作类型
     */
    public static void update(RuleFormForFront value, OperateTypeEnum operateType) {
        if (CollectionUtil.isEmpty(ruleMap)) {
            return;
        }
        switch (operateType) {
            case UPDATE:
            case CREATE:
                put(value.getProductId(), value.getId(), value);
                break;
            case DELETE:
                remove(value.getProductId(), value.getId());
                break;
            case DELETE_ALL:
                removeAll();
                break;
            default:
                throw new BusinessException("没有对应的枚举类型" + operateType);
        }

    }

    private static void removeAll() {
        ruleMap.clear();
    }

}
