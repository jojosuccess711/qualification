/**
 * @filename:QaRelationClauseGroupServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaRelationClauseGroup;
import com.bdjbd.dao.mapper.QaRelationClauseGroupMapper;
import com.bdjbd.service.QaRelationClauseGroupService;

/**   
 *  
 * @Description:  条件分组与条件项关系表 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaRelationClauseGroupServiceImpl extends BaseServiceImpl<QaRelationClauseGroup, String> implements QaRelationClauseGroupService {

	@Autowired
    private QaRelationClauseGroupMapper mapper;

    @Autowired
    public void setBaseDao(QaRelationClauseGroupMapper mapper){
        super.setBaseDao(mapper);
    }
}