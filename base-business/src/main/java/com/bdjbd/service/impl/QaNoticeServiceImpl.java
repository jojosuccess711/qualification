/**
 * @filename:QaNoticeServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaNotice;
import com.bdjbd.dao.mapper.QaNoticeMapper;
import com.bdjbd.service.QaNoticeService;

/**   
 *  
 * @Description:  系统公告 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaNoticeServiceImpl extends BaseServiceImpl<QaNotice, String> implements QaNoticeService {

	@Autowired
    private QaNoticeMapper mapper;

    @Autowired
    public void setBaseDao(QaNoticeMapper mapper){
        super.setBaseDao(mapper);
    }
}