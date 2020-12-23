/**
 * @filename:SysUserRoleDao 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.SysAdminRole;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  用户角色——DAO
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysAdminRoleMapper extends BaseDao<SysAdminRole> {

    /**
     * 删除用户角色
     * @param adminId
     * @return
     */
    int deleteByAdminId(@Param("adminId") String adminId);

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<SysAdminRole> items);

}
