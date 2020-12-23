/**
 * @filename:QaRelationClauseItemCategoryServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaRelationClauseItemCategory;
import com.bdjbd.dao.mapper.QaRelationClauseItemCategoryMapper;
import com.bdjbd.service.QaRelationClauseItemCategoryService;

/**   
 *  
 * @Description:  条件子项与分类关系表 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaRelationClauseItemCategoryServiceImpl extends BaseServiceImpl<QaRelationClauseItemCategory, String> implements QaRelationClauseItemCategoryService {

	@Autowired
    private QaRelationClauseItemCategoryMapper mapper;

    @Autowired
    public void setBaseDao(QaRelationClauseItemCategoryMapper mapper){
        super.setBaseDao(mapper);
    }
}