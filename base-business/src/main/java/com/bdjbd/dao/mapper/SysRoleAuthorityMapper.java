/**
 * @filename:SysRoleAuthorityDao 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.SysRoleAuthority;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  角色权限——DAO
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysRoleAuthorityMapper extends BaseDao<SysRoleAuthority> {

    /**
     * 删除角色权限
     * @param roleId
     * @return
     */
    int deleteByRoleId(@Param("roleId") String roleId);

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<SysRoleAuthority> items);
}
