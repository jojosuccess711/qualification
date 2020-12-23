/**
 * @filename:QaStandardRecordItemCommittedServiceImpl 2020年4月9日
 * @project 职称评审系统  V1.0
 * Copyright(c) 2018 songzekun Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import com.bdjbd.dao.entity.QaStandardRecordItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaStandardRecordItemCommitted;
import com.bdjbd.dao.mapper.QaStandardRecordItemCommittedMapper;
import com.bdjbd.service.QaStandardRecordItemCommittedService;

import java.util.List;

/**   
 *  
 * @Description:  用户已提交参数选项数据 —— SERVICEIMPL
 * @Author:       songzekun   
 * @CreateDate:   2020年4月9日
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaStandardRecordItemCommittedServiceImpl extends BaseServiceImpl<QaStandardRecordItemCommitted, String> implements QaStandardRecordItemCommittedService {

	@Autowired
    private QaStandardRecordItemCommittedMapper mapper;

    @Autowired
    public void setBaseDao(QaStandardRecordItemCommittedMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    public List<QaStandardRecordItem> findRecordItemsByRecordId(String recordId) {
        return mapper.findRecordItemsByRecordId(recordId);
    }

    /**
     * 批量插入
     * @return
     */
    @Override
    public int batchInsert(List<QaStandardRecordItem> items) {
        return mapper.batchInsert(items);
    }

    /**
     * 批量更新
     * @return
     */
    @Override
    public int batchUpdate(List<QaStandardRecordItem> items) {
        return mapper.batchUpdate(items);
    }
}