/**
 * @filename:QaAcademicRecordServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.Order;
import com.bdjbd.bo.Category;
import com.bdjbd.bo.ClauseCategory;
import com.bdjbd.bo.MapperClause;
import com.bdjbd.common.util.UUIDUtils;
import com.bdjbd.dao.entity.*;
import com.bdjbd.service.*;
import com.bdjbd.dao.entity.QaAcademicRecordItem;
import com.bdjbd.dao.entity.QaAcademicRecordLog;
import com.bdjbd.dao.entity.QaCategory;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.enums.AcademicTypeEnum;
import com.bdjbd.service.QaAcademicRecordItemService;
import com.bdjbd.service.QaAcademicRecordLogService;
import com.bdjbd.service.QaBaseClauseItemService;
import com.bdjbd.service.QaCategoryService;
import com.bdjbd.service.QaStandardRecordService;
import com.bdjbd.service.common.RedisService;
import com.bdjbd.service.matches.DataCategoryHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.mapper.QaAcademicRecordMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @Description: 职称评审记录 —— SERVICEIMPL
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Slf4j
@Service
public class QaAcademicRecordServiceImpl extends BaseServiceImpl<QaAcademicRecord, String> implements QaAcademicRecordService {

    @Autowired
    private QaAcademicRecordMapper mapper;

    @Autowired
    private QaAcademicRecordLogService qaAcademicRecordLogService;
    @Autowired
    private QaCategoryService qaCategoryService;
    @Autowired
    QaStandardRecordService qaStandardRecordService;
    @Autowired
    QaBaseClauseItemService qaBaseClauseItemService;
    @Autowired
    DataCategoryHandlerService dataCategoryHandlerService;
    @Autowired
    QaAcademicRecordItemService qaAcademicRecordItemService;
    @Autowired
    RedisService redisService;

    @Autowired
    public void setBaseDao(QaAcademicRecordMapper mapper) {
        super.setBaseDao(mapper);
    }

    @Transactional
    @Override
    public Integer saveRecord(QaAcademicRecord entity) {
        this.save(entity);
        QaAcademicRecordLog recordLog = getQaAcademicRecordLog(entity);
        qaAcademicRecordLogService.save(recordLog);
        return 1;
    }

    /**
     * 电脑审核通过职称详情信息
     * @param pageNum 页码
     * @param pageSize 分页大小
     * @param category 领域
     * @param subCategory 分类
     * @param userName 用户姓名
     * @param jobTitle 职称
     * @return
     */
    @Override
    public Message findRecords(Integer pageNum, Integer pageSize, String
            category, String subCategory, String userName, String jobTitle) {
        //获取领域id
        List<QaCategory> qaCategory = qaCategoryService.findList( Collections
                .singletonList(Filter.eq("name", category)), null);
        if (ObjectUtils.isEmpty(qaCategory) || StringUtils.isEmpty(category)) {
            category = null;
        }
        else {
            category = qaCategory.get(0).getId();
        }
        //获取分类id
        List<QaCategory> subQaCategory = qaCategoryService.findList(
                Collections.singletonList(Filter.eq("name", subCategory)), null);
        if (StringUtils.isEmpty(subCategory) || ObjectUtils.isEmpty(subQaCategory)) {
            subCategory = null;
        }
        else {
            subCategory = subQaCategory.get(0).getId();
        }
        if (StringUtils.isEmpty(jobTitle)) {
            jobTitle = null;
        }
        //职称id
        List<QaCategory> titles = qaCategoryService.findTitles(category, subCategory, jobTitle);
        List<String> titleIds = titles.parallelStream().map
                (QaCategory::getId).collect(Collectors.toList());

        //通过用户姓名获取id
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService
                .findList(
                        Collections.singletonList(Filter.eq("name", userName)), null);
        String userId = null;
        if (!ObjectUtils.isEmpty(qaStandardRecords) || !StringUtils.isEmpty(userName)) {
            userId = qaStandardRecords.get(0).getUserId();
        }
        Map<String, Object> resultMap = new HashMap<>();
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("curCategoryId", category));
        filters.add(Filter.eq("curCategoryChildId", subCategory));
        filters.add(Filter.eq("userId", userId));
        filters.add(Filter.in("curAcademicId", titleIds));
        filters.add(Filter.eq("firstCheckStatus", 1));
        List<QaAcademicRecord> qaAcademicRecords = findList((pageNum - 1) * pageSize, pageSize,
                filters, Collections.singletonList(Order.asc("createDate")));
        int total = count(filters.toArray(new Filter[0]));
        getAcademicData(qaAcademicRecords);
        resultMap.put("pageNum", pageNum);
        resultMap.put("pageSize", pageSize);
        resultMap.put("total", total);
        resultMap.put("data", qaAcademicRecords);
        return Message.success(resultMap);
    }

    //    @Transactional
    @Override
    public Integer deleteByUser(String userId) {
        qaAcademicRecordItemService.deleteByUser(userId);
        return mapper.deleteByUser(userId);
    }

    //    @Transactional
    @Override
    public Integer batchInsert(List<QaAcademicRecord> list) {
        List<QaAcademicRecordLog> logList = new ArrayList<>();
        for (QaAcademicRecord qaAcademicRecord : list) {
            QaAcademicRecordLog recordLog = getQaAcademicRecordLog(qaAcademicRecord);
            logList.add(recordLog);
        }
        qaAcademicRecordLogService.batchSave(logList);
        return mapper.batchInsert(list);
    }

    private QaAcademicRecordLog getQaAcademicRecordLog(QaAcademicRecord qaAcademicRecord) {
        QaAcademicRecordLog recordLog = new QaAcademicRecordLog();
        recordLog.setId(UUIDUtils.generateUuid());
        recordLog.setCreateDate(new Date());
        recordLog.setModifyDate(new Date());
        recordLog.setAcademicRecordId(qaAcademicRecord.getId());
        recordLog.setOperatorId(qaAcademicRecord.getUserId());
        recordLog.setOperator(qaAcademicRecord.getUserName());
        recordLog.setType(1);
        return recordLog;
    }

    /**
     * 拟参评符合审核条件的人员职称分类统计占比
     * @return
     */
    @Override
    public Message findRecordsRate(String curAcademicName, String recordTitle,
            String categoryType, String userName) {
        List<Filter> filters = getFilters(curAcademicName, recordTitle,
                categoryType, true, null, userName);
        Integer titleCount = qaStandardRecordService.count(Filter.like
                ("technologyCategory",
                        curAcademicName));
        //五大类领域信息集合
        List<QaCategory> categories = qaCategoryService.findList(
                Collections.singletonList(Filter.eq("type", 0)), null);
        List<QaAcademicRecord> qaAcademicRecords = findList( filters, null);
        int totalCount = qaAcademicRecords.parallelStream().collect(Collectors
                .groupingBy
                        (QaAcademicRecord::getUserId)).size();
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> result = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00");
        for (QaCategory qaCategory : categories) {
            Map<String, Object> map = new HashMap<>();
            int count = qaAcademicRecords.parallelStream().filter
                    (item -> qaCategory.getId().equals(item.getCategoryId()))
                    .collect(Collectors.groupingBy
                            (QaAcademicRecord::getUserId)).size();
            map.put("count", count);
            map.put("name", qaCategory.getName());
            map.put("rate", df.format(totalCount == 0 ? 0.00F : (float) count / totalCount));
            result.add(map);
        }
        resultMap.put("totalCount", totalCount);
        resultMap.put("data", result);
        resultMap.put("totalPersonCount", titleCount);
        resultMap.put("totalRate", df.format(titleCount == 0 ? 0.00F : (float)
                totalCount / titleCount));
        return Message.success(resultMap);
    }

    /**
     * 拟参评人员列表/拟参评符合、不符合人员列表
     * @param curAcademicName 现任职称
     * @param recordTitle 拟参评职称等级
     * @param categoryType 目标岗位
     * @return
     */
    @Override
    public Message findRecordsList(Integer pageNum, Integer pageSize, String
            curAcademicName, String
            recordTitle, String categoryType, Boolean hasPass, String
            organization, String userName) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Filter> filters = getFilters(curAcademicName, recordTitle,
                categoryType, hasPass, organization, userName);
        if ((!StringUtils.isEmpty(organization) || !StringUtils.isEmpty(userName))
                && filters.parallelStream().noneMatch(item -> "userId".equals(item
                .getProperty()))) {
            resultMap.put("pageNum", pageNum);
            resultMap.put("pageSize", pageSize);
            resultMap.put("total", 0);
            resultMap.put("data", new ArrayList<>());
            return Message.success(resultMap);
        }
        List<QaAcademicRecord> qaAcademicRecords = findList((pageNum - 1) * pageSize,
                pageSize, filters,
                Collections.singletonList(Order.asc("createDate")));
        int total = count(filters.toArray(new Filter[0]));
        getAcademicData(qaAcademicRecords);
        resultMap.put("pageNum", pageNum);
        resultMap.put("pageSize", pageSize);
        resultMap.put("total", total);
        resultMap.put("data", qaAcademicRecords);
        return Message.success(resultMap);
    }

    /**
     * 拟参评不符合人员占比
     * @param curAcademicName 现任职称
     * @param recordTitle 拟参评职称
     * @param categoryType 目标岗位
     * @return
     */
    @Override
    public Message findNotPassRate(String curAcademicName, String
            recordTitle, String categoryType, String userName) {
        List<Filter> filters = getFilters(curAcademicName, recordTitle,
                categoryType, false, null, userName);
        String id = filters.parallelStream().filter(item -> "categoryChildId"
                .equals(item.getProperty())).map(Filter::getValue).findFirst().get().toString();
        //通过拟参评职称获取职称名称
        recordTitle = recordTitle.substring(recordTitle.length() - 2);
        //获取岗位id
        List<QaCategory> qaCategorys = qaCategoryService.findList(1, Arrays.asList
                (Filter.eq("anotherName", recordTitle), Filter.like("parent", "," + id)), null);
        DecimalFormat df = new DecimalFormat("0.00%");
        if (ObjectUtils.isEmpty(qaCategorys)) {
            return Message.error("岗位名称不正确");
        }
        else {
            List<Map<String, Object>> list = new ArrayList<>();
            List<QaAcademicRecord> qaAcademicRecords = findList( filters,
                    Collections.singletonList(Order.asc("createDate")));
            long totalCount = qaAcademicRecords.parallelStream().collect(Collectors
                    .groupingBy(QaAcademicRecord::getUserId)).size();
            QaCategory qaCategory = qaCategorys.get(0);
            List<MapperClause> mapperClauseList = qaBaseClauseItemService
                    .findItemsByCategory(qaCategory.getId());
            Category analysis = dataCategoryHandlerService.analysis(qaCategory, mapperClauseList);
            List<ClauseCategory> clauseCategories = analysis.getClauseCategories();
            Map<String, Object> result = new HashMap<>();

            for (ClauseCategory clause : clauseCategories) {
                Map<String, Object> map = new HashMap<>();
                map.put("clauseGroupId", clause.getGroupId());
                map.put("name", clause.getGroupTitle());
                List<String> recordList = qaAcademicRecordItemService.findRecordList(clause.getGroupId(), "0");
                Set<String> set = new HashSet<>(recordList);
                long count = qaAcademicRecords.parallelStream().filter
                        (item -> set.contains(item.getId())).collect(Collectors.groupingBy(QaAcademicRecord::getUserId)).size();
                map.put("count", count);
                list.add(map);
                recordList.clear();
                set.clear();
            }
            result.put("data", list);
            result.put("totalCount", totalCount);
            return Message.success(result);
        }
    }

    /**
     * 构造评审记录数据
     * @param qaAcademicRecords 评审记录数据集合
     */
    private void getAcademicData(List<QaAcademicRecord> qaAcademicRecords) {
        List<QaCategory> qaCategories = qaCategoryService.findAll();
        for (QaAcademicRecord qaAcademicRecord : qaAcademicRecords) {
            qaAcademicRecord.setCategoryName(getCategoryName(qaAcademicRecord
                    .getCategoryId(), false, qaCategories));
            qaAcademicRecord.setCategoryChildName(getCategoryName(qaAcademicRecord
                    .getCategoryChildId(), false, qaCategories));
            qaAcademicRecord.setCategoryAcademicName(getCategoryName(qaAcademicRecord
                    .getCategoryAcademicId(), true, qaCategories));
            qaAcademicRecord.setCurCategoryName(getCategoryName(qaAcademicRecord
                    .getCurCategoryId(), false, qaCategories));
            qaAcademicRecord.setCurCategoryChildName(getCategoryName(qaAcademicRecord
                    .getCurCategoryChildId(), false, qaCategories));
            qaAcademicRecord.setCurAcademicName(getCategoryName(qaAcademicRecord
                    .getCurAcademicId(), true, qaCategories));
        }
    }

    /**
     * 获取职称岗位名称
     * @return
     */
    private String getCategoryName(String categoryId, boolean isTitle, List<QaCategory> qaCategories) {
        String name = "";
        Optional op = qaCategories.parallelStream().filter(item -> item.getId().equals(categoryId)).map(item -> {
            if (isTitle) {
                return item.getAnotherName();
            }
            else {
                return item.getName();
            }
        }).findFirst();
        if (op.isPresent()) {
            name = op.get().toString();
        }
        return name;
    }

    /**
     * @param categoryType 拟参评职称岗位
     * @param curAcademicName 现任职称
     * @param hasPass 是否符合
     * @param recordTitle 拟参评职称
     * 通过条件构造筛选数据
     * @return
     */
    private List<Filter> getFilters(String curAcademicName, String recordTitle,
            String categoryType, Boolean hasPass, String organization, String userName) {
        List<Filter> filters = new ArrayList<>();
        List<Filter> userFilter = new ArrayList<>();
        if (!StringUtils.isEmpty(userName)) {
            userFilter.add(Filter.eq("name", userName));
        }
        if (!StringUtils.isEmpty(organization)) {
            userFilter.add(Filter.eq("attr0", organization));
        }
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService
                .findList(userFilter, null);
        List<String> userIds = qaStandardRecords.parallelStream().map
                (QaStandardRecord::getUserId).collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(userIds)) {
            filters.add(Filter.in("userId", userIds));
        }
        else {
            filters.add(Filter.isNull("userId"));
        }

        //获取岗位id
        List<QaCategory> qaCategorys = qaCategoryService.findList( Collections
                .singletonList(Filter.eq("name", categoryType)), null);
        if (!ObjectUtils.isEmpty(qaCategorys) && !StringUtils.isEmpty
                (categoryType)) {
            categoryType = qaCategorys.get(0).getId();
            filters.add(Filter.eq("categoryChildId", categoryType));
        }
        //获取职称id
        List<QaCategory> titleCategorys = qaCategoryService.findList(
                Collections.singletonList(Filter.eq("anotherName", curAcademicName)), null);
        List<String> titleIds = titleCategorys.parallelStream().map
                (QaCategory::getId).collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(titleIds)) {
            filters.add(Filter.in("curAcademicId", titleIds));
        }
        if (!StringUtils.isEmpty(recordTitle)) {
            int categoryTypeId = AcademicTypeEnum.getByValue(recordTitle).getKey();
            filters.add(Filter.eq("academicType", categoryTypeId));
        }
        if (hasPass == null) {
            return filters;
        }
        //是否通过电脑审核
        if (hasPass) {
            filters.add(Filter.eq("firstCheckStatus", 1));
        }
        else {
            filters.add(Filter.ne("firstCheckStatus", 1));
        }
        return filters;
    }

    /**
     * 获取拟参评不符合人员条件详情
     * @return
     */
    @Override
    public Message findNotPassDetails(String recordId) {
        List<QaAcademicRecordItem> qaAcademicRecordItems = qaAcademicRecordItemService.findNotPassDetails(recordId);
        return Message.success(qaAcademicRecordItems);
    }

    /**
     * 删除用户的拟参评记录和记录项
     */
    //    @Transactional
    @Override
    public synchronized Message deleteAndInsertRecords(String userId,
            List<QaAcademicRecord> qaAcademicRecordList, List<QaAcademicRecordItem> qaAcademicRecordItemList) {
//        log.info("审核结果保存，{}，{}", qaAcademicRecordList.size(), qaAcademicRecordItemList.size());

        log.info("删除用户之前的记录 开始...");
        // 删除用户之前的记录
        deleteByUser(userId);
        log.info("删除用户之前的记录 结束...");

        if (qaAcademicRecordList.size() > 0) {
            batchInsert(qaAcademicRecordList);
        }
        if (qaAcademicRecordItemList.size() > 0) {
            qaAcademicRecordItemService.batchInsert(qaAcademicRecordItemList);
            log.warn("成功！！！！！！！！！！！");
        }

        return Message.success();
    }

    /**
     * 通过缓存获取已提交用户信息
     * @param name
     * @param category
     * @param categoryType
     * @param technologyCategory
     * @param atrr0
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Message findUserCommittedRecords(String name,
            String category, String categoryType, String technologyCategory,
            String atrr0,
            Integer
            pageNum, Integer pageSize) {
        Map map = redisService.getRedisTemplate().opsForHash().entries
                ("COMMITTED_USER_RECORDS");
        List<QaStandardRecord> records = new ArrayList<>();
        for (Object entry : map.entrySet()) {
            QaStandardRecord qaStandardRecord = (QaStandardRecord) ((Map.Entry) entry).getValue();
            records.add(qaStandardRecord);
        }
        Map<String, Object> resultMap = new HashMap<>();
        List<QaStandardRecord> qaStandardRecords = records.stream().filter(item ->
                (StringUtils.isEmpty(name) ||
                        name.equals(item.getName())) &&
                        (StringUtils.isEmpty(category)|| category.equals(item.getCategory())) &&
                        (StringUtils.isEmpty(categoryType) || categoryType.equals(item.getCategoryType()))
                        && (StringUtils.isEmpty(technologyCategory)|| technologyCategory.equals(item.getTechnologyCategory()))
                        && (StringUtils.isEmpty(atrr0)|| atrr0
                        .equals(item.getAttr0())))
                .sorted(Comparator.comparing(QaStandardRecord::getCreateDate)).skip
                        ((pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        resultMap.put("pageNum", pageNum);
        resultMap.put("pageSize", pageSize);
        resultMap.put("total", records.size());
        resultMap.put("data", qaStandardRecords);
        return Message.success(resultMap);
    }

    /**
     * t通过缓存获取用户详情数据
     * @param userId
     * @return
     */
    @Override
    public Message findUserCommittedDetails(String userId) {
        Object items = redisService.getRedisTemplate()
                .opsForHash().get("COMMITTED_RECORD_ITEMS", userId);
        return Message.success(items);
    }
}