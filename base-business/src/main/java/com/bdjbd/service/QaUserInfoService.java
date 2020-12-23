/**
 * @filename:QaUserInfoService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaUserInfo;
/**   
 *  
 * @Description:  用户信息 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaUserInfoService extends BaseService<QaUserInfo, String> {

    /**
     * 创建用户信息
     * @param id
     * @param mobile
     * @param techNum
     * @return
     */
    QaUserInfo createHandler(String id, String mobile, String name, String techNum);
}