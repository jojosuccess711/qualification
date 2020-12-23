/**
 * @filename:BaseParameterGroupService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.Message;
import com.bdjbd.dao.entity.BaseParameter;
import com.bdjbd.dao.entity.BaseRelationParameterDefinition;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.BaseParameterGroup;

import java.util.List;
import java.util.Map;

/**
 *  
 * @Description:  参数组表 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface BaseParameterGroupService extends BaseService<BaseParameterGroup, String> {

    int insertParam(List<BaseParameter> list);

    List<BaseParameter> findParamByGroupId(String groupId, boolean hasChild);

    int relationSave(BaseRelationParameterDefinition relationParameterDefinition);

    // attr0
    List<String> findCategoryList();

    Message<?> findGroupList();

    List<BaseParameterGroup> findGroupListByCategory(String type);

    // 查询全部的参数组，包括一级二级
    List<BaseParameterGroup> findAllGroupList();
    /**
     * 获取各类录入信息数据下拉选项数据
     * @return List
     */
    List<BaseParameterGroup> findOptions(String userId,boolean isCommitted);

    List<BaseParameterGroup> findGroup(String userId,List<String> groupId);

    List<BaseParameterGroup> findAllGroups();
}