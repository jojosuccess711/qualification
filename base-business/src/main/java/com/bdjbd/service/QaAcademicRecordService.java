/**
 * @filename:QaAcademicRecordService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.Message;
import com.bdjbd.dao.entity.QaAcademicRecordItem;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaAcademicRecord;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 * @Description:  职称评审记录 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaAcademicRecordService extends BaseService<QaAcademicRecord, String> {

    Integer saveRecord(QaAcademicRecord entity);

    /**
     * 电脑审核通过职称详情信息
     * @param pageNum
     * @param pageSize
     * @param category
     * @param subCategory
     * @param userName
     * @param jobTitle
     * @return
     */
    Message findRecords(Integer pageNum, Integer pageSize, String category,
                        String subCategory, String userName, String jobTitle);

    Integer deleteByUser(String userId);

    Integer batchInsert(List<QaAcademicRecord> list);

    /**
     * 拟参评符合审核条件的人员职称分类占比
     * @return
     */
    Message findRecordsRate(String curAcademicName,String recordTitle,
            String categoryType,String userName);

    /**
     * 拟参评人员列表/拟参评符合、不符合人员列表
     * @param curAcademicName 现任职称
     * @param recordTitle 拟参评职称等级
     * @param categoryType 目标岗位
     * @param hasPass 是否符合
     * @param organization 实际工作单位
     * @return
     */
    Message findRecordsList(Integer pageNum, Integer pageSize,String curAcademicName, String
            recordTitle,
            String categoryType,Boolean hasPass,String
            organization,String userName);

    /**
     * 拟参评不符合人员占比
     * @param curAcademicName 现任职称
     * @param recordTitle 拟参评职称
     * @param categoryType 目标岗位
     * @return
     */
    Message findNotPassRate(String curAcademicName, String recordTitle,
            String categoryType,String userName);

    /**
     * 获取拟参评不符合人员条件详情
     * @param recordId
     * @return
     */
    Message findNotPassDetails(String recordId);

    /**
     * 删除用户的拟参评记录和记录项
     */
    Message deleteAndInsertRecords(String userId,List<QaAcademicRecord>
            qaAcademicRecordList,List<QaAcademicRecordItem> qaAcademicRecordItemList);

    Message findUserCommittedRecords(String name,
           String category,String categoryType, String technologyCategory,
            String atrr0,
           Integer pageNum,
           Integer pageSize);

    Message findUserCommittedDetails(String userId);
}