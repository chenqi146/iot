package com.cqmike.front.map;

import cn.hutool.core.collection.CollectionUtil;
import com.cqmike.common.front.enums.OperateTypeEnum;
import com.cqmike.common.platform.form.ProductPropertyForm;
import com.cqmike.core.exception.BusinessException;

import java.util.*;

/**
 * @program: iot
 * @ClassName: PropertyFormMap
 * @Description: 属性map  mqtt使用   因为有些连接走mqtt有些走netty
 * @Author: chen qi
 * @Date: 2020/3/19 20:58
 * @Version: 1.0
 **/
public class PropertyFormMap {

    /**
     * k -> 产品id   v -> (k -> 属性id, v -> 规则)
     */
    private static Map<String, Map<String, ProductPropertyForm>> propertyMap = null;

    public static void init(Map<String, Map<String, ProductPropertyForm>> property) {
        propertyMap = property;
    }

    public static List<ProductPropertyForm> get(String productId) {
        Map<String, ProductPropertyForm> map = propertyMap.get(productId);
        if (CollectionUtil.isEmpty(map)) {
            return Collections.emptyList();
        }
        Collection<ProductPropertyForm> values = map.values();
        return new ArrayList<>(values);
    }

    public static void put(String productId, String propertyId, ProductPropertyForm value) {
        Map<String, ProductPropertyForm> map = propertyMap.get(productId);
        if (map == null) {
            map = new HashMap<>(4);
        }
        map.put(propertyId, value);
        propertyMap.putIfAbsent(productId, map);
    }

    public static void remove(String productId, String propertyId) {
        Map<String, ProductPropertyForm> map = propertyMap.get(productId);
        if (CollectionUtil.isEmpty(map)) {
            return;
        }
        map.remove(propertyId);
    }


    /**
     * @param value       变动的规则
     * @param operateType 操作类型
     */
    public static void update(ProductPropertyForm value, OperateTypeEnum operateType) {
        if (CollectionUtil.isEmpty(propertyMap)) {
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
        propertyMap.clear();
    }

}
