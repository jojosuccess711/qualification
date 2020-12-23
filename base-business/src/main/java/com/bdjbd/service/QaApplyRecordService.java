/**
 * @filename:QaApplyRecordService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.Message;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaApplyRecord;
import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.Page;
import java.util.List;
import java.util.Map;

/**
 *
 * @Description:  申请记录 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaApplyRecordService extends BaseService<QaApplyRecord, String> {

    List<QaApplyRecord> findList(Integer pageNum, Integer pageSize, String category, String subCategory,
                                      String userName, String jobTitle, String approveTime, Integer approveResult, List<Integer> approveResultList);

    Integer countList(String category, String subCategory,
                                      String userName, String jobTitle, String approveTime, Integer approveResult, List<Integer> approveResultList);


    /**
     * 新增申请记录
     * @param userId  申请用户ID
     * @param userName 用户姓名
     * @param categoryId  领域id
     * @param categoryChildId 岗位id
     * @param categoryAcademicId 申请职称
     * @param list 条件列表
     * @param operateType
     * @return
     */
    QaApplyRecord create(String userId, String userName, String categoryId, String categoryChildId, String categoryAcademicId, Map<String, String> list, Integer operateType);

    /**
     * 修改申请记录
     *
     * @param id
     * @param list 条件列表
     * @param operateType
     * @return
     */
    Message<QaApplyRecord> edit( String id, Map<String, String> list, Integer operateType);

    /**
     * 人工审核
     * @param applyId
     * @param opinion
     * @param type
     * @return
     */
    Message<QaApplyRecord> approve(String applyId, String opinion, Integer type);
    /**
     * 通过登录用户获取个人首页数据/通过用户姓名，电话号码筛选用户申请信息
     * @param userId
     * @param userName
     * @param phoneNum
     * @return
     */
    Message findPersonalPage(String userId,String userName,String  phoneNum);

    /**
     * 通过登录用户信息获取已申请未申请列表
     * @param userId
     * @return
     */
    Message findPersonalApplies(String userId);

    /**
     * 通过条件筛选申请信息（包含申请条件信息）
     * @param filters
     * @return
     */
    QaApplyRecord   findRecordWithAttribute(Filter... filters);

    /**
     * 审核详情查看
     * @param id
     * @return
     */
    Message<QaApplyRecord> approveDetails(String id);

    /**
     * 插入某个领域下的多条申请记录
     * @param userId
     * @param userName
     * @param categoryId
     * @param list
     * @param operateType
     * @return
     */
    int createList(String userId, String userName, String categoryId, Map<String, String> list, int operateType);

    /**
     * 根据用户ID和申请职称获取申请记录
     * @param userId
     * @param categoryAcademicId
     * @return
     */
    QaApplyRecord findByUserAndAcademic(String userId, String categoryAcademicId);

    /**
     * 根据用户ID和申请领域获取申请记录
     * @param userId
     * @param categoryId
     * @return
     */
    List<QaApplyRecord> findByUserAndCategory(String userId, String categoryId);

    List<QaApplyRecord> findApplyAndAttrCount(Integer approveStatus, Integer past, String categoryAcademicId);
}