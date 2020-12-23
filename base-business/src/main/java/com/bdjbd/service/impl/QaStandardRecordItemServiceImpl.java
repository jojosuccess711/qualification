/**
 * @filename:QaStandardRecordItemServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.bo.ParameterCheck;
import com.bdjbd.bo.StandardRecordItemOrder;
import com.bdjbd.common.util.DateUtil;
import com.bdjbd.common.util.UUIDUtils;
import com.bdjbd.dao.entity.*;
import com.bdjbd.dao.mapper.QaStandardRecordItemMapper;
import com.bdjbd.service.*;
import com.bdjbd.service.common.RedisService;
import com.bdjbd.util.ClassTransferUtil;
import com.bdjbd.util.FileUtils;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 用户标准信息记录项 —— SERVICEIMPL
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 */
@Slf4j
@Service
public class QaStandardRecordItemServiceImpl extends BaseServiceImpl<QaStandardRecordItem, String> implements QaStandardRecordItemService {
    @Autowired
    BaseParameterService baseParameterService;
    @Autowired
    QaStandardRecordService qaStandardRecordService;
    @Autowired
    BaseParameterGroupService baseParameterGroupService;
    @Autowired
    private QaStandardRecordItemMapper mapper;

    @Autowired
    private BaseRelationParameterDefinitionService baseRelationParameterDefinitionService;
    @Autowired
    RedisService redisService;
    @Autowired
    QaStandardRecordCommittedService qaStandardRecordCommittedService;
    @Autowired
    QaStandardRecordItemCommittedService qaStandardRecordItemCommittedService;

    @Value("${file.upload.path}")
    private String filePath;

    @Value("${file.upload.img-path}")
    private String imgPath;

    @Autowired
    public void setBaseDao(QaStandardRecordItemMapper mapper) {
        super.setBaseDao(mapper);
    }

    @Override
    public void replaceData(List<QaStandardRecordItem> useData) {
        mapper.replaceData(useData);
    }

    /**
     * 通过recordId获取填写项中的最大id
     * @return
     */
    @Override
    public Integer findMaxId(String recordId) {
        return mapper.findMaxId(recordId);
    }

    @Override
    public void batchDelete(List<QaStandardRecordItem> items) {
        mapper.batchDelete(items);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Message saveStandardRecordItems(List<QaStandardRecordItem>
            qaStandardRecordItems, String userId, String groupId) {
        qaStandardRecordItems = qaStandardRecordItems.parallelStream().filter
                (item -> item.getParameterGroupId().equals(groupId)).collect(Collectors.toList());
        List<QaStandardRecordItem> recordItems =
                qaStandardRecordItems.parallelStream().filter(item -> "10100".equals(item.getParameterGroupId())).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(recordItems)) {
            log.error("录入信息不包含基本信息");
        }
        List<BaseParameter> baseParameters = baseParameterService
                .findParametersByGroupId(groupId);
        Message res = checkUserInfo(recordItems, baseParameters, groupId);
        if (!res.isSuccess()) {
            return res;
        }
        QaStandardRecord standardRecord = null;
        Class recordClass = QaStandardRecord.class;
        Object object = null;
        try {
            object = recordClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        for (QaStandardRecordItem item : recordItems) {
            Optional optional = baseParameters.parallelStream()
                    .filter(param -> param.getId().equals(item.getParameterId()
                    )).findFirst();
            if (!optional.isPresent() || StringUtils.isEmpty(((BaseParameter) optional.get()).getAttr0())) {
                log.error("当前参数不属于基本信息");
                continue;
            }
            BaseParameter baseParameter = (BaseParameter) optional.get();
            String validate = baseParameter.getTypeValidate();
            if (validate.contains("|")) {
                validate = validate.split("\\|")[0].split("-")[0];
            }
            Field field;
            try {
                String value = item.getParameterValue();
                if (value == null) {
                    value = "";
                }
                field = recordClass.getDeclaredField(baseParameter.getAttr0());
                field.setAccessible(true);
                if (Long.class == field.getType()) {
                    if ("sex".equals(field.getName())) {
                        if ("男".equals(value)) {
                            field.set(object, 1L);
                        }
                        else {
                            field.set(object, 0L);
                        }
                    }
                    else {
                        field.set(object, Long.parseLong(value));
                    }
                }
                else if (Date.class == field.getType()) {
                    Date date = null;
                    if (!StringUtils.isEmpty(value)) {
                        date = DateUtil.dateParse(DateUtil
                                .DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, value);
                        if (date == null) {
                            date = DateUtil.dateParse(DateUtil
                                    .DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, value + " 00:00:00");
                        }
                        SimpleDateFormat dateFormat;
                        if (date == null) {
                            try {
                                dateFormat = new SimpleDateFormat(validate);
                                date = dateFormat.parse(value);
                            } catch (ParseException e) {
                                log.error("{}：日期解析异常", baseParameter.getId());
                            }
                        }
                    }
                    field.set(object, date);
                }
                else {
                    field.set(object, value);
                }
                standardRecord = (QaStandardRecord) object;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        QaStandardRecord record;
        Message message = qaStandardRecordService.saveOrUpdateStandardRecord(standardRecord, userId);
        if (message.isSuccess()) {
            record = (QaStandardRecord) message.getData();
        }
        else {
            return message;
        }
        if (record == null) {
            List<QaStandardRecord> qaStandardRecords = qaStandardRecordService.findList
                    (1, Collections.singletonList(Filter.eq("userId", userId)), null);
            if (ObjectUtils.isEmpty(qaStandardRecords)) {
                return Message.error("您未录入基本信息数据，请先录入！");
            }
            record = qaStandardRecords.get(0);
        }

        Integer maxId = findMaxId(record.getId());
        Object recordItemIndex = redisService.getRedisTemplate().opsForHash().get("RECORD_ITEM_ID_INDEX", record.getId());
        if (recordItemIndex == null) {
            redisService.getRedisTemplate().opsForHash().put
                    ("RECORD_ITEM_ID_INDEX", record.getId(), maxId == null ? 0 : maxId);
        }
        String[] groupIds = groupId.split(",");
        if (!ObjectUtils.isEmpty(qaStandardRecordItems)) {
            for (QaStandardRecordItem item : qaStandardRecordItems) {
                getItemId(record.getId(), item);
                String value = item.getParameterValue();
                if (value == null || "null".equals(value)) {
                    value = "";
                }
                item.setRecordId(record.getId());
                Optional op = baseParameters.parallelStream()
                        .filter(param -> param.getId().equals(item.getParameterId()
                        )).findFirst();
                BaseParameter baseParameter = null;
                if (op.isPresent()) {
                    baseParameter = (BaseParameter) op.get();
                }
                else {
                    baseParameter = baseParameterService.find(item.getParameterId());
                }
                if (baseParameter == null) {
                    return Message.success("参数id" + item.getParameterId() + "不存在");
                }
                String validate = baseParameter.getTypeValidate();
                if ("date".equals(baseParameter.getType())) {
                    if (StringUtils.isEmpty(value)) {
                        item.setParameterValue("");
                    }
                    else {
                        if (value.contains("|")) {
                            String[] validates = validate.split("\\|")[0].split("-");
                            String[] dates = value.split("\\|");
                            String startDate = dates.length == 0 || "".equals(dates[0]) || "null".equals(dates[0]) ?
                                    "" : formatDate
                                    (validates[0], dates[0]);
                            String endDate = dates.length == 0 || dates.length ==
                                    1 || "至今".equals(dates[1]) || "".equals
                                    (dates[1]) || "null".equals(dates[1]) ? "至今"
                                    : formatDate
                                    (validates[1], dates[1]);
                            item.setParameterValue(startDate + "-" + endDate);
                        }
                        else {
                            item.setParameterValue(formatDate(validate, value));
                        }
                    }
                }
                if ("integer".equals(validate) && "".equals(value)) {
                    item.setParameterValue("0");
                }
                item.setGroupId(item.getParameterGroupId());
                item.setType(baseParameter.getType());
                item.setTypeValidate(baseParameter.getTypeValidate());
                item.setName(baseParameter.getName());
                item.setAttr2(baseParameter.getTypeValidate());
                item.setParameter(baseParameter);
            }
            // 排序 orders
            orderItems(qaStandardRecordItems);
            //            qaStandardRecordItems=qaStandardRecordItems.parallelStream()
            //                    .sorted(Comparator.comparing
            //                            (QaStandardRecordItem::getOrders)).collect(Collectors.toList());
            deleteByGroupId(groupIds, record.getId());
            //            batchDelete(qaStandardRecordItems);
            batchInsert(qaStandardRecordItems);
        }
        else {
            deleteByGroupId(groupIds, record.getId());
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("groupId", groupId);
        resMap.put("parentValue", "");
        resMap.put("items", qaStandardRecordItems);
        // 根据二级表处理得到一级表的数据
        parentValueDeal(qaStandardRecordItems, resMap, groupIds);
        return Message.success(resMap);
    }

    @Override
    public Message saveStandardRecordItems2(List<QaStandardRecordItem> qaStandardRecordItems, String userId, String groupId) throws IllegalAccessException, InstantiationException {
        if (StringUtils.isEmpty(groupId) || StringUtils.isEmpty(userId) || ObjectUtils.isEmpty(qaStandardRecordItems)) {
            return Message.error("参数错误");
        }
        List<BaseParameter> baseParameters = baseParameterService.findParametersByGroupId(groupId);
        QaStandardRecord standardRecord = null;
        if (groupId.equals("10100")) { //个人基本信息
            Message res = checkUserInfo(qaStandardRecordItems, baseParameters, groupId);
            if (!res.isSuccess()) {
                return res;
            }
            standardRecord = getQaStandardRecord(qaStandardRecordItems, baseParameters);
            if (standardRecord == null) {
                return Message.error("未获取到个人基本信息");
            }
            standardRecord = (QaStandardRecord) qaStandardRecordService.saveOrUpdateStandardRecord(standardRecord, userId).getData();
        }

        if (standardRecord == null) {
            List<QaStandardRecord> qaStandardRecords = qaStandardRecordService.findList(1, Collections.singletonList(Filter.eq("userId", userId)), null);
            if (ObjectUtils.isEmpty(qaStandardRecords)) {
                return Message.error("您未录入基本信息数据，请先录入！");
            }
            standardRecord = qaStandardRecords.get(0);
        }

        //处理items
        Message message = dealItems(qaStandardRecordItems, baseParameters, standardRecord);
        if (message != null) {
            return message;
        }

        // 查改删存
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("recordId", standardRecord.getId()));
        filters.add(Filter.eq("parameterGroupId", groupId));
        List<QaStandardRecordItem> list = this.findList(Integer.MAX_VALUE, filters, null);

        int paramOrders = qaStandardRecordItems.get(0).getOrders();
        List<QaStandardRecordItem> collect = list.stream().filter(item -> item.getOrders().equals(paramOrders)).collect(Collectors.toList());

        list.removeAll(collect);
        list.addAll(qaStandardRecordItems);

        list.forEach(item -> {
            item.setParameter(getParameter(baseParameters, item));
        });
        // 排序 orders
        orderItems(list);

        String[] groupIds = groupId.split(",");
        deleteByGroupId(groupIds, standardRecord.getId());

        batchInsert(list);

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("groupId", groupId);
        resMap.put("parentValue", "");
        resMap.put("items", qaStandardRecordItems);
        // 根据二级表处理得到一级表的数据
        parentValueDeal(list, resMap, groupIds);
        return Message.success(resMap);
    }

    private QaStandardRecord getQaStandardRecord(List<QaStandardRecordItem> qaStandardRecordItems, List<BaseParameter> baseParameters) throws IllegalAccessException, InstantiationException {
        QaStandardRecord standardRecord = null;
        Class recordClass = QaStandardRecord.class;
        Object object = recordClass.newInstance();
        for (QaStandardRecordItem item : qaStandardRecordItems) {
            Optional optional = baseParameters.parallelStream().filter(param -> param.getId().equals(item.getParameterId())).findFirst();
            if (!optional.isPresent() || StringUtils.isEmpty(((BaseParameter) optional.get()).getAttr0())) {
                log.error("当前参数不属于基本信息");
                continue;
            }
            BaseParameter baseParameter = (BaseParameter) optional.get();
            String validate = baseParameter.getTypeValidate();
            if (validate.contains("|")) {
                validate = validate.split("\\|")[0].split("-")[0];
            }
            Field field;
            try {
                String value = item.getParameterValue() == null ? "" : item.getParameterValue();
                field = recordClass.getDeclaredField(baseParameter.getAttr0());
                field.setAccessible(true);
                if (Long.class == field.getType()) {
                    if ("sex".equals(field.getName())) {
                        field.set(object, "男".equals(value) ? 1L : 0L);
                    } else {
                        field.set(object, Long.parseLong(value));
                    }
                } else if (Date.class == field.getType()) {
                    Date date = null;
                    if (!StringUtils.isEmpty(value)) {
                        date = DateUtil.dateParse(DateUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, value);
                        if (date == null) {
                            date = DateUtil.dateParse(DateUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, value + " 00:00:00");
                        }
                        SimpleDateFormat dateFormat;
                        if (date == null) {
                            try {
                                dateFormat = new SimpleDateFormat(validate);
                                date = dateFormat.parse(value);
                            } catch (ParseException e) {
                                log.error("{}：日期解析异常", baseParameter.getId());
                            }
                        }
                    }
                    field.set(object, date);
                } else {
                    field.set(object, value);
                }
                standardRecord = (QaStandardRecord) object;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return standardRecord;
    }

    private Message dealItems(List<QaStandardRecordItem> qaStandardRecordItems, List<BaseParameter> baseParameters, QaStandardRecord standardRecord) {
        for (QaStandardRecordItem item : qaStandardRecordItems) {
            item.setId(UUIDUtils.generateUuid());
            String value = item.getParameterValue();
            if (value == null || "null".equals(value)) {
                value = "";
            }
            item.setRecordId(standardRecord.getId());
            BaseParameter baseParameter = getParameter(baseParameters, item);
            if (baseParameter == null) {
                return Message.success("参数id" + item.getParameterId() + "不存在");
            }
            String validate = baseParameter.getTypeValidate();
            if ("date".equals(baseParameter.getType())) {
                if (StringUtils.isEmpty(value)) {
                    item.setParameterValue("");
                } else {
                    if (value.contains("|")) {
                        String[] validates = validate.split("\\|")[0].split("-");
                        String[] dates = value.split("\\|");
                        String startDate = dates.length == 0 || "".equals(dates[0]) || "null".equals(dates[0]) ? "" : formatDate(validates[0], dates[0]);
                        String endDate = dates.length == 0 || dates.length == 1 || "至今".equals(dates[1]) || "".equals
                                (dates[1]) || "null".equals(dates[1]) ? "至今" : formatDate(validates[1], dates[1]);
                        item.setParameterValue(startDate + "-" + endDate);
                    } else {
                        item.setParameterValue(formatDate(validate, value));
                    }
                }
            }
            if ("integer".equals(validate) && "".equals(value)) {
                item.setParameterValue("0");
            }
            item.setGroupId(item.getParameterGroupId());
            item.setType(baseParameter.getType());
            item.setTypeValidate(baseParameter.getTypeValidate());
            item.setName(baseParameter.getName());
            item.setAttr2(baseParameter.getTypeValidate());
            item.setParameter(baseParameter);
        }
        return null;
    }

    private BaseParameter getParameter(List<BaseParameter> baseParameters, QaStandardRecordItem item) {
        Optional op = baseParameters.parallelStream().filter(param -> param.getId().equals(item.getParameterId())).findFirst();
        BaseParameter baseParameter = null;
        if (op.isPresent()) {
            baseParameter = (BaseParameter) op.get();
        } else {
            baseParameter = baseParameterService.find(item.getParameterId());
        }
        return baseParameter;
    }

    @Override
    public Message deleteRecordAndItem(List<QaStandardRecordItem> qaStandardRecordItems, String userId) {
        if (StringUtils.isEmpty(userId) || ObjectUtils.isEmpty(qaStandardRecordItems)) {
            return Message.error("参数错误");
        }
        String groupId = qaStandardRecordItems.get(0).getParameterGroupId();
        Integer orders = qaStandardRecordItems.get(0).getOrders();

        if (groupId.equals("10100")) {
            return Message.error("个人基本信息不可直接删除");
        }

        //查询 record
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService.findList(1, Collections.singletonList(Filter.eq("userId", userId)), null);
        if (ObjectUtils.isEmpty(qaStandardRecords)) {
            return Message.success();
        }
        QaStandardRecord standardRecord = qaStandardRecords.get(0);
        // delete items
        this.deleteByRecordIdAndparamId(groupId, null, standardRecord.getId(), orders);

        // 如果有二级表，需要清空二级表
        List<BaseParameter> baseParameters = baseParameterService.findParametersByGroupId(groupId);
        List<BaseRelationParameterDefinition> all = baseRelationParameterDefinitionService.findAll();
        List<String> paramIdList = new ArrayList<>();
        for (BaseRelationParameterDefinition definition : all) {
            paramIdList.add(definition.getParameterId());
            for (BaseParameter baseParameter : baseParameters) {
                if (definition.getParameterId().equals(baseParameter.getId())) {
                    // 删除二级表
                    this.deleteByRecordIdAndparamId(definition.getParameterGroupId(), null, standardRecord.getId(), null);
                }
            }
        }

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("groupId", groupId);
        resMap.put("parentValue", "");
        resMap.put("items", qaStandardRecordItems);
        // 根据二级表处理得到一级表的数据
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("recordId", standardRecord.getId()));
        filters.add(Filter.eq("parameterGroupId", groupId));
        List<QaStandardRecordItem> list = this.findList(Integer.MAX_VALUE, filters, null);
        for (QaStandardRecordItem item : list) {
            item.setParameter(getParameter(baseParameters, item));
        }
        parentValueDeal(list, resMap, groupId.split(","));
        return Message.success(resMap);
    }

    @SuppressWarnings("unchecked")
    private void getItemId(String recordId, QaStandardRecordItem item) {
        long id = redisService.getRedisTemplate().opsForHash().increment
                ("RECORD_ITEM_ID_INDEX", recordId, 1L);
        item.setId(recordId + "_" + (id + 1 > 9 ? id + 1 : "0" + (id + 1)));
    }

    // 排序 orders
    private void orderItems(List<QaStandardRecordItem> qaStandardRecordItems) {

        // 可能有多组的数据，groupId分组
        Map<String, List<QaStandardRecordItem>> mapGroup = new HashMap<>();
        for (QaStandardRecordItem qaStandardRecordItem : qaStandardRecordItems) {
            String parameterGroupId = qaStandardRecordItem.getParameterGroupId();
            List<QaStandardRecordItem> recordItemList = mapGroup.get(parameterGroupId);
            if (recordItemList == null) {
                recordItemList = new ArrayList<>();
            }
            recordItemList.add(qaStandardRecordItem);
            mapGroup.put(parameterGroupId, recordItemList);
        }
        // 各分组排序
        for (String s : mapGroup.keySet()) {
            sortOrders(mapGroup.get(s));
        }
    }

    private void sortOrders(List<QaStandardRecordItem> qaStandardRecordItems) {
        Map<Integer, List<QaStandardRecordItem>> map = mapByOrders(qaStandardRecordItems);
        if (map.size() <= 1) {
            for (QaStandardRecordItem item : qaStandardRecordItems) {
                item.setAttr1(item.getOrders());
            }
            return;
        }

        List<StandardRecordItemOrder> standardRecordItemOrderList = new ArrayList<>();
        for (Integer integer : map.keySet()) {
            List<QaStandardRecordItem> recordItemList = map.get(integer);
            StandardRecordItemOrder standardRecordItemOrder = new StandardRecordItemOrder();
            standardRecordItemOrder.setItems(recordItemList);
            standardRecordItemOrder.setOrder(integer);
            for (QaStandardRecordItem item : recordItemList) {
                String attr4 = item.getParameter().getAttr4();
                if (!StringUtils.isEmpty(attr4)) {
                    List<ParameterCheck> parameterChecks = JSON.parseArray(attr4, ParameterCheck.class);
                    ParameterCheck dateOrder = ParameterCheck.getInstance("dateOrder", parameterChecks);
                    if (dateOrder != null && dateOrder.isStatus()) {
                        JSONObject checkJson = dateOrder.getCheckJson();
                        standardRecordItemOrder.setOrderType(checkJson.getString("order"));
                        String parameterValue = item.getParameterValue();
                        String typeValidate = item.getParameter().getTypeValidate().split("-")[0];
                        if (StringUtils.isEmpty(parameterValue) || "-至今".equals(parameterValue)) {
                            standardRecordItemOrder.setOrderTime(new Date());
                        }
                        else {
                            standardRecordItemOrder.setOrderTime(DateUtil.dateParse(typeValidate, parameterValue));
                        }
                        break;
                    }
                }
            }
            standardRecordItemOrderList.add(standardRecordItemOrder);
        }
        sortItems(standardRecordItemOrderList);
        for (int i = 0; i < standardRecordItemOrderList.size(); i++) {
            standardRecordItemOrderList.get(i).setOrder(i);
            for (QaStandardRecordItem item : standardRecordItemOrderList.get(i).getItems()) {
                //                item.setOrders(i);
                item.setAttr1(i);
            }
        }
    }

    private Map<Integer, List<QaStandardRecordItem>> mapByOrders(List<QaStandardRecordItem> qaStandardRecordItems) {
        Map<Integer, List<QaStandardRecordItem>> map = new HashMap<>();
        for (QaStandardRecordItem qaStandardRecordItem : qaStandardRecordItems) {
            List<QaStandardRecordItem> recordItemList = map.get(qaStandardRecordItem.getOrders());
            if (recordItemList == null) {
                recordItemList = new ArrayList<>();
            }
            recordItemList.add(qaStandardRecordItem);
            map.put(qaStandardRecordItem.getOrders(), recordItemList);
        }
        return map;
    }

    private void sortItems(List<StandardRecordItemOrder> standardRecordItemOrderList) {
        Collections.sort(standardRecordItemOrderList, new Comparator<StandardRecordItemOrder>() {
            @Override
            public int compare(StandardRecordItemOrder o1, StandardRecordItemOrder o2) {
                if (o1.getOrderType().equals(StandardRecordItemOrder.ASC)) {
                    return o1.getOrderTime().compareTo(o2.getOrderTime());
                }
                else {
                    return o2.getOrderTime().compareTo(o1.getOrderTime());
                }
            }
        });
    }

    // 根据二级表处理得到一级表的数据
    private void parentValueDeal(List<QaStandardRecordItem>
            qaStandardRecordItems, Map<String, Object> resMap, String[]
            groupIds) {
        if (!ObjectUtils.isEmpty(qaStandardRecordItems)) {
            //  若是二级表数据保存，需要生成一级表的数据返回前端
            BaseParameterGroup baseParameterGroup = baseParameterGroupService.find(qaStandardRecordItems.get(0).getParameterGroupId());
            if (baseParameterGroup.getGrade() == 1) {
                BaseRelationParameterDefinition byParamGroup = baseRelationParameterDefinitionService.findByParamGroup(qaStandardRecordItems.get(0).getParameterGroupId());
                String parameterId = byParamGroup.getParameterId();
                String checkType = baseParameterGroup.getAttr2();
                JSONObject jsonObject = JSONObject.parseObject(checkType);
                List<BaseParameter> parameters = new ArrayList<>();
                parameters.add(getParameter(qaStandardRecordItems, parameterId, jsonObject));
                JSONArray relationParameter = jsonObject.getJSONArray("relationParameter");
                if (relationParameter != null && relationParameter.size() > 0) {
                    for (Object o : relationParameter) {
                        JSONObject parameter = (JSONObject) o;
                        parameters.add(getParameter(qaStandardRecordItems, parameter.getString("id"), parameter.getJSONObject("rule")));
                    }
                }
                resMap.put("parentValue", parameters);
            }
        }
        else {
            String groupId = "";
            if (groupIds.length == 1) {
                groupId = groupIds[0];
                BaseParameterGroup baseParameterGroup = baseParameterGroupService.find(groupId);
                if (baseParameterGroup.getGrade() == 1) {
                    BaseRelationParameterDefinition byParamGroup = baseRelationParameterDefinitionService.findByParamGroup(groupId);
                    BaseParameter baseParameter = baseParameterService.find(byParamGroup.getParameterId());
                    baseParameter.setAttr1("");
                    List<BaseParameter> parameters = new ArrayList<>();
                    parameters.add(baseParameter);
                    JSONObject jsonObject = JSONObject.parseObject(baseParameterGroup.getAttr2());
                    JSONArray relationParameter = jsonObject.getJSONArray("relationParameter");
                    if (relationParameter != null && relationParameter.size() > 0) {
                        for (Object o : relationParameter) {
                            JSONObject parameter = (JSONObject) o;
                            BaseParameter base = baseParameterService.find(parameter.getString("id"));
                            base.setAttr1("");
                            parameters.add(base);
                        }
                    }
                    resMap.put("parentValue", parameters);
                }
            }
        }
    }

    /**
     * 匹配规则
     * @return
     */
    private BaseParameter getParameter(List<QaStandardRecordItem> qaStandardRecordItems, String parameterId, JSONObject jsonObject) {
        BaseParameter baseParameter = baseParameterService.find(parameterId);
        String type = jsonObject.getString("type");
        Map<Integer, List<QaStandardRecordItem>> map = mapByOrders(qaStandardRecordItems);
        Integer checkSuccessNumber = map.size();
        switch (type) {
            case "count":// 统计验证通过的数目
                checkSuccessNumber = count(jsonObject, map);
                break;
            case "relationCount":
                checkSuccessNumber = relationCount(jsonObject, map);
                break;
        }
        JSONObject result = jsonObject.getJSONObject("result");
        if (result != null && result.size() > 0) {
            if (checkSuccessNumber > 0) {
                baseParameter.setAttr1(result.getString("success"));
            }
            else {
                baseParameter.setAttr1(result.getString("fail"));
            }
        }
        else {
            baseParameter.setAttr1("" + checkSuccessNumber);
        }
        return baseParameter;
    }

    private int relationCount(JSONObject jsonObject, Map<Integer, List<QaStandardRecordItem>> map) {
        int count = 0;
        JSONObject object = jsonObject.getJSONObject("validate");
        String paramId = object.getString("paramId");
        String[] paramRes = object.getObject("paramRes", String[].class);
        List<String> paramResList = Arrays.asList(paramRes);
        Boolean status = object.getBoolean("status");
        if (status) {
            // paramRes满足条件时检查 relationParameter
            for (Integer integer : map.keySet()) {
                List<QaStandardRecordItem> recordItemList = map.get(integer);
                // defaultParameter
                boolean defaultParameterCheck = isParamListCheck(recordItemList, object.getJSONArray("defaultParameter"));
                if (!defaultParameterCheck) {
                    continue;
                }
                boolean checkBrother = false;
                for (QaStandardRecordItem item : recordItemList) {
                    if (item.getParameterId().equals(paramId) && paramResList.contains(item.getParameterValue())) {
                        checkBrother = true;
                        break;
                    }
                }
                boolean paramListCheck = true;
                if (checkBrother) {
                    //relationParameter
                    paramListCheck = isParamListCheck(recordItemList, object.getJSONArray("relationParameter"));
                }
                if (paramListCheck) {
                    count++;
                }
            }
        }
        else {
            count = map.size();
        }
        return count;
    }

    private boolean isParamListCheck(List<QaStandardRecordItem> recordItemList, JSONArray jsonArray) {
        boolean paramListCheck = true;
        for (Object o1 : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) o1;
            String id = jsonObject1.getString("id");
            String checkType = jsonObject1.getJSONObject("rule").getString("type");
            boolean paramCheck = false;
            if ("notEmpty".equals(checkType)) {
                for (QaStandardRecordItem item : recordItemList) {
                    if (item.getParameterId().equals(id) && !StringUtils.isEmpty(item.getParameterValue())) {
                        paramCheck = true;
                        break;
                    }
                }
            }
            else {
                paramCheck = true;
            }
            if (!paramCheck) {
                paramListCheck = false;
                break;
            }
        }
        return paramListCheck;
    }

    // 统计验证通过的数目
    private Integer count(JSONObject jsonObject, Map<Integer, List<QaStandardRecordItem>> map) {
        Integer checkSuccessNumber = 0;
        JSONObject validate = jsonObject.getJSONObject("validate");
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            List<QaStandardRecordItem> itemList = map.get(iterator.next());
            // check
            boolean check = true;
            for (QaStandardRecordItem item : itemList) {
                BaseParameter parameter = item.getParameter();
                if (Integer.valueOf(parameter.getAttr2()) != 0 && StringUtils.isEmpty(item.getParameterValue())) {
                    check = false;
                    break;
                }
                if (validate != null && validate.containsKey(parameter.getId())) {
                    List<String> strings = validate.getObject(parameter.getId(), List.class);
                    if (!strings.contains(item.getParameterValue())) {
                        check = false;
                        break;
                    }
                }
            }
            if (check) {
                checkSuccessNumber++;
            }
        }
        return checkSuccessNumber;
    }

    // 用户基本信息数据校验
    private Message checkUserInfo(List<QaStandardRecordItem> recordItems,
            List<BaseParameter> baseParameters, String groupId) {
        if (recordItems == null || recordItems.size() == 0) {
            return Message.success();
        }
        BaseParameterGroup baseParameterGroup = baseParameterGroupService.find(groupId);
        for (QaStandardRecordItem item : recordItems) {
            //            BaseParameter baseParameter = baseParameterService.find(item.getParameterId());
            BaseParameter baseParameter = baseParameters.parallelStream()
                    .filter(param -> param.getId().equals(item.getParameterId()
                    )).findFirst().get();
            if ("1".equals(baseParameter.getAttr2())) {
                if (StringUtils.isEmpty(item.getParameterValue()) || "null".equals(item.getParameterValue())) {
                    return Message.error(baseParameterGroup.getName() + "-" + baseParameter.getName() + "未填写！");
                }
                if (baseParameter.getTypeValidate().contains("|")) {
                    if ("".equals(item.getParameterValue().split("-")[0])) {
                        return Message.error(baseParameterGroup.getName() + "-" + baseParameter.getName() + "未选择开始时间");
                    }
                }
            }
        }
        return Message.success();
    }

    private void deleteByGroupId(String[] groupIds, String recordId) {
        mapper.deleteByGroupId(groupIds, recordId);
    }

    private String formatDate(String validate, String dateStr) {
        SimpleDateFormat dateFormat = null;
        Date date = null;
        try {
            dateFormat = new SimpleDateFormat(validate);
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            dateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD);
            try {
                date = dateFormat.parse(dateStr);
            } catch (ParseException e1) {
                log.error("日期解析异常");
            }
        }
        return DateUtil.dateFormat(validate, date);
    }

    /**
     * 数据录入删除录入项数据
     */
    @Override
    public void deleteByRecordIdAndparamId(String groupId, String parameterId,
            String recordId, Integer order) {
        mapper.deleteByRecordIdAndparamId(groupId, parameterId, recordId, order);
    }

    /**
     * 批量更新
     * @return
     */
    @Override
    public int batchInsert(List<QaStandardRecordItem> items) {
        return mapper.batchInsert(items);
    }

    @Override
    public Message uploadStandardRecordFile(MultipartFile[] multipartFiles,
            String userId, String groupId, String parameterId, String orders) {

        if (!ObjectUtils.isEmpty(multipartFiles)) {
            List<Map<String, String>> urls = new ArrayList<>(multipartFiles.length);
            for (MultipartFile multipartFile : multipartFiles) {
                if (!FileUtils.checkFileSize(multipartFile.getSize(), 100, "M")) {
                    return Message.error("上传文件大小不能超过100MB");
                }
                Map<String, String> map = new HashMap<>();
                Message message = FileUtils.saveFile(multipartFile, filePath, userId,
                        groupId, parameterId, orders);
                if (message.isSuccess()) {
                    map.put("url", message.getData().toString());
                    map.put("groupId", groupId);
                    map.put("parameterId", parameterId);
                    map.put("orders", orders);
                    urls.add(map);
                }
                else {
                    return Message.error(multipartFile.getOriginalFilename()
                            + message.getMessage());
                }
            }
            return Message.success(urls);
        }
        else {
            return Message.error("文件为空");
        }
    }

    /**
     * 用户基本信息图片上传
     * @return
     */
    @Override
    public Message uploadUserImg(MultipartFile multipartFile, String userId) {
        if (multipartFile == null) {
            return Message.error("文件为空");
        }
        List<String> imgRule = Arrays.asList(".jpg", ".jpeg", ".gif", ".png",
                ".JPG", ".JPEG", ".GIF", ".PNG");
        String fileName = multipartFile.getOriginalFilename();
        if (fileName != null && fileName.contains(".")) {
            String fileType = fileName.substring(fileName.lastIndexOf("."),
                    fileName.length());
            if (!imgRule.contains(fileType)) {
                return Message.error("上传的文件不是图片文件");
            }
            else {
                return FileUtils.saveFile(multipartFile, imgPath, userId);
            }
        }
        else {
            return Message.error("文件类型错误");
        }
    }

    /**
     * 用户提交数据
     * @param userId 用户id
     * @return
     */
    @Override
    public Message commitRecord(String userId) {
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService
                .findList(Collections.singletonList(Filter.eq("userId", userId)), null);
        QaStandardRecord qaStandardRecord;
        if (ObjectUtils.isEmpty(qaStandardRecords)) {
            return Message.error("您缺少基本信息数据，请先录入");
        }
        else {
            qaStandardRecord = qaStandardRecords.get(0);
        }
        if (count(Filter.eq("parameterGroupId", "10100"), Filter.eq
                ("recordId", qaStandardRecord.getId())) == 0) {
            return Message.error("您缺少基本信息数据，请先录入");
        }

        List<QaStandardRecordItem> blankItems = findList(Integer.MAX_VALUE, Collections.singletonList(Filter.eq("recordId", qaStandardRecord.getId())), null);

        // 参加军以上重大军事行动或部队活动信息->表彰奖励情况->施奖单位 验证逻辑
        List<QaStandardRecordItem> list_006 = new ArrayList<>();
        List<QaStandardRecordItem> list_007 = new ArrayList<>();
        for (QaStandardRecordItem item : blankItems) {
            if (item.getParameterId().equals("12100-006")) {
                if (!StringUtils.isEmpty(item.getParameterValue()) && !item.getParameterValue().equals("无")) {
                    list_006.add(item);
                }
            }
            if (item.getParameterId().equals("12100-007")) {
                list_007.add(item);
            }
        }
        for (QaStandardRecordItem item : list_006) {
            for (QaStandardRecordItem qaStandardRecordItem : list_007) {
                if (item.getOrders() == qaStandardRecordItem.getOrders()) {
                    if (StringUtils.isEmpty(qaStandardRecordItem.getParameterValue())) {
                        // 验证失败
                        BaseParameterGroup baseParameterGroup = baseParameterGroupService.find("12100");
                        BaseParameter baseParameter = baseParameterService.find("12100-007");
                        return Message.error(baseParameterGroup.getName() + "-" + baseParameter.getName() + "未填写！");
                    }
                }
            }
        }

        // 5-10500-006-003 是否被评优/被抽查  选择 否 以后， 后面三个字段（ 评优/抽查年度   抽查/评优单位   抽查/评选结果）不需要填写
        List<QaStandardRecordItem> list_510500006003 = new ArrayList<>();
        List<QaStandardRecordItem> list_510500006003_relation = new ArrayList<>();
        List<String> list = Arrays.asList("5-10500-006-004", "5-10500-006-005", "5-10500-006-006");
        List<Integer> ordersList = new ArrayList<>();
        for (QaStandardRecordItem item : blankItems) {
            if (item.getParameterId().equals("5-10500-006-003")) {
                if (!StringUtils.isEmpty(item.getParameterValue()) && !item.getParameterValue().equals("否")) {
                    list_510500006003.add(item);
                    ordersList.add(item.getOrders());
                }
            }
            if (list.contains(item.getParameterId())) {
                list_510500006003_relation.add(item);
            }
        }
        for (QaStandardRecordItem item : list_510500006003_relation) {
            if (ordersList.contains(item.getOrders())) {
                if (StringUtils.isEmpty(item.getParameterValue())) {
                    // 验证失败
                    BaseParameterGroup baseParameterGroup = baseParameterGroupService.find("5-10500-006");
                    BaseParameter baseParameter = baseParameterService.find(item.getParameterId());
                    return Message.error(baseParameterGroup.getName() + "-" + baseParameter.getName() + "未填写！");
                }
            }
        }

        for (QaStandardRecordItem item : blankItems) {
            BaseParameterGroup baseParameterGroup = baseParameterGroupService
                    .find(item.getParameterGroupId());
            BaseParameter baseParameter = baseParameterService.find(item
                    .getParameterId());
            if (baseParameter != null) {
                if ("1".equals(baseParameter.getAttr2())) {
                    if ("".equals(item.getParameterValue())) {
                        return Message.error(baseParameterGroup.getName()
                                + "-" + baseParameter.getName() + "未填写！");
                    }
                    if (baseParameter.getTypeValidate().contains("|")) {
                        if ("".equals(item.getParameterValue().split("-")[0])) {
                            return Message.error(baseParameterGroup.getName()
                                    + "-" + baseParameter.getName() + "未选择开始时间");
                        }
                    }
                }
            }
        }
        //        }
        qaStandardRecord.setCommitStatus(true);
        qaStandardRecordService.update(qaStandardRecord);

        /* TODO 目前该代码块功能不使用 */
        if (false) {
            QaStandardRecordCommitted qaStandardRecordCommitted = new QaStandardRecordCommitted();
            ClassTransferUtil.cloneAttribute(qaStandardRecord, qaStandardRecordCommitted);
            if (qaStandardRecordCommittedService.exists(qaStandardRecord.getId())) {
                qaStandardRecordCommittedService.update(qaStandardRecordCommitted);
            } else {
                qaStandardRecordCommittedService.save(qaStandardRecordCommitted);
            }
            int o = qaStandardRecordItemCommittedService.count(Filter.eq("recordId", qaStandardRecord.getId()));
            if (o > 0) {
                qaStandardRecordItemCommittedService.batchUpdate(blankItems);
            } else {
                qaStandardRecordItemCommittedService.batchInsert(blankItems);
            }
        }
        return Message.success("提交成功");
    }

    @Override
    public List<QaStandardRecordItem> findItemsByRecordId(String recordId) {
        return mapper.findItemsByRecordId(recordId);
    }
}