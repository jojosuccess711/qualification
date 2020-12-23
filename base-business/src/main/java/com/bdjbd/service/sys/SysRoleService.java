/**
 * @filename:SysRoleService 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys;

import com.bdjbd.Message;
import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.dao.entity.SysRole;
import com.bdjbd.web.service.BaseService;

import java.util.List;

/**
 *  
 * @Description:  角色 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysRoleService extends BaseService<SysRole, String> {

    /**
     * 获取用户拥有角色
     * @param id
     * @return
     */
    List<SysRole> findHasRole(String id);

    /**
     * 获取用户未拥有角色
     * @param id
     * @return
     */
    List<SysRole>findNotHasRole(String id);

    /**
     * 创建角色
     * @param role
     * @return
     */
    Message<?> createHandler(SysRole role, String... authority);

    /**
     * 编辑角色
     * @param role
     * @return
     */
    Message<?> updateHandler(SysRole role, String... authority);

    /**
     * 删除角色
     * @param id
     * @return
     */
    Message<?> deleteHandler(String id);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    Message<?> batchDeleteHandler(String... ids);

    /**
     * 权限管理
     * @param id
     * @return
     */
    Message<?> authority(String id);

    /**
     * 权限保存
     * @param authority
     * @return
     */
    Message<?> authoritySave(String id, String... authority);

    /**
     * 角色列表
     * @param type
     * @return
     */
    List<SysRole> findList(SysAuthority.Type type);

    /**
     * 角色信息
     * @param id
     * @return
     */
    Message<SysRole> info(String id);
}