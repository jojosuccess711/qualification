/**
 * @filename:BaseSimpleDefinitionDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.BaseSimpleDefinition;

/**   
 *  
 * @Description:  简单数据定义——DAO
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface BaseSimpleDefinitionMapper extends BaseDao<BaseSimpleDefinition> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<BaseSimpleDefinition> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<BaseSimpleDefinition> items);
}
