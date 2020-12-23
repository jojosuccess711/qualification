/**
 * @filename:SysLogServiceImpl 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.sys.impl;

import com.bdjbd.common.util.UUIDUtils;
import com.bdjbd.dao.entity.SysAdmin;
import com.bdjbd.dao.entity.SysLog;
import com.bdjbd.dao.mapper.SysLogMapper;
import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.service.sys.SysLogService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**   
 *  
 * @Description:  系统日志 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, String> implements SysLogService {

	@Autowired
    private SysLogMapper mapper;
	@Autowired
    private SysAdminService sysAdminService;

    @Autowired
    public void setBaseDao(SysLogMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    public SysLog createLog(Integer type, String relationId, String before, String after, String content, String memo, Boolean save) {
        SysAdmin admin = null;
        SysLog sysLog = new SysLog();
        sysLog.setId(UUIDUtils.generateUuid());
        sysLog.setCreateDate(new Date());
        sysLog.setOperatorId(admin != null ? admin.getId().toString() : null);
        sysLog.setOperator(admin != null ? admin.getName() : null);
        sysLog.setType(type);
        sysLog.setRelationId(relationId);
        sysLog.setBeforeInfo(before);
        sysLog.setAfterInfo(after);
        sysLog.setContent(content);
        sysLog.setMemo(memo);
        if(save)
            save(sysLog);
        return sysLog;
    }

    @Override
    public int batchInsert(List<SysLog> items) {
        return mapper.batchInsert(items);
    }
}