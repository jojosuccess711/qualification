/**
 * @filename:QaApplyRecordAttributeService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.dao.entity.QaApplyRecord;
import com.bdjbd.dao.entity.QaClause;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaApplyRecordAttribute;

import java.util.List;
import java.util.Map;
/**
 *  
 * @Description:  申请记录属性 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaApplyRecordAttributeService extends BaseService<QaApplyRecordAttribute, String> {
    /**
     * 根据申请id获取填报未满足的条件
     * @param applyId
     * @return
     */
   List<QaApplyRecordAttribute>  findAttributeByApplyId(String applyId);

    /**
     * 新增选择结果
     * @param list
     * @param qaApplyRecord
     * @param operateType
     * @return
     */
    boolean create(Map<String, String> list, QaApplyRecord qaApplyRecord, Integer operateType);

    /**
     * 批量增加选择结果
     * @param qaApplyRecordAttributeList
     * @return
     */
    int batchInsert(List<QaApplyRecordAttribute> qaApplyRecordAttributeList);

    /**
     * 判断用户选择结果是否符合规则
     * @param value
     * @param qaClauseList
     * @param list
     * @return
     */
    int isMatching(String value,String annexUrlResult, List<QaClause> qaClauseList, Map<String, String> list);

    /**
     * 删除某条申请记录的全部条件
     * @param id
     */
    void deleteByApplyId(String id);

    /**
     * 根据某条申请记录中所有通过项或所有未通过项
     * @param id
     * @param pastType
     * @return
     */
    List<QaApplyRecordAttribute> findPassInfoByApplyId(String id, int pastType);

    List<QaApplyRecordAttribute> findByUser(String userId);

}