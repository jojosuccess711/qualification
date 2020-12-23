/**
 * @filename:SysConfigAttributesService 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys;

import com.bdjbd.dao.entity.SysConfigAttributes;
import com.bdjbd.web.service.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  
 * @Description:  系统配置项 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysConfigAttributesService extends BaseService<SysConfigAttributes, String> {

    int batchInsert(List<SysConfigAttributes> items);

    int deleteByConfig(String configId);
}