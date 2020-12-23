/**
 * @filename:SysConfigAttributesServiceImpl 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys.impl;

import com.bdjbd.dao.entity.SysConfigAttributes;
import com.bdjbd.dao.mapper.SysConfigAttributesMapper;
import com.bdjbd.service.sys.SysConfigAttributesService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**   
 *  
 * @Description:  系统配置项 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class SysConfigAttributesServiceImpl extends BaseServiceImpl<SysConfigAttributes, String> implements SysConfigAttributesService {

	@Autowired
    private SysConfigAttributesMapper mapper;

    @Autowired
    public void setBaseDao(SysConfigAttributesMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    public int batchInsert(List<SysConfigAttributes> items) {
        return mapper.batchInsert(items);
    }

    @Override
    public int deleteByConfig(String configId) {
        return mapper.deleteByConfig(configId);
    }
}