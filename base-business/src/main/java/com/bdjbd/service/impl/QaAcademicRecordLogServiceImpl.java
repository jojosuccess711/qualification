/**
 * @filename:QaAcademicRecordLogServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaAcademicRecordLog;
import com.bdjbd.dao.mapper.QaAcademicRecordLogMapper;
import com.bdjbd.service.QaAcademicRecordLogService;

import java.util.List;

/**   
 *  
 * @Description:  职称评审记录日志 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaAcademicRecordLogServiceImpl extends BaseServiceImpl<QaAcademicRecordLog, String> implements QaAcademicRecordLogService {

	@Autowired
    private QaAcademicRecordLogMapper mapper;

    @Autowired
    public void setBaseDao(QaAcademicRecordLogMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    public int batchSave(List<QaAcademicRecordLog> list) {
        return mapper.batchInsert(list);
    }
}