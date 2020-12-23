/**
 * @filename:BaseParameterServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.BaseParameter;
import com.bdjbd.dao.mapper.BaseParameterMapper;
import com.bdjbd.service.BaseParameterService;

import java.util.List;

/**   
 *  
 * @Description:  参数表 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class BaseParameterServiceImpl extends BaseServiceImpl<BaseParameter, String> implements BaseParameterService {

	@Autowired
    private BaseParameterMapper mapper;

    @Autowired
    public void setBaseDao(BaseParameterMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    public int batchInsert(List<BaseParameter> list) {
        return mapper.batchInsert(list);
    }
    @Cacheable("findAllParameters")
    @Override
    public List<BaseParameter> findAllParameters() {
        return findAll();
    }
    @Cacheable("findParametersByGroupId")
    @Override
    public List<BaseParameter> findParametersByGroupId(String groupId) {
        return mapper.findParametersByGroupId(groupId);
    }
}