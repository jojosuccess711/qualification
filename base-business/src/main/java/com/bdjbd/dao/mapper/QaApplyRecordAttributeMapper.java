/**
 * @filename:QaApplyRecordAttributeDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaApplyRecordAttribute;

/**   
 *  
 * @Description:  申请记录属性——DAO
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaApplyRecordAttributeMapper extends BaseDao<QaApplyRecordAttribute> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaApplyRecordAttribute> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaApplyRecordAttribute> items);

    /**
     * 删除某条申请记录的全部条件
     * @param applyId
     */
    int deleteByApplyId(@Param("applyId") String applyId);
    List<QaApplyRecordAttribute>findAttributeByApplyId(@Param("applyId") String applyId);

    List<QaApplyRecordAttribute> findByUser(@Param("userId") String userId);

    /**
     *
     * @param applyId
     * @param past 是否满足业绩条件要求 1：是 0：否
     */
    Integer findCountByApply(@Param("applyId") String applyId, @Param("past") Integer past);
}
