/**
 * @filename:QaParticipantsDao 2020年8月24日
 * @project 人员录入  V1.0
 * Copyright(c) 2018 Mnie Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaParticipants;

/**
 *
 * @Description: 参评人员——DAO
 * @Author: Mnie
 * @CreateDate: 2020年8月24日
 * @Version: V1.0
 *
 */
public interface QaParticipantsMapper extends BaseDao<QaParticipants> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaParticipants> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaParticipants> items);


    void delByUserId(@Param("userId") String userId);
}
