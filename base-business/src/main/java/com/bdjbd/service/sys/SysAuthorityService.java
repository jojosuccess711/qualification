/**
 * @filename:SysAuthorityService 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys;

import com.bdjbd.Message;
import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.web.service.BaseService;

import java.util.List;

/**   
 *  
 * @Description:  权限 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysAuthorityService extends BaseService<SysAuthority, String> {

    /**
     * 获取指定用户权限列表
     * @param adminId 用户id
     * @return 权限列表
     */
    List<SysAuthority> getByAdminId(String adminId);

    /**
     * 获取已拥有权限
     * @param roleId
     * @param type
     * @return
     */
    List<SysAuthority> findHasAuthority(String roleId, Integer type);

    /**
     * 获取未拥有权限
     * @param roleId
     * @param type
     * @return
     */
    List<SysAuthority> findNotHasAuthority(String roleId, Integer type);

    /**
     * 创建权限
     * @param authority
     * @return
     */
    Message<?> createHandler(SysAuthority authority);

    /**
     * 编辑权限
     * @param authority
     * @return
     */
    Message<?> updateHandler(SysAuthority authority);

    /**
     * 删除权限
     * @param id
     * @return
     */
    Message<?> deleteHandler(String id);

    /**
     * 权限列表
     * @param type
     * @return
     */
    List<SysAuthority> findList(SysAuthority.Type type);

    /**
     * 清除缓存
     */
    void clearCache();
}