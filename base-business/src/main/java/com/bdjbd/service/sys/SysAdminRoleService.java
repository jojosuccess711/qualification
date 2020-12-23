/**
 * @filename:SysUserRoleService 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys;

import com.bdjbd.dao.entity.SysAdminRole;
import com.bdjbd.dao.entity.SysRole;
import com.bdjbd.web.service.BaseService;

import java.util.List;

/**
 *  
 * @Description:  用户角色 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysAdminRoleService extends BaseService<SysAdminRole, String> {

    /**
     * 删除用户角色
     * @param adminId
     * @return
     */
    int deleteByAdminId(String adminId);

    /**
     * 批量添加角色
     * @param adminId
     * @param roles
     * @return
     */
    int batchInsert(String adminId, List<SysRole> roles);

    /**
     * 角色使用数量
     * @param roleId 角色Id
     * @return
     */
    long count(String roleId);
}