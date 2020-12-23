/**
 * @filename:SysLogDao 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.SysLog;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  系统日志——DAO
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysLogMapper extends BaseDao<SysLog> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<SysLog> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<SysLog> items);
}
