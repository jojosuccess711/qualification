/**
 * @filename:QaStandardRecordItemDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaStandardRecordItem;

/**
 * @Description: 用户标准信息记录项——DAO
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 */
public interface QaStandardRecordItemMapper extends BaseDao<QaStandardRecordItem> {

    /**
     * 批量插入
     *
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaStandardRecordItem> items);


    /**
     * 批量更新
     *
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaStandardRecordItem> items);

    /**
     * 插入/修改用户数据
     *
     * @param useData
     */
    void replaceData(@Param("useData") List<QaStandardRecordItem> useData);

    Integer findMaxId(@Param("recordId") String recordId);

    /**
     * 数据录入删除录入项数据
     *
     * @param parameterId
     * @param recordId
     */
    void deleteByRecordIdAndparamId(@Param("groupId") String groupId,
                                    @Param("parameterId") String parameterId, @Param("recordId")
                                            String recordId, @Param("order") Integer order);

    void deleteByGroupId(@Param("groupIds") String[] groupIds, @Param("recordId")
            String recordId);

    void batchDelete(@Param("items") List<QaStandardRecordItem> items);

    List<QaStandardRecordItem> findItemsByRecordId(@Param("recordId") String
                                                           recordId);

    List<QaStandardRecordItem> findPageList(@Param("recordId") String recordId, @Param("groupId") String groupId);

    List<QaStandardRecordItem> findPageListItem(@Param("recordId") String recordId, @Param("groupId") String groupId, @Param("i") int i);

    int selectCountByPage(@Param("recordId") String recordId, @Param("groupId") String groupId);

    List<QaStandardRecordItem> selectItemGroup(@Param("recordId") String recordId, @Param("groupId") String groupId, @Param("min") int min, @Param("max") int max);

    List<QaStandardRecordItem> selectRepairData(@Param("recordId") String recordId);
}
