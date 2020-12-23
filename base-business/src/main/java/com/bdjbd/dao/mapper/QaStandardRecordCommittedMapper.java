/**
 * @filename:QaStandardRecordCommittedDao 2020年4月9日
 * @project 职称评审系统  V1.0
 * Copyright(c) 2018 songzekun Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaStandardRecordCommitted;

/**   
 *  
 * @Description:  已提交的用户信息——DAO
 * @Author:       songzekun   
 * @CreateDate:   2020年4月9日
 * @Version:      V1.0
 *    
 */
public interface QaStandardRecordCommittedMapper extends BaseDao<QaStandardRecordCommitted> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaStandardRecordCommitted> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaStandardRecordCommitted> items);
}
