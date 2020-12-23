/**
 * @filename:QaBaseClauseItemDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.bo.MapperClause;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaBaseClauseItem;

/**   
 *  
 * @Description:  条件子项——DAO
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaBaseClauseItemMapper extends BaseDao<QaBaseClauseItem> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaBaseClauseItem> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaBaseClauseItem> items);

    /**
     * 根据职称集合获取Items
     * @param categoryAcademicIdList
     * @return
     */
    List<Map> findItemByAcademics(@Param("academicIdList") List<Long> categoryAcademicIdList);

    /**
     * 通过职称查询符合的条件信息
     */
    List<MapperClause> findItemsByCategory(@Param("categoryId") String categoryId);
}
