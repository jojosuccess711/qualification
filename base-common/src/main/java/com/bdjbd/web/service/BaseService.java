package com.bdjbd.web.service;

import com.bdjbd.Filter;
import com.bdjbd.Order;
import com.bdjbd.Page;
import com.bdjbd.Pageable;

import java.io.Serializable;
import java.util.List;

/**
  * @interfaceName BaseService
  * @description Service 基类
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public interface BaseService<T, ID extends Serializable> {
    /**
     * 通过筛选条件查询对象信息
     * @param filters 条件集合
     * @return T
     */
    T findEntityByFilter(List<Filter> filters);
    /**
     * 查找实体对象
     *
     * @param id
     *            ID
     * @return 实体对象，若不存在则返回null
     */
    T find(ID id);

    /**
     * 查找所有实体对象集合
     *
     * @return 所有实体对象集合
     */
    List<T> findAll();
    /**
     * 查找实体对象集合
     * @param filters 筛选
     * @param orders 排序
     * @return 实体对象集合
     */
    List<T> findList(List<Filter> filters, List<Order> orders);
    /**
     * 查找实体对象集合
     *
     * @param ids
     *            ID
     * @return 实体对象集合
     */
    List<T> findList(ID... ids);

    /**
     * 查找实体对象集合
     *
     * @param count
     *            数量
     * @param filters
     *            筛选
     * @param orders
     *            排序
     * @return 实体对象集合
     */
    List<T> findList(Integer count, List<Filter> filters, List<Order> orders);

    /**
     * 查找实体对象集合
     *
     * @param first
     *            起始记录
     * @param count
     *            数量
     * @param filters
     *            筛选
     * @param orders
     *            排序
     * @return 实体对象集合
     */
    List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders);

    /**
     * 查找实体对象分页
     *
     * @param pageable
     *            分页信息
     * @return 实体对象分页
     */
    Page<T> findPage(Pageable pageable);

    /**
     * 查询实体对象总数
     *
     * @return 实体对象总数
     */
    Integer count(T t);

    /**
     * 查询实体对象数量
     *
     * @param filters
     *            筛选
     * @return 实体对象数量
     */
    Integer count(Filter... filters);

    /**
     * 判断实体对象是否存在
     *
     * @param id
     *            ID
     * @return 实体对象是否存在
     */
    boolean exists(ID id);

    /**
     * 判断实体对象是否存在
     *
     * @param filters
     *            筛选
     * @return 实体对象是否存在
     */
    boolean exists(Filter... filters);

    /**
     * 保存实体对象
     *
     * @param entity
     *            实体对象
     */
    Integer save(T entity);

    /**
     * 保存实体对象列表
     *
     * @param list
     *            实体对象列表
     */
    Integer saveAll(List<T> list);

    /**
     * 更新实体对象
     *
     * @param entity
     *            实体对象
     * @return 实体对象
     */
    Integer update(T entity);

    /**
     * 更新实体对象
     *
     * @param entity
     *            实体对象
     * @param ignoreProperties
     *            忽略属性
     * @return 实体对象
     */
    Integer update(T entity, String... ignoreProperties);

    /**
     * 删除实体对象
     *
     * @param id
     *            ID
     */
    Integer delete(ID id);

    /**
     * 删除实体对象
     *
     * @param ids
     *            ID
     */
    Integer delete(ID... ids);

    /**
     * 删除实体对象
     *
     * @param entity
     *            实体对象
     */
    Integer delete(T entity);
}
