/**
 * @filename:SysRoleDao 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.SysRole;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  角色——DAO
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysRoleMapper extends BaseDao<SysRole> {

    /**
     * 获取已拥有角色
     * @param adminId 用户Id
     * @return
     */
    List<SysRole> findHasRole(@Param("adminId") String adminId);

    /**
     * 获取未拥有角色
     * @param type 类型
     * @param adminId 用户Id
     * @return
     */
    List<SysRole> findNotHasRole(@Param("type") Integer type, @Param("adminId") String adminId);

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<SysRole> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<SysRole> items);
}
