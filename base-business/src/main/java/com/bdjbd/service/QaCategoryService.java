/**
 * @filename:QaCategoryService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.Message;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaCategory;

import java.util.List;
import java.util.Map;

/**
 *  
 * @Description:  评审分类 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaCategoryService extends BaseService<QaCategory, String> {

    /**
     * 变更申请总人数
     * @param categoryId
     * @param categoryChildId
     */
    void updateApplyNum(String categoryId, String categoryChildId, String academicTitle);

    /**
     * 变更初审通过人数
     * @param categoryId
     * @param categoryChildId
     */
    void updateFirstApproveNum(String categoryId, String categoryChildId, String academicTitle);

    /**
     * 变更审核通过人数
     * @param categoryId
     * @param categoryChildId
     */
    void updateApproveNum(String categoryId, String categoryChildId, String academicTitle);

    /*各类评审人员数量*/
    List<Map<String, Object>> findListApplyNumber(Integer status);

    List<QaCategory> findListDetails();

    QaCategory findDetailsById(String id);

    /**
     *
     * @param hasClause 是否包含条件
     * @return
     */
    List<QaCategory> findListDetails(boolean hasClause);

    /**
     * 申请参评使用
     * @param hasClause 是否包含条件
     * @return
     */
    List<QaCategory> findListDetails1(boolean hasClause, String userId);

    /**
     * 根据领域ID和type获取下属所有数据集合
     * @param categoryId
     * @param type
     * @return
     */
    List<QaCategory> findListByCategory(String categoryId, Integer type);

    /**
     * 根据领域ID 集合和type获取下属所有数据集合
     * @param categoryIdList
     * @param type
     * @return
     */
    List<Long> findListByCategoryList(List<Long> categoryIdList, Integer type);

    /**
     * 通过职称关联岗位二级下拉数据
     * @param categoryTitle 职称名称
     * @return
     */
    Message findCategoryTypeByTitle(String categoryTitle);

    /**
     * 职称评审各类人员数量饼图数据
     * @return
     */
    Message<?> findCategorys();

    /**
     * 获取五大类人员占比
     * @return
     */
    Message<?> findCategorysRate();

    /**
     * 获取五大类人员职称占比
     * @return
     */
    Message<?> findCategoryTitlesRate();

    /**
     * 获取17小类人员占比
     * @return
     */
    Message<?> findCategoryTypesRate();

    /**
     * 通过领域、分类、职称名称获取职称id
     * @param category 领域
     * @param subCategory 分类
     * @param jobTitle 职称
     * @return
     */
    List<QaCategory> findTitles(String category, String subCategory, String jobTitle);

    /**
     * 获取模拟参评人员占比
     * @return
     */
    Message<?> findRecordsRate();

    /**
     * 获取职称名称列表
     * @return
     */
    Message findTitles();

    /**
     * 根据职称名称联动拟参评职称等级
     * @param titleName
     * @return
     */
    Message findAcademicType(String titleName);

    Message findCategoryTypes();
}