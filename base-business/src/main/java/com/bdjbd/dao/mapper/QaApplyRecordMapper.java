/**
 * @filename:QaApplyRecordDao 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.mapper;

import com.bdjbd.Filter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.dao.entity.QaApplyRecord;

/**   
 *  
 * @Description:  申请记录——DAO
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaApplyRecordMapper extends BaseDao<QaApplyRecord> {

    /**
     * 批量插入
     * @param items
     * @return
     */
    int batchInsert(@Param("items") List<QaApplyRecord> items);


    /**
     * 批量更新
     * @param items
     * @return
     */
    int batchUpdate(@Param("items") List<QaApplyRecord> items);

    List<QaApplyRecord> findList(@Param("offset") Integer offset,
                                       @Param("pageSize") Integer pageSize,
                                       @Param("category") String category,
                                       @Param("subCategory") String subCategory,
                                       @Param("userName") String userName,
                                       @Param("jobTitle") String jobTitle,
                                       @Param("approveTime") String approveTime,
                                       @Param("approveResult") Integer approveResult,
                                       @Param("approveResultList") List<Integer> approveResultList
                                 );

    Integer countList(@Param("category") String category,
                                 @Param("subCategory") String subCategory,
                                 @Param("userName") String userName,
                                 @Param("jobTitle") String jobTitle,
                                 @Param("approveTime") String approveTime,
                                 @Param("approveResult") Integer approveResult,
                      @Param("approveResultList") List<Integer> approveResultList);

    QaApplyRecord findRecordWithAttribute(@Param("filters")Filter[] filters);

    List<QaApplyRecord> findApplyAndAttrCount(@Param("approveStatus") Integer approveStatus,
                                                    @Param("past") Integer past,
                                                    @Param("categoryAcademicId") String categoryAcademicId
                                              );
}
