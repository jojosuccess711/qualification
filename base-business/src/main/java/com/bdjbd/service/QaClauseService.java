/**
 * @filename:QaClauseService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaClause;

import java.util.List;

/**
 *  
 * @Description:  评审条件 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaClauseService extends BaseService<QaClause, String> {

    /**
     * 根据ID获取该条件序号下所有条件
     * @param id
     * @return
     */
    List<QaClause> findClauseList(String id);

    /**
     * 查找指定职称下所有条件集合
     * @param categoryId
     * @return
     */
    List<QaClause> findListByAcademic(String categoryId);

    /**
     * 根据条件ID查询与该条件重复的其他条件
     * @param key
     * @return
     */
    List<QaClause> findRepeatClauseListById(String key);

    List<QaClause> findByCategory(List<String> categoryIds);
}