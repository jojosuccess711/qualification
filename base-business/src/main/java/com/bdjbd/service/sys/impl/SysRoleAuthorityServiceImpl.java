/**
 * @filename:SysRoleAuthorityServiceImpl 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys.impl;

import com.bdjbd.Filter;
import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.dao.entity.SysRoleAuthority;
import com.bdjbd.dao.mapper.SysRoleAuthorityMapper;
import com.bdjbd.service.sys.SysRoleAuthorityService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**   
 *  
 * @Description:  角色权限 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class SysRoleAuthorityServiceImpl extends BaseServiceImpl<SysRoleAuthority, String> implements SysRoleAuthorityService {

	@Autowired
    private SysRoleAuthorityMapper mapper;

    @Autowired
    public void setBaseDao(SysRoleAuthorityMapper mapper){
        super.setBaseDao(mapper);
    }

    /**
     * 删除角色权限
     *
     * @param roleId
     * @return
     */
    @Override
    public int deleteByRoleId(String roleId) {
        return mapper.deleteByRoleId(roleId);
    }

    /**
     * 批量添加权限
     *
     * @param roleId
     * @param authorities
     * @return
     */
    @Override
    public int batchInsert(String roleId, List<SysAuthority> authorities) {
        if(authorities == null || authorities.isEmpty())
            return 0;
        List<SysRoleAuthority> roleAuthorities = new ArrayList<>();
        for(Iterator<SysAuthority> iterator = authorities.iterator(); iterator.hasNext();){
            SysRoleAuthority roleAuthority = new SysRoleAuthority();
            roleAuthority.setRoleId(roleId);
            roleAuthority.setAuthorityId(iterator.next().getId());
            roleAuthorities.add(roleAuthority);
        }
        return mapper.batchInsert(roleAuthorities);
    }

    /**
     * 权限使用数量
     *
     * @param authorityId
     * @return
     */
    @Override
    public Integer count(String authorityId) {
        return count(Filter.eq("authorityId", authorityId));
    }
}