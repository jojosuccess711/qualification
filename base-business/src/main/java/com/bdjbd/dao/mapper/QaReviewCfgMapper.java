/**
 * @filename:QaReviewCfgDao 2020年8月24日
 * @project 人员录入  V1.0
 * Copyright(c) 2018 Mnie Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaReviewCfg;

/**   
 *  
 * @Description:  评审配置——DAO
 * @Author:       Mnie   
 * @CreateDate:   2020年8月24日
 * @Version:      V1.0
 *    
 */
public interface QaReviewCfgMapper extends BaseDao<QaReviewCfg> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaReviewCfg> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaReviewCfg> items);
}
