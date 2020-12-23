package com.bdjbd.service.impl;

import com.bdjbd.dao.entity.BaseParameterGroup;
import com.bdjbd.service.BCacheService;
import com.bdjbd.service.BaseParameterGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author zekunsong
 * @CreateDate 2020/4/20
 * @Version V1.0
 */
@Service
public class BCacheServiceImpl implements BCacheService {
    @Autowired
    BaseParameterGroupService service;
    @Cacheable("findAllGroups")
    @Override
    public List<BaseParameterGroup> findAllGroups() {
        return service.findAll();
    }
}
