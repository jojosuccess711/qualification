/**
 * @filename:QaCategoryServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.service.impl;

import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.Order;
import com.bdjbd.common.util.ClassUtil;
import com.bdjbd.common.util.ListUtil;
import com.bdjbd.dao.entity.QaAcademicRecord;
import com.bdjbd.dao.entity.QaClause;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.enums.AcademicTypeEnum;
import com.bdjbd.service.QaAcademicRecordService;
import com.bdjbd.service.QaClauseService;
import com.bdjbd.service.QaStandardRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaCategory;
import com.bdjbd.dao.mapper.QaCategoryMapper;
import com.bdjbd.service.QaCategoryService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 评审分类 —— SERVICEIMPL
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 */
@Slf4j
@Service
public class QaCategoryServiceImpl extends BaseServiceImpl<QaCategory, String> implements QaCategoryService {

    @Autowired
    private QaCategoryMapper mapper;

    @Autowired
    private QaClauseService qaClauseService;
    @Autowired
    QaAcademicRecordService qaAcademicRecordService;
    @Autowired
    QaStandardRecordService qaStandardRecordService;

    @Autowired
    public void setBaseDao(QaCategoryMapper mapper) {
        super.setBaseDao(mapper);
    }

    @Override
    public List<Map<String, Object>> findListApplyNumber(Integer status) {
        return mapper.findListApplyNumber(status);
    }

    /**
     * 变更申请总人数
     */
    @Override
    public void updateApplyNum(String categoryId, String categoryChildId, String academicTitle) {
        QaCategory category = this.find(categoryId);
        if (category != null) {
            category.setApplyNum(category.getApplyNum() + 1);
            this.update(category);
        }
        QaCategory childCategory = this.find(categoryChildId);
        if (childCategory != null) {
            childCategory.setApplyNum(childCategory.getApplyNum() + 1);
            this.update(childCategory);
        }
        QaCategory academicCategory = this.find(academicTitle);
        if (academicCategory != null) {
            academicCategory.setApplyNum(academicCategory.getApplyNum() + 1);
            this.update(academicCategory);
        }
    }

    /**
     * 变更初审通过人数
     */
    @Override
    public void updateFirstApproveNum(String categoryId, String categoryChildId, String academicTitle) {
        QaCategory category = this.find(categoryId);
        if (category != null) {
            category.setFirstApproveNum(category.getFirstApproveNum() + 1);
            this.update(category);
        }
        QaCategory childCategory = this.find(categoryChildId);
        if (childCategory != null) {
            childCategory.setFirstApproveNum(childCategory.getFirstApproveNum() + 1);
            this.update(childCategory);
        }
        QaCategory academicCategory = this.find(academicTitle);
        if (academicCategory != null) {
            academicCategory.setFirstApproveNum(academicCategory.getFirstApproveNum() + 1);
            this.update(academicCategory);
        }
    }

    /**
     * 变更审核通过人数
     */
    @Override
    public void updateApproveNum(String categoryId, String categoryChildId, String academicTitle) {
        QaCategory category = this.find(categoryId);
        if (category != null) {
            category.setApproveNum(category.getApproveNum() + 1);
            this.update(category);
        }
        QaCategory childCategory = this.find(categoryChildId);
        if (childCategory != null) {
            childCategory.setApproveNum(childCategory.getApproveNum() + 1);
            this.update(childCategory);
        }
        QaCategory academicCategory = this.find(academicTitle);
        if (academicCategory != null) {
            academicCategory.setApproveNum(academicCategory.getApproveNum() + 1);
            this.update(academicCategory);
        }
    }

    @Override
    public List<QaCategory> findListDetails() {
        return mapper.findListDetails();
    }

    @Override
    public QaCategory findDetailsById(String id) {
        return mapper.findDetailsById(id);
    }

    /**
     * 根据领域ID和查询type获取下属所有数据集合
     *
     * @return
     */
    @Override
    public List<QaCategory> findListByCategory(String categoryId, Integer type) {
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("orders"));
        List<QaCategory> list = this.findList(ListUtil.addToList(Filter.like("parent", categoryId), Filter.eq("type", type)), orders);
        return list;
    }

    /**
     * 根据领域ID 集合和type获取下属所有数据集合
     *
     * @return
     */
    @Override
    public List<Long> findListByCategoryList(List<Long> categoryIdList, Integer type) {
        List<Long> academicList = new ArrayList<>();
        for (long categoryId : categoryIdList) {
            List<QaCategory> list = this.findListByCategory(Long.toString(categoryId), type);
            if (list != null && !list.isEmpty()) {
                for (QaCategory category : list) {
                    academicList.add(Long.parseLong(category.getId()));
                }
            }
        }
        return academicList;
    }

    @Override
    public List<QaCategory> findListDetails(boolean hasClause) {
        List<QaCategory> listDetails = new ArrayList<>();
        List<QaCategory> all = this.findAll();
        for (QaCategory qaCategory : all) {
            if (qaCategory.getType() == 0) {
                listDetails.add(qaCategory);
            }
        }
        for (QaCategory listDetail : listDetails) {
            List<QaCategory> childList = new ArrayList<>();
            for (QaCategory qaCategory : all) {
                if (qaCategory.getParent() != null && listDetail.getId().equals(qaCategory.getParent())) {
                    childList.add(qaCategory);
                }
            }
            for (QaCategory qaCategory : childList) {
                List<QaCategory> subChildList = new ArrayList<>();
                for (QaCategory category : all) {
                    if (category.getParent() != null && category.getType() == 2 && category.getParent().split(",")[1].equals(qaCategory.getId())) {
                        subChildList.add(category);
                    }
                }
                qaCategory.setChildList(subChildList);
            }
            listDetail.setChildList(childList);
        }

        if (hasClause) {
            for (QaCategory qaCategory : listDetails) {
                List<String> categoryIds = new ArrayList<>();
                for (QaCategory category : qaCategory.getChildList()) {
                    for (QaCategory qaCategory1 : category.getChildList()) {
                        categoryIds.add(qaCategory1.getId());
                    }
                }
                if (categoryIds.size() == 0) {
                    qaCategory.setQaClauseList(new ArrayList<QaClause>());
                } else {
                    List<QaClause> list = qaClauseService.findByCategory(categoryIds);
                    qaCategory.setQaClauseList(list);
                }

            }
        }
        return listDetails;
    }

    /**
     * 申请参评使用
     *
     * @param hasClause 是否包含条件
     * @return
     */
    @Override
    public List<QaCategory> findListDetails1(boolean hasClause, String userId) {
        // 查询用户当前职称
        List<QaStandardRecord> qaStandardRecordList = qaStandardRecordService.findList(Arrays.asList(Filter.eq("userId", userId)), null);
        if (!CollectionUtils.isEmpty(qaStandardRecordList)) {
            QaStandardRecord qaStandardRecord = qaStandardRecordList.get(0);
            List<QaCategory> listDetails = new ArrayList<>();
            List<QaCategory> all = this.findAll();
            //type == 1的所有类型
            for (QaCategory qaCategory : all) {
                if (qaCategory.getType() == 1) {
                    listDetails.add(qaCategory);
                }
            }
            for (QaCategory listDetail : listDetails) {
                List<QaCategory> childList = new ArrayList<>();
                for (QaCategory category : all) {
                    if (category.getParent() != null && category.getType() == 2 && category.getParent().split(",")[1].equals(listDetail.getId())) {
                        if (qaStandardRecord.getTechnologyCategory().substring(0, 2).equals(category.getAnotherName())) {
                            category.setName("续任" + category.getName());
                        } else {
                            category.setName("晋升" + category.getName());
                        }
                        childList.add(category);
                    }
                }
                listDetail.setChildList(childList);
            }
            return listDetails;
        }
        return null;
    }

    /**
     * 通过职称关联岗位二级下拉数据
     *
     * @param categoryTitle 职称名称
     * @return
     */
    @Cacheable("findCategoryTypeByTitle")
    @Override
    public Message findCategoryTypeByTitle(String categoryTitle) {

        List<QaCategory> qaCategories = findList(Collections.singletonList(Filter.eq
                ("name", categoryTitle)), null);
        Set<String> parentSet = new HashSet<>();
        for (QaCategory qaCategory : qaCategories) {
            String[] strings = qaCategory.getParent().split(",");
            if (strings.length > 0) {
                parentSet.addAll(Arrays.asList(strings));
            }
        }
        if (parentSet.size() == 0) {
            return Message.error("当前职称未找到相关的岗位信息");
        }
        String[] parents = parentSet.toArray(new String[parentSet.size()]);
        List<QaCategory> result = findList(parents);
        result = result.parallelStream().filter(item -> !StringUtils.isEmpty(item
                .getParent())).collect(Collectors.toList());
        List<Map<?, ?>> list = ClassUtil.listBeanToMap(false, result, "id", "name");
        return Message.success(list);
    }

    /**
     * 职称评审各类人员数量饼图数据
     *
     * @return
     */
    @Override
    public Message<?> findCategorys() {
        //职称分类配置数据
        List<QaCategory> categories = findListDetails(false);
//        //职称提交审核数据
//        List<QaAcademicRecord> academicRecords = qaAcademicRecordService
//                .findAll();
//        //筛选出已完成电脑审核的数据
//        academicRecords = academicRecords.parallelStream().filter(item -> 1 == item
//                .getFirstCheckStatus()).collect(Collectors.toList());
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService
                .findAll();
        for (QaCategory qaCategory : categories) {
            long categoryCount = qaStandardRecords.parallelStream().filter
                    (item -> qaCategory.getId().equals(item
                            .getAttr1()) && item.getCommitStatus()).count();
            qaCategory.setFirstApproveCount(categoryCount);
            for (QaCategory childCategory : qaCategory.getChildList()) {
                long childCategoryCount = qaStandardRecords.parallelStream().filter
                        (item -> childCategory.getId().equals(item
                                .getAttr2()) && item.getCommitStatus()).count();
                childCategory.setFirstApproveCount(childCategoryCount);
                for (QaCategory title : childCategory.getChildList()) {
                    long titleCount = qaStandardRecords.parallelStream().filter
                            (item -> title.getId().equals(item
                                    .getAttr3()) && item.getCommitStatus()).count();
                    childCategory.setFirstApproveCount(childCategoryCount);
                    title.setFirstApproveCount(titleCount);
                }
            }
        }
        return Message.success(categories);
    }

    /**
     * 获取五大类人员占比
     *
     * @return
     */
    @Override
    public Message<?> findCategorysRate() {
        //五大类领域信息集合
        List<QaCategory> categories = findListDetails();
//        //职称提交已通过电脑审核数据
//        List<QaAcademicRecord> academicRecords = qaAcademicRecordService
//                .findList(10000, Collections.singletonList(Filter.eq
//                                ("firstCheckStatus", 1)),
//                        null);
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService
                .findAll();
        for (QaCategory qaCategory : categories) {
            long count = qaStandardRecords.parallelStream().filter
                    (item -> qaCategory.getId()
                            .equals(item.getAttr1()) && item.getCommitStatus()).count();
            qaCategory.setFirstApproveCount(count);
        }
        List<Map<?, ?>> list = ClassUtil.listBeanToMap(false, categories, "id",
                "name", "firstApproveCount");
        return Message.success(list);
    }

    /**
     * 获取五大类人员职称占比
     *
     * @return
     */
    @Override
    public Message<?> findCategoryTitlesRate() {
        //五大类领域信息集合
        List<QaCategory> categories = findList(Collections.singletonList
                (Filter.eq("type", 2)), null);
        //职称提交已通过电脑审核数据
//        List<QaAcademicRecord> academicRecords = qaAcademicRecordService
//                .findList(10000, Collections.singletonList(Filter.eq
//                                ("firstCheckStatus", 1)),
//                        null);
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService
                .findAll();
        //职称名称集合
        Set<String> titleNames = categories.parallelStream().collect
                (Collectors.groupingBy(QaCategory::getAnotherName)).keySet();
        List<Map<String, Object>> result = new ArrayList<>();
        for (String title : titleNames) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", title);
            Set<String> titleIds = categories.parallelStream().filter
                    (item -> title.equals(item.getAnotherName())).map
                    (QaCategory::getId).collect(Collectors.toSet());
            long count = qaStandardRecords.parallelStream().filter
                    (item -> titleIds.contains(item.getAttr3()) && item.getCommitStatus()).count();
            map.put("firstApproveCount", count);
            result.add(map);
        }
        return Message.success(result);
    }

    /**
     * 获取17小类人员占比
     *
     * @return
     */
    @Override
    public Message<?> findCategoryTypesRate() {
        //五大类领域信息集合
        List<QaCategory> categories = findList(Collections.singletonList
                (Filter.eq("type", 1)), null);
//        //职称提交已通过电脑审核数据
//        List<QaAcademicRecord> academicRecords = qaAcademicRecordService
//                .findList(10000, Collections.singletonList(Filter.eq
//                                ("firstCheckStatus", 1)),
//                        null);
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService
                .findAll();
        for (QaCategory qaCategory : categories) {
            long count = qaStandardRecords.parallelStream().filter
                    (item -> qaCategory.getId().equals(item.getAttr2()) && item.getCommitStatus())
                    .count();
            qaCategory.setFirstApproveCount(count);
        }
        List<Map<?, ?>> list = ClassUtil.listBeanToMap(false, categories, "id",
                "name", "firstApproveCount");
        return Message.success(list);
    }

    /**
     * 获取模拟参评人员占比
     *
     * @return
     */
    @Override
    public Message<?> findRecordsRate() {
        List<QaAcademicRecord> academicRecords = qaAcademicRecordService.findAll();
        int recordCount = academicRecords.parallelStream().filter
                (item -> item.getHasUserInput() != null && item.getHasUserInput())
                .collect(Collectors.groupingBy(QaAcademicRecord::getUserId)).size();
        List<Map<String, Object>> list = new ArrayList<>(2);
        Map<String, Object> recordMap = new HashMap<>(2);
        Map<String, Object> titleMap = new HashMap<>(2);
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService
                .findAll();
        recordMap.put("name", "申请参评人员数量统计");
        recordMap.put("count", recordCount);
        list.add(recordMap);
        titleMap.put("name", "现任职称人员数量统计");
        int count = qaStandardRecords.parallelStream().filter
                (item -> item != null && item.getCommitStatus()).collect(Collectors
                .toList()).size();
        titleMap.put("count", count);

        list.add(titleMap);
        return Message.success(list);
    }

    /**
     * 通过领域、分类、职称名称获取职称id
     *
     * @param category    领域
     * @param subCategory 分类
     * @param jobTitle    职称
     * @return
     */
    @Override
    public List<QaCategory> findTitles(String category, String subCategory, String jobTitle) {
        return mapper.findTitles(category, subCategory, jobTitle);
    }

    /**
     * 获取职称名称列表
     *
     * @return
     */
    @Override
    public Message findTitles() {
        List<QaCategory> qaCategories = findList(
                Collections.singletonList(Filter.eq("type", 2)), Collections
                        .singletonList(Order.desc("id")));
        List<String> titles = qaCategories.parallelStream().map
                (QaCategory::getAnotherName).distinct().collect(Collectors.toList());
        return Message.success(titles);
    }

    @Override
    public Message findCategoryTypes() {
        List<QaCategory> qaCategories = findList(
                Collections.singletonList(Filter.eq("type", 1)), Collections
                        .singletonList(Order.desc("id")));
        List<String> categoryTypes = qaCategories.parallelStream().map
                (QaCategory::getName).distinct().collect(Collectors.toList());
        return Message.success(categoryTypes);
    }

    /**
     * 根据职称名称联动拟参评职称等级
     *
     * @return
     */
    @Override
    public Message findAcademicType(String titleName) {
        List<String> list = new ArrayList<>();
        switch (titleName) {
            case "初职":
                list.add(AcademicTypeEnum.ACADEMIC_TYPE_1.getValue());
                list.add(AcademicTypeEnum.ACADEMIC_TYPE_2.getValue());
                break;
            case "中职":
                list.add(AcademicTypeEnum.ACADEMIC_TYPE_3.getValue());
                list.add(AcademicTypeEnum.ACADEMIC_TYPE_4.getValue());
                break;
            case "副高":
                list.add(AcademicTypeEnum.ACADEMIC_TYPE_5.getValue());
                list.add(AcademicTypeEnum.ACADEMIC_TYPE_6.getValue());
                break;
            case "正高":
                list.add(AcademicTypeEnum.ACADEMIC_TYPE_7.getValue());
                break;
        }
        return Message.success(list);
    }
}