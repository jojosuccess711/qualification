/**
 * @filename:BaseParameterGroupDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.BaseParameterGroup;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  参数组表——DAO
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface BaseParameterGroupMapper extends BaseDao<BaseParameterGroup> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<BaseParameterGroup> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<BaseParameterGroup> items);

    /**
     * 通过层级获取参数组列表
     * @param grade
     * @return
     */
    List<BaseParameterGroup> findList(Integer grade);

    /**
     * 通过id获取参数组（包含参数项）
     * @param id
     * @return
     */
    BaseParameterGroup findHasParameterById(String id);

    List<String> findCategoryList();
    /**
     * 获取各类录入信息数据下拉选项数据
     *
     * @return List
     */
    List<BaseParameterGroup> findOptions(@Param("userId") String userId);

    List<BaseParameterGroup> findGroup(@Param("userId") String userId,@Param
            ("groupId")List<String> groupId);
}
