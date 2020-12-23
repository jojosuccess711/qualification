package com.bdjbd.service;

import com.bdjbd.dao.entity.QaAcademicRecordItem;
import com.bdjbd.web.service.BaseService;

import java.util.List;

/**
 * @author zhuzhe
 */
public interface QaAcademicRecordItemService extends BaseService<QaAcademicRecordItem, String> {

    /**
     * 批量保存
     * @return
     */
    int batchInsert(List<QaAcademicRecordItem> items);

    int deleteByUser(String userId);

    /**
     * 获取拟参评不符合人员条件详情
     * @return
     */
    List<QaAcademicRecordItem> findNotPassDetails(String recordId);

    /**
     * 通过groupid和通过状态获取recordid的列表
     * @param groupId
     * @param status
     * @return
     */
    List<String> findRecordList(String groupId, String status);


}
