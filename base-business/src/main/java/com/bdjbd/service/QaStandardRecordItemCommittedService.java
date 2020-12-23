/**
 * @filename:QaStandardRecordItemCommittedService 2020年4月9日
 * @project 职称评审系统  V1.0
 * Copyright(c) 2018 songzekun Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaStandardRecordItemCommitted;

import java.util.List;

/**
 *
 * @Description: 用户已提交参数选项数据 —— SERVICE
 * @Author: songzekun
 * @CreateDate: 2020年4月9日
 * @Version: V1.0
 *
 */
public interface QaStandardRecordItemCommittedService extends BaseService<QaStandardRecordItemCommitted, String> {
    List<QaStandardRecordItem> findRecordItemsByRecordId(String recordId);

    /**
     * 批量插入
     * @return
     */
    int batchInsert(List<QaStandardRecordItem> items);


    /**
     * 批量更新
     * @return
     */
    int batchUpdate(List<QaStandardRecordItem> items);
}