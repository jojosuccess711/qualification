/**
 * @filename:QaStandardRecordDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.bo.QaStandardRecordVO;
import com.bdjbd.dao.entity.QaStandardRecord;

/**
 * @Description: 用户标准信息记录表——DAO
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 */
public interface QaStandardRecordMapper extends BaseDao<QaStandardRecord> {

    /**
     * 批量插入
     *
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaStandardRecord> items);


    /**
     * 批量更新
     *
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaStandardRecord> items);

    /**
     * 通过申请人信息查询已填写的申请信息
     *
     * @param userId
     * @return
     */
    List<QaStandardRecord> findQaStandardRecords(@Param("userId") String userId);

    List<QaStandardRecord> findByUserAndParamGroup(@Param("userId") String userId,
                                                   @Param("paramsGroupId") String paramsGroupId);

    /**
     * 根据用户id查询用户基本信息
     * changpeng添加
     *
     * @param userId
     * @return
     */
    Map<String, Object> getBaseUserInfo(@Param("userId") String userId);

    QaStandardRecord findOne(@Param("userId") String userId);

	QaStandardRecord findByIdCard(@Param("idCard")String idCard);
}
