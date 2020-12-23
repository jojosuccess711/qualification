/**
 * @filename:BaseParameterService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.BaseParameter;

import java.util.List;

/**
 *  
 * @Description:  参数表 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface BaseParameterService extends BaseService<BaseParameter, String> {

    /*批量添加*/
    int batchInsert(List<BaseParameter> list);

    List<BaseParameter> findAllParameters();

    List<BaseParameter> findParametersByGroupId(String groupId);
}