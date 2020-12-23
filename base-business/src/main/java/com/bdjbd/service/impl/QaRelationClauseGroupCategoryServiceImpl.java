/**
 * @filename:QaRelationClauseGroupCategoryServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaRelationClauseGroupCategory;
import com.bdjbd.dao.mapper.QaRelationClauseGroupCategoryMapper;
import com.bdjbd.service.QaRelationClauseGroupCategoryService;

/**   
 *  
 * @Description:  条件分组与类别关系表 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaRelationClauseGroupCategoryServiceImpl extends BaseServiceImpl<QaRelationClauseGroupCategory, String> implements QaRelationClauseGroupCategoryService {

	@Autowired
    private QaRelationClauseGroupCategoryMapper mapper;

    @Autowired
    public void setBaseDao(QaRelationClauseGroupCategoryMapper mapper){
        super.setBaseDao(mapper);
    }
}