/**
 * @filename:QaBaseClauseItemServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import com.bdjbd.Message;
import com.bdjbd.bo.MapperClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaBaseClauseItem;
import com.bdjbd.dao.mapper.QaBaseClauseItemMapper;
import com.bdjbd.service.QaBaseClauseItemService;

import java.util.List;
import java.util.Map;

/**   
 *  
 * @Description:  条件子项 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaBaseClauseItemServiceImpl extends BaseServiceImpl<QaBaseClauseItem, String> implements QaBaseClauseItemService {

	@Autowired
    private QaBaseClauseItemMapper mapper;

    @Autowired
    public void setBaseDao(QaBaseClauseItemMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    public Message<QaBaseClauseItem> finAllList(List<Long> categoryAcademicIdList) {
        List<Map> itemsList = mapper.findItemByAcademics(categoryAcademicIdList);
        if(itemsList != null && !itemsList.isEmpty()){
            return Message.success(itemsList);
        }
        return Message.error("条件为空");
    }

//    @Cacheable("findItemsByCategory")
    @Override
    public List<MapperClause> findItemsByCategory(String categoryId) {
        return mapper.findItemsByCategory(categoryId);
    }
}