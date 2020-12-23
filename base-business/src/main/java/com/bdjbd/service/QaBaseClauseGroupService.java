/**
 * @filename:QaBaseClauseGroupService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.dao.entity.QaBaseClauseGroup;
import com.bdjbd.web.service.BaseService;

import java.util.List;

/**
 *  
 * @Description:  基本条件项组 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaBaseClauseGroupService extends BaseService<QaBaseClauseGroup, String> {

    int batchInsert(List<QaBaseClauseGroup> list);
}