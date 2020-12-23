/**
 * @filename:QaStandardRecordService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.bo.QaStandardRecordVO;
import com.bdjbd.bo.ResVO;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaStandardRecord;

import java.util.List;
import java.util.Map;

/**
 *  
 * @Description:  用户标准信息记录表 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaStandardRecordService extends BaseService<QaStandardRecord, String> {
    /**
     * 通过申请人信息查询已填写的申请信息
     * @param userId
     * @return
     */
	List<QaStandardRecord> findQaStandardRecords(String userId);
    /**
     * 判断用户基础信息并保存
     * @param QaStandardRecord
     * @param userId
     * @return Message
     */
    Message saveOrUpdateStandardRecord(QaStandardRecord QaStandardRecord,String userId);

    List<QaStandardRecord> findByUserAndParamGroup(String userId, String groupId);

    Page<QaStandardRecord> findPage(Integer pageNum, Integer pageSize, QaStandardRecord qaStandardRecord);

    int saveUserCategory(QaStandardRecord qaStandardRecord);

    Message<?> findDetails(String userId);


    Page<QaStandardRecordVO> findApproveList(Integer pageNum, Integer pageSize, QaStandardRecord qaStandardRecord)throws Exception;

    ResVO findListByGroup(Integer pageNum, Integer pageSize, String groupId, String userId, String type,String status);

    /**
     * 根据用户id查询用户基本信息
     * changpeng添加
     * @param userId
     * @return
     */
    Map<String, Object> getBaseUserInfo(String userId);
    
    QaStandardRecord findByIdCard(String idCard);
}