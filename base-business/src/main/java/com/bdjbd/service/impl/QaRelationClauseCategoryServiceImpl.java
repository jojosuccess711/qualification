/**
 * @filename:QaRelationClauseCategoryServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaRelationClauseCategory;
import com.bdjbd.dao.mapper.QaRelationClauseCategoryMapper;
import com.bdjbd.service.QaRelationClauseCategoryService;

/**   
 *  
 * @Description:  条件项与职称关系表 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaRelationClauseCategoryServiceImpl extends BaseServiceImpl<QaRelationClauseCategory, String> implements QaRelationClauseCategoryService {

	@Autowired
    private QaRelationClauseCategoryMapper mapper;

    @Autowired
    public void setBaseDao(QaRelationClauseCategoryMapper mapper){
        super.setBaseDao(mapper);
    }
}