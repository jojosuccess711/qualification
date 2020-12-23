/**
 * @filename:BaseSimpleDefinitionServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.Order;
import com.bdjbd.Pageable;
import com.bdjbd.common.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.BaseSimpleDefinition;
import com.bdjbd.dao.mapper.BaseSimpleDefinitionMapper;
import com.bdjbd.service.BaseSimpleDefinitionService;

import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @Description: 简单数据定义 —— SERVICEIMPL
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "BaseSimpleDefinition")
public class BaseSimpleDefinitionServiceImpl extends BaseServiceImpl<BaseSimpleDefinition, String> implements BaseSimpleDefinitionService {

    @Autowired
    private BaseSimpleDefinitionMapper mapper;

    @Autowired
    public void setBaseDao(BaseSimpleDefinitionMapper mapper) {
        super.setBaseDao(mapper);
    }

    @Override
    @Cacheable
    public BaseSimpleDefinition find(String s) {
        return super.find(s);
    }

    @Override
    @Cacheable
    public Message findSelectOptionsByType(String type) {
        List<BaseSimpleDefinition> baseSimpleDefinitionList =
                findList(Arrays.asList(Filter.like("type", type)), null);
        Map map =
                baseSimpleDefinitionList.parallelStream()
                        .collect(Collectors.groupingBy(BaseSimpleDefinition::getAttr0,
                                Collectors.mapping(BaseSimpleDefinition::getName, Collectors.toList())));
        ;
        return Message.success(map);
    }

    @Override
    @Cacheable
    public List<BaseSimpleDefinition> findList(String type) {
        return findList( ListUtil.addToList(Filter.eq("type", type)), ListUtil.addToList(Order.asc("orders")));
    }

    @Cacheable
    @Override
    public List<BaseSimpleDefinition> findAllSimpleDefinitions() {
        return findAll();
    }
}