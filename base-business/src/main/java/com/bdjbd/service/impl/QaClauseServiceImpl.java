/**
 * @filename:QaClauseServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import com.bdjbd.Filter;
import com.bdjbd.Order;
import com.bdjbd.common.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaClause;
import com.bdjbd.dao.mapper.QaClauseMapper;
import com.bdjbd.service.QaClauseService;

import java.util.ArrayList;
import java.util.List;

/**   
 *  
 * @Description:  评审条件 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaClauseServiceImpl extends BaseServiceImpl<QaClause, String> implements QaClauseService {

	@Autowired
    private QaClauseMapper mapper;

    @Autowired
    public void setBaseDao(QaClauseMapper mapper){
        super.setBaseDao(mapper);
    }

    /**
     * 根据条件ID获取该条件序号下所有条件
     * @param id
     * @return
     */
    @Override
    public List<QaClause> findClauseList(String id) {
        QaClause qaclause = this.find(id);
        List<QaClause> qaClauseList = null;
        if(qaclause != null){
            qaClauseList = this.findList(100, ListUtil.addToList(Filter.eq("clauseNum", qaclause.getClauseNum()),Filter.eq("categoryId",qaclause.getCategoryId())), null);
        }
        return qaClauseList;
    }

    /**
     * 查找指定职称下所有条件集合
     * @param categoryId
     * @return
     */
    @Override
    public List<QaClause> findListByAcademic(String categoryId) {
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("clauseNum"));
        List<QaClause> list = this.findList(100, ListUtil.addToList(Filter.like("categoryId",categoryId)), orders);
        return list;
    }

    /**
     * 根据条件ID查询与该条件重复的其他条件
     * @param id
     * @return
     */
    @Override
    public List<QaClause> findRepeatClauseListById(String id) {
        QaClause qaClause = this.find(id);
        if(qaClause != null) {
            List<QaClause> list = this.findList(100, ListUtil.addToList(Filter.eq("repeatIndex", qaClause.getRepeatIndex())), null);
            return list;
        }
        return null;
    }

    @Override
    public List<QaClause> findByCategory(List<String> categoryIds) {
        List<QaClause> byCategory = mapper.findByCategory(categoryIds);
        return byCategory;
    }
}