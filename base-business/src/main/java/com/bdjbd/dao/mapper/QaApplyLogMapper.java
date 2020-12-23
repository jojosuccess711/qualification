/**
 * @filename:QaApplyLogDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaApplyLog;

/**   
 *  
 * @Description:  申请审核动态日志——DAO
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaApplyLogMapper extends BaseDao<QaApplyLog> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaApplyLog> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaApplyLog> items);
}
