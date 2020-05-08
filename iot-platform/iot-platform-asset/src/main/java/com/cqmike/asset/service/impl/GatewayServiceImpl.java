package com.cqmike.asset.service.impl;

import com.cqmike.asset.convert.GatewayConvert;
import com.cqmike.asset.entity.Gateway;
import com.cqmike.asset.repository.GatewayRepository;
import com.cqmike.asset.service.GatewayService;
import com.cqmike.common.platform.form.GatewayForm;
import com.cqmike.common.platform.form.search.GatewaySearchForm;
import com.cqmike.core.service.AbstractCurdService;
import org.springframework.stereotype.Service;
/**
 * @program: 
 * @Interface: GatewayServiceImpl
 * @Description: GatewayServiceImpl实现类
 * @Author: chen qi
 * @Date: 2020-16-07 16:22
 * @Version: 1.0
**/

@Service
public class GatewayServiceImpl extends AbstractCurdService<Gateway, String, GatewaySearchForm, GatewayForm> implements GatewayService {

    private final GatewayRepository repository;
    
    private final GatewayConvert convert;

    public GatewayServiceImpl(GatewayRepository repository,
                                GatewayConvert convert) {
        super(repository, convert);
        this.repository = repository;
        this.convert = convert;
    }
}