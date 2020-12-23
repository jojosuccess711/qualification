/**
 * @filename:QaBaseClauseItemService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.Message;
import com.bdjbd.bo.MapperClause;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaBaseClauseItem;

import java.util.List;

/**   
 *  
 * @Description:  条件子项 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaBaseClauseItemService extends BaseService<QaBaseClauseItem, String> {

    Message<QaBaseClauseItem> finAllList(List<Long> categoryAcademicIdList);

    List<MapperClause> findItemsByCategory(String categoryId);
}