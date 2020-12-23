/**
 * @filename:SysUserRoleServiceImpl 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys.impl;

import com.bdjbd.Filter;
import com.bdjbd.dao.entity.SysAdminRole;
import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.dao.entity.SysRole;
import com.bdjbd.dao.mapper.SysAdminRoleMapper;
import com.bdjbd.service.sys.SysAdminRoleService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**   
 *  
 * @Description:  用户角色 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class SysAdminRoleServiceImpl extends BaseServiceImpl<SysAdminRole, String> implements SysAdminRoleService {

	@Autowired
    private SysAdminRoleMapper mapper;

    @Autowired
    public void setBaseDao(SysAdminRoleMapper mapper){
        super.setBaseDao(mapper);
    }

    /**
     * 删除用户角色
     *
     * @param adminId
     * @return
     */
    @Override
    public int deleteByAdminId(String adminId) {
        return mapper.deleteByAdminId(adminId);
    }

    /**
     * 批量添加角色
     *
     * @param adminId
     * @param roles
     * @return
     */
    @Override
    public int batchInsert(String adminId, List<SysRole> roles) {
        if(roles == null || roles.isEmpty())
            return 0;
        List<SysAdminRole> userRoles = new ArrayList<>();
        for(Iterator<SysRole> iterator = roles.iterator(); iterator.hasNext();){
            SysAdminRole userRole = new SysAdminRole();
            userRole.setAdminId(adminId);
            userRole.setRolesId(iterator.next().getId());
            userRole.setType(SysAuthority.Type.ADMIN.ordinal());
            userRoles.add(userRole);
        }
        return mapper.insertList(userRoles);
    }

    /**
     * 角色使用数量
     *
     * @param roleId 角色Id
     * @return
     */
    @Override
    public long count(String roleId) {
        return count(Filter.eq("rolesId", roleId));
    }
}