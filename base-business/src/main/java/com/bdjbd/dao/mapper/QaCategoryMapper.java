/**
 * @filename:QaCategoryDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaCategory;

/**   
 *  
 * @Description:  评审分类——DAO
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaCategoryMapper extends BaseDao<QaCategory> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaCategory> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaCategory> items);

    /*各类评审人员数量*/
    List<Map<String, Object>> findListApplyNumber(@Param("status") Integer status);

    List<QaCategory> findListDetails();

    List<QaCategory> findChild(@Param("parent") String parent);

    QaCategory findDetailsById(String id);

    /**
     * 通过领域、分类、职称名称获取职称id
     * @param category 领域
     * @param subCategory 分类
     * @param jobTitle 职称
     * @return
     */
    List<QaCategory> findTitles(@Param("category") String category,@Param("subCategory") String subCategory,@Param("jobTitle") String
            jobTitle);
}
