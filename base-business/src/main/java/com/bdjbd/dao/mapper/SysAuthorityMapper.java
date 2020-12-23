/**
 * @filename:SysAuthorityDao 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  权限——DAO
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysAuthorityMapper extends BaseDao<SysAuthority> {

    /**
     * 通过名称、权限名、url查找权限项
     * @param name
     * @param authority
     * @param url
     * @return
     */
    List<SysAuthority> findExistByNameAuthorityUrl(@Param("name") String name,
                                                   @Param("authority") String authority,
                                                   @Param("url") String url);

    /**
     * 获取指定用户权限列表
     *
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
    List<SysAuthority> findHasAuthority(@Param("roleId") String roleId, @Param("type") Integer type);

    /**
     * 获取未拥有权限
     * @param roleId
     * @param type
     * @return
     */
    List<SysAuthority> findNotHasAuthority(@Param("roleId") String roleId, @Param("type") Integer type);

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<SysAuthority> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<SysAuthority> items);
}
