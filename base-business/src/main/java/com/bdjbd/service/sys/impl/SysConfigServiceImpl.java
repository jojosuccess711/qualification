/**
 * @filename:SysConfigServiceImpl 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys.impl;

import com.bdjbd.Message;
import com.bdjbd.common.util.DateUtil;
import com.bdjbd.dao.entity.SysConfig;
import com.bdjbd.dao.entity.SysConfigAttributes;
import com.bdjbd.dao.mapper.SysConfigMapper;
import com.bdjbd.service.sys.SysConfigAttributesService;
import com.bdjbd.service.sys.SysConfigService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**   
 *  
 * @Description:  系统配置 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "SysConfig")
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig, String> implements SysConfigService {

	@Autowired
    private SysConfigMapper mapper;

	@Autowired
	SysConfigAttributesService sysConfigAttributesService;

    @Autowired
    public void setBaseDao(SysConfigMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    @CacheEvict(cacheNames = {"SysConfig"}, allEntries = true)
    public void clearCache() {
    }

    /**
     * 通过id获取配置(包含属性)
     *
     * @param id
     * @return
     */
    @Override
    public SysConfig findHasAttributes(String id) {
        return mapper.findHasAttributes(id);
    }

    @Override
    public SysConfig findAllHasAttributes(String id) {
        return mapper.findAllHasAttributes(id);
    }

    @Override
    public Message<?> checkStartDateEndDate(String id) {
        SysConfig sysConfig = this.findHasAttributes(id);
        if (sysConfig == null || !sysConfig.getStatus()) {
            return Message.error("该功能目前暂未开放");
        }
        List<SysConfigAttributes> attributes = sysConfig.getAttributes();

        for (SysConfigAttributes attribute : attributes) {
            Date date = DateUtil.dateParse(DateUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, attribute.getValue());
            if (attribute.getName().equals("beginDate")) {
                if (date.after(new Date())) {
                    return Message.error("该功能目前暂未开放");
                }
            }
            if (attribute.getName().equals("endDate")) {
                if (date.before(new Date())) {
                    return Message.error("该功能已关闭");
                }
            }
        }
        return null;
    }

    @Override
    public boolean checkStartDateEndDate(SysConfig sysConfig) {
        if (sysConfig == null || !sysConfig.getStatus()) {
            return false;
        }
        List<SysConfigAttributes> attributes = sysConfig.getAttributes();
        for (SysConfigAttributes attribute : attributes) {
            Date date = DateUtil.dateParse(DateUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, attribute.getValue());
            if (attribute.getName().equals("beginDate")) {
                if (date.after(new Date())) {
                    return false;
                }
            }
            if (attribute.getName().equals("endDate")) {
                if (date.before(new Date())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Transactional
    @Override
    public int saveConfig(SysConfig sysConfig) {
        this.save(sysConfig);
        if (sysConfig.getAttributes() != null && sysConfig.getAttributes().size() > 0) {
            sysConfigAttributesService.batchInsert(sysConfig.getAttributes());
        }
        return 1;
    }

    @Transactional
    @Override
    public int updateConfig(SysConfig sysConfig) {
        sysConfigAttributesService.deleteByConfig(sysConfig.getId());
        if (sysConfig.getAttributes() != null && sysConfig.getAttributes().size() > 0) {
            sysConfigAttributesService.batchInsert(sysConfig.getAttributes());
        }
        this.update(sysConfig);
        return 1;
    }
}