/**
 * @filename:QaIntoParticipantsDao 2020年8月24日
 * @project 人员录入  V1.0
 * Copyright(c) 2018 Mnie Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.bo.Number;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaIntoParticipants;

/**
 * @Description: 进入参评人员——DAO
 * @Author: Mnie
 * @CreateDate: 2020年8月24日
 * @Version: V1.0
 */
public interface QaIntoParticipantsMapper extends BaseDao<QaIntoParticipants> {

    /**
     * 批量插入
     *
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaIntoParticipants> items);


    /**
     * 批量更新
     *
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaIntoParticipants> items);

    void deleteByParticipants(@Param("userId") String userId);

    QaIntoParticipants selectByUserId(String userId);

    void delByUserId(@Param("userId") String userId);

    Number selectNumber();
}
