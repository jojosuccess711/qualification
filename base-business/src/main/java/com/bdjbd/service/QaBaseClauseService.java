/**
 * @filename:QaBaseClauseService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaBaseClause;

import java.util.List;

/**
 *  
 * @Description:  基本条件项 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaBaseClauseService extends BaseService<QaBaseClause, String> {

    int batchInsert(List<QaBaseClause> list);
}