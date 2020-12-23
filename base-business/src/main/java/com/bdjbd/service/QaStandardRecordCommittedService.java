/**
 * @filename:QaStandardRecordCommittedService 2020年4月9日
 * @project 职称评审系统  V1.0
 * Copyright(c) 2018 songzekun Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.Page;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaStandardRecordCommitted;
/**   
 *  
 * @Description:  已提交的用户信息 —— SERVICE
 * @Author:       songzekun   
 * @CreateDate:   2020年4月9日
 * @Version:      V1.0
 *    
 */
public interface QaStandardRecordCommittedService extends BaseService<QaStandardRecordCommitted, String> {

    Page<QaStandardRecordCommitted> findCommittedRecords(Integer pageNum, Integer pageSize, String name, String category, String categoryType, String technologyCategory, String attr0);
}