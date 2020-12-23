/**
 * @filename:BaseRelationParameterDefinitionService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.BaseRelationParameterDefinition;

import java.util.List;

/**
 *  
 * @Description:  录入信息记录表 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface BaseRelationParameterDefinitionService extends BaseService<BaseRelationParameterDefinition, String> {

    /**
     * @param groupId
     * @return
     */
    BaseRelationParameterDefinition findByParamGroup(String groupId);
}