/**
 * @filename:SysAuthorityServiceImpl 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys.impl;

import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.common.util.ListUtil;
import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.dao.mapper.SysAuthorityMapper;
import com.bdjbd.service.sys.SysAuthorityService;
import com.bdjbd.service.sys.SysRoleAuthorityService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**   
 *  
 * @Description:  权限 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "SysAuthority")
public class SysAuthorityServiceImpl extends BaseServiceImpl<SysAuthority, String> implements SysAuthorityService {

	@Autowired
    private SysAuthorityMapper mapper;
    @Autowired
    private SysRoleAuthorityService roleAuthorityService;

    @Autowired
    public void setBaseDao(SysAuthorityMapper mapper){
        super.setBaseDao(mapper);
    }

    /**
     * 获取指定用户权限列表
     *
     * @param adminId 用户id
     * @return 权限列表
     */
    @Override
    public List<SysAuthority> getByAdminId(String adminId) {
        return mapper.getByAdminId(adminId);
    }

    /**
     * 获取已拥有权限
     *
     * @param roleId
     * @param type
     * @return
     */
    @Override
    public List<SysAuthority> findHasAuthority(String roleId, Integer type) {
        return mapper.findHasAuthority(roleId, type);
    }

    /**
     * 获取未拥有权限
     *
     * @param roleId
     * @param type
     * @return
     */
    @Override
    public List<SysAuthority> findNotHasAuthority(String roleId, Integer type) {
        return mapper.findNotHasAuthority(roleId, type);
    }

    /**
     * 创建权限
     *
     * @param authority
     * @return
     */
    @Override
    public Message<?> createHandler(SysAuthority authority) {
        Boolean createOrUpdate = isCreateOrUpdate(authority);
        if(!createOrUpdate)
            return Message.error("权限已存在");

        Message<?> message = treeHandler(authority);
        if(message != null)
            return message;

        Integer save = save(authority);
        if(save == 1)
            return Message.success("保存成功");
        return Message.error("已经保存！");
    }

    /**
     * 编辑权限
     *
     * @param authority
     * @return
     */
    @Override
    public Message<?> updateHandler(SysAuthority authority) {
        Boolean createOrUpdate = isCreateOrUpdate(authority);
        if(!createOrUpdate)
            return Message.error("权限已存在");

        Message<?> message = treeHandler(authority);
        if(message != null)
            return message;

        Integer update = update(authority);
        if(update == 1)
            return Message.success("更新成功");
        return Message.error("已经更新！");
    }

    private Boolean isCreateOrUpdate(SysAuthority authority){
        List<SysAuthority> authorities = mapper.findExistByNameAuthorityUrl(authority.getName(), authority.getAuthority(), authority.getUrl());
        if(authorities.size() > 1)
            return false;
        if(authorities.size() > 0){
            if(authority.getId() == null)
                return false;
            return authorities.get(0).getId().equals(authority.getId());
        }
        return true;
    }

    private Message<?> treeHandler(SysAuthority authority){
        String parentId = authority.getParent();
        if(StringUtils.isNotBlank(parentId)){
            SysAuthority parent = find(parentId);
            if(parent == null){
                return Message.error("父级权限不存在");
            }
            authority.setGrade(parent.getGrade() + 1);
            authority.setTreePath(parent.getTreePath() + parentId + ",");
            authority.setParent(parentId);
        }else if(authority.getType() == SysAuthority.Type.ADMIN.ordinal()){
            authority.setParent(null);
            authority.setTreePath(",");
            authority.setGrade(0);
        }else{
            authority.setParent(null);
            authority.setTreePath(null);
            authority.setGrade(null);
        }
        return null;
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @Override
    public Message<?> deleteHandler(String id) {
        SysAuthority authority = find(id);
        if(authority == null)
            return Message.error("权限不存在");
        if(authority.getAuthority().contains("ROLE_"))
            return Message.error("内置权限不可删除");

        Integer count = roleAuthorityService.count(id);
        if(count > 0){
            return Message.error("权限已被分配，不可删除");
        }
        Integer delete = delete(id);
        if(delete == 1){
            return Message.success("删除成功");
        }
        return Message.error("权限已删除");
    }

    /**
     * 权限列表
     *
     * @param type
     * @return
     */
    @Override
    public List<SysAuthority> findList(SysAuthority.Type type) {
        return findList( ListUtil.addToList(Filter.eq("type", type.ordinal())), null);
    }

    @CacheEvict(cacheNames = {
            "SysAuthority"
    }, allEntries = true)
    @Override
    public void clearCache() {

    }
}