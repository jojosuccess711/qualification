/**
 * @filename:QaRelationClauseGroupCategoryDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaRelationClauseGroupCategory;

/**   
 *  
 * @Description:  条件分组与类别关系表——DAO
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaRelationClauseGroupCategoryMapper extends BaseDao<QaRelationClauseGroupCategory> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaRelationClauseGroupCategory> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaRelationClauseGroupCategory> items);
}
