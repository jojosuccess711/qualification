/**
 * @filename:SysLogService 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys;

import com.bdjbd.dao.entity.SysLog;
import com.bdjbd.web.service.BaseService;

import java.util.List;

/**
 *  
 * @Description:  系统日志 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysLogService extends BaseService<SysLog, String> {

    SysLog createLog(Integer type, String relationId, String before, String after, String content, String memo, Boolean save);

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(List<SysLog> items);
}