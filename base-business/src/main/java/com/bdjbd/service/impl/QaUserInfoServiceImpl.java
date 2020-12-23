/**
 * @filename:QaUserInfoServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaUserInfo;
import com.bdjbd.dao.mapper.QaUserInfoMapper;
import com.bdjbd.service.QaUserInfoService;

import java.util.Date;

/**   
 *  
 * @Description:  用户信息 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaUserInfoServiceImpl extends BaseServiceImpl<QaUserInfo, String> implements QaUserInfoService {

	@Autowired
    private QaUserInfoMapper mapper;

    @Autowired
    public void setBaseDao(QaUserInfoMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    public QaUserInfo createHandler(String id, String mobile, String name, String techNum) {
        QaUserInfo userInfo = new QaUserInfo();
        userInfo.setId(id);
        userInfo.setMobile(mobile);
        userInfo.setTechNum(techNum);
        userInfo.setCreateDate(new Date());
        userInfo.setModifyDate(userInfo.getCreateDate());
        save(userInfo);
        return userInfo;
    }
}