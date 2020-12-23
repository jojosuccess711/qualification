/**
 * @filename:SysAdminDao 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.SysAdmin;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  管理员——DAO
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
public interface SysAdminMapper extends BaseDao<SysAdmin> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<SysAdmin> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<SysAdmin> items);

    List<SysAdmin> selectPage(
            @Param("offset") Integer offset,
            @Param("limit") Integer limit,
            @Param("attr0") String attr0,
            @Param("mobile") String mobile,
            @Param("name") String name,
            @Param("roles") String roles
    );
    Integer countPage(
            @Param("attr0") String attr0,
            @Param("mobile") String mobile,
            @Param("name") String name,
            @Param("roles") String roles
    );
}
