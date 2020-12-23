/**
 * @filename:BaseRelationParameterDefinitionServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.BaseRelationParameterDefinition;
import com.bdjbd.dao.mapper.BaseRelationParameterDefinitionMapper;
import com.bdjbd.service.BaseRelationParameterDefinitionService;

/**   
 *  
 * @Description:  录入信息记录表 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class BaseRelationParameterDefinitionServiceImpl extends BaseServiceImpl<BaseRelationParameterDefinition, String> implements BaseRelationParameterDefinitionService {

	@Autowired
    private BaseRelationParameterDefinitionMapper mapper;

    @Autowired
    public void setBaseDao(BaseRelationParameterDefinitionMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    public BaseRelationParameterDefinition findByParamGroup(String groupId) {
        return mapper.findByParamGroup(groupId);
    }
}