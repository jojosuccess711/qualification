/**
 * @filename:SysRoleAuthorityService 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys;

import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.dao.entity.SysRoleAuthority;
import com.bdjbd.web.service.BaseService;

import java.util.List;

/**
 *  
 * @Description:  角色权限 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysRoleAuthorityService extends BaseService<SysRoleAuthority, String> {

    /**
     * 删除角色权限
     * @param roleId
     * @return
     */
    int deleteByRoleId(String roleId);

    /**
     * 批量添加权限
     * @param roleId
     * @param authorities
     * @return
     */
    int batchInsert(String roleId, List<SysAuthority> authorities);

    /**
     * 权限使用数量
     * @param authorityId
     * @return
     */
    Integer count(String authorityId);
}