/**
 * @filename:QaAcademicRecordDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.QaAcademicRecordItem;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  职称评审记录——DAO
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaAcademicRecordItemMapper extends BaseDao<QaAcademicRecordItem> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaAcademicRecordItem> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaAcademicRecordItem> items);

    int deleteByUser( @Param("userId") String userId);

    List<QaAcademicRecordItem> findNotPassDetails(@Param("recordId") String
            recordId);

    List<String> findRecordList(@Param("groupId") String groupId,@Param("status") String status);

    Integer findMaxOrder(@Param("groupId") String groupId,@Param("recordId") String recordId);
}
