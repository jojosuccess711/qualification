/**
 * @filename:SysConfigService 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys;

import com.bdjbd.Message;
import com.bdjbd.dao.entity.SysConfig;
import com.bdjbd.web.service.BaseService;

/**
 *  
 * @Description:  系统配置 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysConfigService extends BaseService<SysConfig, String> {

    /**
     * 清空缓存
     */
    void clearCache();

    /**
     * 通过id获取配置(包含属性)
     * @param id
     * @return
     */
    SysConfig findHasAttributes(String id);
    SysConfig findAllHasAttributes(String id);

    Message<?> checkStartDateEndDate(String id);

    boolean checkStartDateEndDate(SysConfig sysConfig);

    int saveConfig(SysConfig sysConfig);

    int updateConfig(SysConfig sysConfig);
}