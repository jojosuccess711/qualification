/**
 * @filename:QaAcademicRecordLogService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaAcademicRecordLog;

import java.util.List;

/**
 *  
 * @Description:  职称评审记录日志 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaAcademicRecordLogService extends BaseService<QaAcademicRecordLog, String> {

    int batchSave(List<QaAcademicRecordLog> list);
}