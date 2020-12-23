/**
 * @filename:BaseSimpleDefinitionService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.Message;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.BaseSimpleDefinition;

import java.util.List;

/**
 *  
 * @Description:  简单数据定义 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface BaseSimpleDefinitionService extends BaseService<BaseSimpleDefinition, String> {

       Message findSelectOptionsByType(String   type);

       /**
        * 通过类型获取一组定义
        * @param type
        * @return
        */
       List<BaseSimpleDefinition> findList(String type);

       List<BaseSimpleDefinition> findAllSimpleDefinitions();
}