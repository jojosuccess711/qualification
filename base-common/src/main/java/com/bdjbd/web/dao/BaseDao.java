package com.bdjbd.web.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.example.SelectOneByExampleMapper;
import tk.mybatis.mapper.common.rowbounds.SelectByExampleRowBoundsMapper;
import tk.mybatis.mapper.common.rowbounds.SelectRowBoundsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author dbc
 * @version 1.0
 **/
public interface BaseDao<T> extends Mapper<T>,
        InsertListMapper<T>, SelectByExampleRowBoundsMapper<T>, SelectRowBoundsMapper<T>,
        SelectOneByExampleMapper<T>,
        SelectByExampleMapper<T> {

}
