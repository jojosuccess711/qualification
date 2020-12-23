/**
 * @filename:QaApplyLogServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import com.bdjbd.common.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaApplyLog;
import com.bdjbd.dao.mapper.QaApplyLogMapper;
import com.bdjbd.service.QaApplyLogService;

import java.util.Date;

/**   
 *  
 * @Description:  申请审核动态日志 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaApplyLogServiceImpl extends BaseServiceImpl<QaApplyLog, String> implements QaApplyLogService {

	@Autowired
    private QaApplyLogMapper mapper;

    @Autowired
    public void setBaseDao(QaApplyLogMapper mapper){
        super.setBaseDao(mapper);
    }

    /**
     * 添加审核日志
     * @param id
     * @param userId
     * @param userName
     * @param categoryId
     * @param categoryChildId
     * @param categoryAcademicId
     * @param type
     */
    @Override
    public void create(String id, String userId, String userName, String categoryId, String categoryChildId, String categoryAcademicId, Integer type) {
        QaApplyLog qaApplyLog = new QaApplyLog();
        qaApplyLog.setId(UUIDUtils.generateUuid());
        qaApplyLog.setCreateDate(new Date());
        qaApplyLog.setModifyDate(new Date());
        qaApplyLog.setApplyId(id);
        qaApplyLog.setUserId(userId);
        qaApplyLog.setUserName(userName);
        qaApplyLog.setCategoryId(categoryId);
        qaApplyLog.setCategoryChildId(categoryChildId);
        qaApplyLog.setCategoryAcademicId(categoryAcademicId);
        qaApplyLog.setType(type);
        this.save(qaApplyLog);
    }
}