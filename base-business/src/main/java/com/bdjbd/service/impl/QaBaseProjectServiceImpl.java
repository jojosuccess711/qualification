/**
 * @filename:QaBaseProjectServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaBaseProject;
import com.bdjbd.dao.mapper.QaBaseProjectMapper;
import com.bdjbd.service.QaBaseProjectService;

/**   
 *  
 * @Description:  项目项表 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaBaseProjectServiceImpl extends BaseServiceImpl<QaBaseProject, String> implements QaBaseProjectService {

	@Autowired
    private QaBaseProjectMapper mapper;

    @Autowired
    public void setBaseDao(QaBaseProjectMapper mapper){
        super.setBaseDao(mapper);
    }
}