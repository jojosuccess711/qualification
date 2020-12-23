/**
 * @filename:QaApproverDao 2019/11/07
 * @project 智慧停车  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaApprover;

/**   
 *  
 * @Description:  审批表——DAO
 * @Author:       DBC   
 * @CreateDate:   2019/11/07
 * @Version:      V1.0
 *    
 */
public interface QaApproverMapper extends BaseDao<QaApprover> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaApprover> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaApprover> items);
}
