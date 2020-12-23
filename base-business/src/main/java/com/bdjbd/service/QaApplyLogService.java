/**
 * @filename:QaApplyLogService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaApplyLog;
/**   
 *  
 * @Description:  申请审核动态日志 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaApplyLogService extends BaseService<QaApplyLog, String> {

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
    void create(String id, String userId, String userName, String categoryId, String categoryChildId, String categoryAcademicId, Integer type);
}