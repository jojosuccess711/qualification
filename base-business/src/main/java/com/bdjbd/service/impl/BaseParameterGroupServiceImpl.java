/**
 * @filename:BaseParameterGroupServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.Order;
import com.bdjbd.dao.entity.BaseParameter;
import com.bdjbd.dao.entity.BaseParameterGroup;
import com.bdjbd.dao.entity.BaseRelationParameterDefinition;
import com.bdjbd.dao.entity.BaseSimpleDefinition;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.dao.mapper.BaseParameterGroupMapper;
import com.bdjbd.service.BCacheService;
import com.bdjbd.service.BaseParameterGroupService;
import com.bdjbd.service.BaseParameterService;
import com.bdjbd.service.BaseRelationParameterDefinitionService;
import com.bdjbd.service.BaseSimpleDefinitionService;
import com.bdjbd.service.QaCategoryService;
import com.bdjbd.service.QaStandardRecordItemCommittedService;
import com.bdjbd.service.QaStandardRecordItemService;
import com.bdjbd.service.QaStandardRecordService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @Description: 参数组表 —— SERVICEIMPL
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Slf4j
@Service
public class BaseParameterGroupServiceImpl extends BaseServiceImpl<BaseParameterGroup, String> implements BaseParameterGroupService {

    @Autowired
    private BaseParameterGroupMapper mapper;

    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    QaCategoryService qaCategoryService;

    @Autowired
    private BaseRelationParameterDefinitionService baseRelationParameterDefinitionService;
    @Autowired
    BaseSimpleDefinitionService baseSimpleDefinitionService;
    @Autowired
    QaStandardRecordItemService qaStandardRecordItemService;
    @Autowired
    QaStandardRecordService qaStandardRecordService;
    @Autowired
    BCacheService bCacheService;
    @Autowired
    QaStandardRecordItemCommittedService qaStandardRecordItemCommittedService;

    @Autowired
    public void setBaseDao(BaseParameterGroupMapper mapper) {
        super.setBaseDao(mapper);
    }

    @Override
    public int insertParam(List<BaseParameter> list) {
        return baseParameterService.batchInsert(list);
    }

    @Cacheable("findParamByGroupId")
    @Override
    public List<BaseParameter> findParamByGroupId(String groupId, boolean hasChild) {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("groupId", groupId));
        filters.add(Filter.eq("status", 1));
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("orders"));
        List<BaseParameter> list = baseParameterService.findList(filters, orders);
        if (!hasChild) {
            return list;
        }

        for (BaseParameter baseParameter : list) {
            List<Filter> filters2 = new ArrayList<>();
            filters2.add(Filter.eq("parameterId", baseParameter.getId()));
            List<BaseRelationParameterDefinition> relationParameterDefinitions = baseRelationParameterDefinitionService.findList(filters2, null);
            if (relationParameterDefinitions != null && relationParameterDefinitions.size() > 0) {
                BaseRelationParameterDefinition definition = relationParameterDefinitions.get(0);
                if (definition.getParameterGroupId() != null) {
                    List<BaseParameter> childParameterList = baseParameterService.findList(Arrays.asList(Filter.eq("groupId", definition.getParameterGroupId()), Filter.eq("status", 1)), orders);
                    baseParameter.setChildParameters(childParameterList.size() == 0 ? null : childParameterList);
                }
            }
        }
        return list;
    }

    @Override
    public int relationSave(BaseRelationParameterDefinition relationParameterDefinition) {
        return baseRelationParameterDefinitionService.save(relationParameterDefinition);
    }

    @Override
    public List<String> findCategoryList() {
        return mapper.findCategoryList();
    }

    @Cacheable("findGroupList")
    @Override
    public Message<?> findGroupList() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<String> categoryList = this.findCategoryList();
        for (String s : categoryList) {
            List<BaseParameterGroup> groups = findGroupListByCategory(s);
            Map<String, Object> map = new HashMap<>();
            map.put("type", s);
            map.put("list", groups);
            list.add(map);
        }
        return Message.success(list);
    }

    @Cacheable("findGroupListByCategory")
    @Override
    public List<BaseParameterGroup> findGroupListByCategory(String type) {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("grade", 0));
        filters.add(Filter.eq("attr0", type));
        filters.add(Filter.eq("status", 1));
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("orders"));
        List<BaseParameterGroup> groups = this.findList(filters, orders);
        for (BaseParameterGroup group : groups) {
            List<BaseParameter> parameterList = this.findParamByGroupId(group.getId(), false);
            group.setParameters(parameterList);
        }
        return groups;
    }

    @Cacheable("findGroupListByCategory")
    @Override
    public List<BaseParameterGroup> findAllGroupList() {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("status", 1));
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("grade"));
        orders.add(Order.asc("attr0"));
        orders.add(Order.asc("orders"));
        List<BaseParameterGroup> groups = this.findList( filters, orders);
        for (BaseParameterGroup group : groups) {
            List<BaseParameter> parameterList = this.findParamByGroupId(group.getId(), false);
            group.setParameters(parameterList);
        }
        return groups;
    }

    @Cacheable("findAllGroups")
    @Override
    public List<BaseParameterGroup> findAllGroups() {
        return findAll();
    }

    /**
     * 获取各类录入信息数据下拉选项数据
     * @return List
     */
    @Override
    public List<BaseParameterGroup> findOptions(String userId, boolean
            isCommitted) {
        String recordId = null;
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService.findList(Collections.singletonList(Filter.eq("userId", userId)), null);
        if (!ObjectUtils.isEmpty(qaStandardRecords)) {
            recordId = qaStandardRecords.get(0).getId();
        }
        //获取信息录入参数分组
        List<BaseParameterGroup> groups = bCacheService.findAllGroups();
        //获取录入信息参数信息
        List<BaseParameter> parameters = baseParameterService.findAllParameters();
        //获取参数项包含的参数属性
        List<BaseSimpleDefinition> options = baseSimpleDefinitionService
                .findAllSimpleDefinitions();
        //根据用户获取用户参数项录入信息
        List<QaStandardRecordItem> items = new ArrayList<>();
        if (!StringUtils.isEmpty(recordId)) {
            if (isCommitted) {
                items = qaStandardRecordItemCommittedService
                        .findRecordItemsByRecordId(recordId);
            }
            else {
                items = qaStandardRecordItemService.findItemsByRecordId(recordId);
            }

        }
        List<BaseRelationParameterDefinition> relations =
                baseRelationParameterDefinitionService.findAll();

        return groupParametersHandler(0, groups, parameters, options, items, relations);
        /*-------------------------------------------------*/
        //        return mapper.findOptions(userId);
    }

    /**
     * 构造分组的参数数据
     */
    private List<BaseParameterGroup> groupParametersHandler(int grade, List<BaseParameterGroup>
            groups, List<BaseParameter> parameters,
            List<BaseSimpleDefinition> options,
            List<QaStandardRecordItem> items, List<BaseRelationParameterDefinition> relations) {
        List<BaseParameterGroup> resultGroups = new ArrayList<>();
        List<BaseParameter> commonParams = parameters.parallelStream()
                .filter(item -> ("all".equals(item.getGroupId())) && item
                        .getStatus() != 0).sorted(Comparator
                        .comparing(BaseParameter::getOrders))
                .collect(Collectors.toList());
        for (BaseParameterGroup group : groups) {
            if (grade == group.getGrade()) {
                List<BaseParameter> groupParams = parameters.parallelStream()
                        .filter(item -> (item.getGroupId().equals(group.getId())) && item
                                .getStatus() != 0).sorted(Comparator
                                .comparing(BaseParameter::getOrders))
                        .collect(Collectors.toList());
                List<BaseParameter> commons = new ArrayList<>();
                for (BaseParameter common : commonParams) {
                    BaseParameter commonClone = (BaseParameter) common.clone();
                    commonClone.setGroupId(group.getId());
                    commons.add(commonClone);
                }
                groupParams.addAll(commons);
                for (BaseParameter parameter : groupParams) {
                    parameterItemsHandler(parameter, options, items,
                            relations, groups, parameters);
                }
                group.setParameters(groupParams);
                resultGroups.add(group);
            }
        }
        return resultGroups;
    }

    private void parameterItemsHandler(BaseParameter parameter,
            List<BaseSimpleDefinition> options, List<QaStandardRecordItem>
            items, List<BaseRelationParameterDefinition> relations, List<BaseParameterGroup>
            groups, List<BaseParameter> parameters) {
        List<BaseSimpleDefinition> paramOptions = options.parallelStream()
                .filter(item -> item.getType().equals(parameter
                        .getTypeValidate())).sorted(Comparator.comparing
                        (BaseSimpleDefinition::getOrders)).collect(Collectors.toList());
        List<QaStandardRecordItem> userItems = items.parallelStream().filter
                (item -> parameter.getGroupId().equals(item.getParameterGroupId())
                        && parameter.getId().equals(item.getParameterId()))
                .sorted(Comparator.comparing(QaStandardRecordItem::getAttr1)).collect
                        (Collectors.toList());
        List<Integer> orderList = userItems.stream()
                .sorted(Comparator.comparing(QaStandardRecordItem::getAttr1))
                .map(QaStandardRecordItem::getOrders).collect
                        (Collectors.toList());
        Optional op = userItems.parallelStream().map(QaStandardRecordItem::getOrders)
                .max(Comparator.naturalOrder());

        if (op.isPresent()) {
            parameter.setMaxOrder(op.get().toString());
        }
        else {
            parameter.setMaxOrder("0");
        }
        parameter.setOrderList(orderList);
        parameter.setUserSelection(userItems);
        parameter.setOptions(paramOptions);
        Set<String> relationGroupIds = relations.parallelStream().filter
                (item -> item.getParameterId().equals(parameter.getId())).map
                (BaseRelationParameterDefinition::getParameterGroupId).collect(Collectors.toSet());
        if (!ObjectUtils.isEmpty(relationGroupIds)) {
            List<BaseParameterGroup> childGroups = groups.parallelStream()
                    .filter(item -> relationGroupIds.contains(item.getId())).collect(Collectors.toList());
            groupParametersHandler(1, childGroups, parameters, options, items, relations);
            parameter.setBaseParameterGroups(childGroups);
        }
        else {
            parameter.setBaseParameterGroups(new ArrayList<>());
        }
    }

    @Override
    public List<BaseParameterGroup> findGroup(String userId, List<String> groupId) {
        return mapper.findGroup(userId, groupId);
    }
}