/**
 * @filename:QaBaseClauseServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaBaseClause;
import com.bdjbd.dao.mapper.QaBaseClauseMapper;
import com.bdjbd.service.QaBaseClauseService;

import java.util.List;

/**   
 *  
 * @Description:  基本条件项 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaBaseClauseServiceImpl extends BaseServiceImpl<QaBaseClause, String> implements QaBaseClauseService {

	@Autowired
    private QaBaseClauseMapper mapper;

    @Autowired
    public void setBaseDao(QaBaseClauseMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    public int batchInsert(List<QaBaseClause> list) {
        return mapper.batchInsert(list);
    }
}