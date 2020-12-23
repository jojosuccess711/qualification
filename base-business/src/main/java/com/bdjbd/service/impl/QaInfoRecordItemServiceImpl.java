/**
 * @filename:QaInfoRecordItemServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaInfoRecordItem;
import com.bdjbd.dao.mapper.QaInfoRecordItemMapper;
import com.bdjbd.service.QaInfoRecordItemService;

/**   
 *  
 * @Description:  录入信息记录项表 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaInfoRecordItemServiceImpl extends BaseServiceImpl<QaInfoRecordItem, String> implements QaInfoRecordItemService {

	@Autowired
    private QaInfoRecordItemMapper mapper;

    @Autowired
    public void setBaseDao(QaInfoRecordItemMapper mapper){
        super.setBaseDao(mapper);
    }
}