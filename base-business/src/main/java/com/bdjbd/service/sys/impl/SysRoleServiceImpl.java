/**
 * @filename:SysRoleServiceImpl 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys.impl;

import com.alibaba.fastjson.JSON;
import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.Pageable;
import com.bdjbd.common.util.ListUtil;
import com.bdjbd.common.util.UUIDUtils;
import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.dao.entity.SysRole;
import com.bdjbd.dao.mapper.SysRoleMapper;
import com.bdjbd.service.sys.*;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bdjbd.enums.LogEnum.*;

/**   
 *  
 * @Description:  角色 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String> implements SysRoleService {

	@Autowired
    private SysRoleMapper mapper;
    @Autowired
    private SysAdminRoleService userRoleService;
    @Autowired
    private SysAuthorityService authorityService;
    @Autowired
    private SysRoleAuthorityService roleAuthorityService;
    @Autowired
    private SysLogService sysLogService;

    @Autowired
    public void setBaseDao(SysRoleMapper mapper){
        super.setBaseDao(mapper);
    }

    /**
     * 获取用户拥有角色
     *
     * @param id
     * @return
     */
    @Override
    public List<SysRole> findHasRole(String id) {
        return mapper.findHasRole(id);
    }

    /**
     * 获取用户未拥有角色
     *
     * @param id
     * @return
     */
    @Override
    public List<SysRole> findNotHasRole(String id) {
        return mapper.findNotHasRole(SysAuthority.Type.ADMIN.ordinal(), id.toString());
    }

    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    @Override
    public Message<?> createHandler(SysRole role, String... authority) {
        role.setId(UUIDUtils.generateShortUuid());
        role.setCreateDate(new Date());
        role.setModifyDate(role.getCreateDate());
        role.setIsSystem(false);
        int insert = mapper.insert(role);
        if(insert == 1){
            Message message = authorityHandler(role.getId(), authority);
            if(!message.isSuccess())
                return message;

            sysLogService.createLog(ROLE_CREATE.ordinal(), role.getName(), null, JSON.toJSONString(role), ROLE_CREATE.getKey(), null, true);
            return Message.success("保存成功");
        }
        return Message.error("保存失败");
    }

    /**
     * 编辑角色
     *
     * @param role
     * @return
     */
    @Override
    public Message<?> updateHandler(SysRole role, String... authority) {
        SysRole oldRole = find(role.getId());
        oldRole.setModifyDate(new Date());
        oldRole.setDescription(role.getDescription());
        oldRole.setName(role.getName());
        oldRole.setType(role.getType());
        update(oldRole);

        Message message = authorityHandler(role.getId(), authority);
        if(!message.isSuccess())
            return message;

        sysLogService.createLog(ROLE_UPDATE.ordinal(), role.getName(), JSON.toJSONString(oldRole), JSON.toJSONString(oldRole), ROLE_UPDATE.getKey(), null, true);
        return Message.success("更新成功");
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @Override
    public Message<?> deleteHandler(String id) {
        SysRole role = find(id);
        if(role == null){
            return Message.error("角色已删除");
        }
        if(role.getIsSystem()){
            return Message.error("系统角色不可删除");
        }
        long count = userRoleService.count(id);
        if(count > 0){
            return Message.error("角色已被分配，不可删除");
        }
        Integer delete = delete(id);
        if(delete == 1){
            roleAuthorityService.deleteByRoleId(id);
            sysLogService.createLog(ROLE_DELETE.ordinal(), role.getName(), JSON.toJSONString(role), null, ROLE_DELETE.getKey(), null, true);
            return Message.success("删除成功");
        }
        return Message.error("角色已删除");
    }

    @Override
    public Message<?> batchDeleteHandler(String... ids) {
        Boolean hasDel = false;
        Boolean hasSystemRole = false;
        for(int i = 0; i < ids.length; i++){
            Message<?> message = deleteHandler(ids[i]);
            if(!message.isSuccess())
                hasSystemRole = true;
            else
                hasDel = true;
        }
        if(!hasDel)
            return Message.error("系统内置角色，不可删除！");
        return Message.success("批量删除成功" + (hasSystemRole ? "，删除项拥有系统角色，已被自动忽略" : ""));
    }

    /**
     * 权限管理
     *
     * @param id
     * @return
     */
    @Override
    public Message<?> authority(String id) {
        List<SysAuthority> hasAuthorities = authorityService.findHasAuthority(id, SysAuthority.Type.ADMIN.ordinal());
        List<SysAuthority> notHasAuthorities = authorityService.findNotHasAuthority(id, SysAuthority.Type.ADMIN.ordinal());
        Map<String, Object> result = new HashMap<>();
        result.put("hasAuthorities", hasAuthorities);
        result.put("notHasAuthorities", notHasAuthorities);
        return Message.success(result);
    }

    /**
     * 权限保存
     *
     * @param id
     * @param authority
     * @return
     */
    @Override
    @Transactional
    public Message<?> authoritySave(String id, String... authority) {
        SysRole role = find(id);
        if(role == null)
            return Message.error("角色不存在");
        if(role.getIsSystem())
            return Message.error("内置角色不可修改");

        Message message = authorityHandler(id, authority);
        if(!message.isSuccess())
            return message;

        return Message.success("保存成功");
    }

    private Message authorityHandler(String roleId, String... authority){
        List<SysAuthority> authorities = authorityService.findList(authority);
        if(authorities == null || authorities.isEmpty()) {
            return Message.error("权限不存在");
        }
        roleAuthorityService.deleteByRoleId(roleId);
        roleAuthorityService.batchInsert(roleId, authorities);

        sysLogService.createLog(ROLE_AUTH.ordinal(), roleId, null, JSON.toJSONString(authorities), ROLE_AUTH.getKey(), null, true);
        return Message.success();
    }

    @Override
    public List<SysRole> findList(SysAuthority.Type type) {
        return findList(Pageable.MAX_PAGE_SIZE, type != null ? ListUtil.addToList(Filter.eq("type", type.ordinal())) : null, null);
    }

    @Override
    public Message<SysRole> info(String id) {
        SysRole sysRole = find(id);
        if(sysRole == null)
            return Message.error("角色不存在");
        sysRole.setAuthorities(authorityService.findHasAuthority(id, sysRole.getType()));
        return Message.success(sysRole);
    }
}