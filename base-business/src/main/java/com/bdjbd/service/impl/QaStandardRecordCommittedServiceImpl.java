/**
 * @filename:QaStandardRecordCommittedServiceImpl 2020年4月9日
 * @project 职称评审系统  V1.0
 * Copyright(c) 2018 songzekun Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import com.bdjbd.Filter;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaStandardRecordCommitted;
import com.bdjbd.dao.mapper.QaStandardRecordCommittedMapper;
import com.bdjbd.service.QaStandardRecordCommittedService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Description: 已提交的用户信息 —— SERVICEIMPL
 * @Author: songzekun
 * @CreateDate: 2020年4月9日
 * @Version: V1.0
 *
 */
@Slf4j
@Service
public class QaStandardRecordCommittedServiceImpl extends BaseServiceImpl<QaStandardRecordCommitted, String> implements QaStandardRecordCommittedService {

    @Autowired
    private QaStandardRecordCommittedMapper mapper;

    @Autowired
    public void setBaseDao(QaStandardRecordCommittedMapper mapper) {
        super.setBaseDao(mapper);
    }

    @Override
    public Page<QaStandardRecordCommitted> findCommittedRecords(Integer pageNum, Integer pageSize, String name, String category, String categoryType, String technologyCategory, String attr0) {
        Pageable pageable = new Pageable(pageNum, pageSize);

        List<Filter> filters = new ArrayList<>();
        setFilter(filters,name, category, categoryType, technologyCategory, attr0);
        pageable.setFilterList(filters);
        return findPage(pageable);
    }
    private void setFilter(List<Filter> filters,String name, String category, String categoryType, String technologyCategory, String attr0){
        if(!StringUtils.isEmpty(name)){
            filters.add(Filter.eq("name",name));
        }
        if(!StringUtils.isEmpty(category)){
            filters.add(Filter.eq("category",category));
        }
        if(!StringUtils.isEmpty(categoryType)){
            filters.add(Filter.eq("categoryType",categoryType));
        }
        if(!StringUtils.isEmpty(technologyCategory)){
            filters.add(Filter.eq("technologyCategory",technologyCategory));
        }
        if(!StringUtils.isEmpty(attr0)){
            filters.add(Filter.eq("attr0",attr0));
        }
    }
}