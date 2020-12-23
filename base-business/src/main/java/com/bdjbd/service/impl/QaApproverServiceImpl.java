/**
 * @filename:QaApproverServiceImpl 2019/11/07
 * @project 智慧停车  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaApprover;
import com.bdjbd.dao.mapper.QaApproverMapper;
import com.bdjbd.service.QaApproverService;

/**   
 *  
 * @Description:  审批表 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2019/11/07
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaApproverServiceImpl extends BaseServiceImpl<QaApprover, String> implements QaApproverService {

	@Autowired
    private QaApproverMapper mapper;

    @Autowired
    public void setBaseDao(QaApproverMapper mapper){
        super.setBaseDao(mapper);
    }
}