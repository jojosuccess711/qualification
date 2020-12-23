package com.bdjbd.web.service.impl;

import com.bdjbd.Filter;
import com.bdjbd.Order;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.web.dao.BaseDao;
import com.bdjbd.web.entity.BaseEntity;
import com.bdjbd.web.service.BaseService;
import com.github.pagehelper.PageRowBounds;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 **/
@Transactional
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    /**
     * 更新忽略属性
     */
    private static final String[] UPDATE_IGNORE_PROPERTIES = new String[]{BaseEntity.ID_PROPERTY_NAME, BaseEntity.CREATE_DATE_PROPERTY_NAME, BaseEntity.MODIFY_DATE_PROPERTY_NAME};

    /**
     * like 操作符
     */
    private static final String LIKE_OPERATOR = "%";

    /**
     * baseDao
     */
    private BaseDao<T> baseDao;

    /**
     * 实体类类型
     */
    private Class<T> entityClass;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    public BaseServiceImpl() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 通过筛选条件查询对象信息
     * @param filters 条件集合
     * @return T
     */
    @Override
    public T findEntityByFilter(List<Filter> filters) {
        Example example = addFilters(filters.toArray(new Filter[]{}));
        return baseDao.selectOneByExample(example);
    }

    /**
     * 查找实体对象
     * @param id ID
     * @return 实体对象，若不存在则返回null
     */
    @Override
    public T find(ID id) {
        return baseDao.selectByPrimaryKey(id);
    }

    /**
     * 查找所有实体对象集合
     * @return 所有实体对象集合
     */
    @Override
    public List<T> findAll() {
        return baseDao.selectAll();
    }

    /**
     * 查找实体对象集合
     * @param filters 筛选
     * @param orders 排序
     * @return 实体对象集合
     */
    @Override
    public List<T> findList(List<Filter> filters, List<Order> orders) {
        Example example = addFilters(filters.toArray(new Filter[]{}));
        addOrders(orders, example);
        return baseDao.selectByExample(example);
    }

    /**
     * 查找实体对象集合
     * @param ids ID
     * @return 实体对象集合
     */
    @Override
    public List<T> findList(ID... ids) {
        List<T> result = new ArrayList<T>();
        if (ids != null) {
            for (ID id : ids) {
                T entity = find(id);
                if (entity != null) {
                    result.add(entity);
                }
            }
        }
        return result;
    }

    /**
     * 查找实体对象集合
     * @param count 数量
     * @param filters 筛选
     * @param orders 排序
     * @return 实体对象集合
     */
    @Override
    public List<T> findList(Integer count, List<Filter> filters, List<Order> orders) {
        Example example = addFilters(filters.toArray(new Filter[]{}));
        addOrders(orders, example);
        RowBounds rowBounds = new RowBounds(0, count);
        return baseDao.selectByExampleAndRowBounds(example, rowBounds);
    }

    /**
     * 查找实体对象集合
     * @param first 起始记录
     * @param count 数量
     * @param filters 筛选
     * @param orders 排序
     * @return 实体对象集合
     */
    @Override
    public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) {
        Example example = addFilters(filters == null ? null : filters.toArray(new Filter[]{}));
        addOrders(orders, example);
        RowBounds rowBounds = new RowBounds(first, count);
        return baseDao.selectByExampleAndRowBounds(example, rowBounds);
    }

    /**
     * 查找实体对象分页
     * @param pageable 分页信息
     * @return 实体对象分页
     */
    @Override
    public Page<T> findPage(Pageable pageable) {
        Example example = addFilters(pageable.getFilterList().toArray(new Filter[]{}));
        addOrders(pageable.getOrders(), example);
        addOrder(pageable, example);
        PageRowBounds pageRowBounds = new PageRowBounds((pageable.getPageNum() - 1) * pageable.getPageSize(), pageable.getPageSize());
        List<T> ts = baseDao.selectByExampleAndRowBounds(example, pageRowBounds);
        return new Page<>(ts, pageRowBounds.getTotal(), pageable);
    }

    /**
     * 查询实体对象总数
     * @return 实体对象总数
     */
    @Override
    public Integer count(T t) {
        return baseDao.selectCount(t);
    }

    /**
     * 查询实体对象数量
     * @param filters 筛选
     * @return 实体对象数量
     */
    @Override
    public Integer count(Filter... filters) {
        return baseDao.selectCountByExample(addFilters(filters));
    }

    /**
     * 判断实体对象是否存在
     * @param id ID
     * @return 实体对象是否存在
     */
    @Override
    public boolean exists(ID id) {
        return find(id) != null;
    }

    /**
     * 判断实体对象是否存在
     * @param filters 筛选
     * @return 实体对象是否存在
     */
    @Override
    public boolean exists(Filter... filters) {
        return baseDao.selectCountByExample(addFilters(filters)) > 0;
    }

    /**
     * 保存实体对象
     * @param entity 实体对象
     */
    @Override
    public Integer save(T entity) {
        return baseDao.insert(entity);
    }

    /**
     * 保存实体对象列表
     * @param list 实体对象列表
     */
    @Override
    public Integer saveAll(List<T> list) {
        return baseDao.insertList(list);
    }

    /**
     * 更新实体对象
     * @param entity 实体对象
     * @return 实体对象
     */
    @Override
    public Integer update(T entity) {
        return baseDao.updateByPrimaryKey(entity);
    }

    /**
     * 更新实体对象
     * @param entity 实体对象
     * @param ignoreProperties 忽略属性
     * @return 实体对象
     */
    @Override
    public Integer update(T entity, String... ignoreProperties) {
        for (String filed : ignoreProperties) {
            try {
                Field field = entity.getClass().getDeclaredField(filed);
                field.setAccessible(true);
                field.set(entity, null);
            } catch (Exception e) {
            }
        }
        return baseDao.updateByPrimaryKeySelective(entity);
    }

    /**
     * 删除实体对象
     * @param id ID
     */
    @Override
    public Integer delete(ID id) {
        return baseDao.deleteByPrimaryKey(id);
    }

    /**
     * 删除实体对象
     * @param ids ID
     */
    @Override
    public Integer delete(ID... ids) {
        Integer count = 0;
        if (ids != null) {
            for (ID id : ids) {
                count += delete(id);
            }
        }
        return count;
    }

    /**
     * 删除实体对象
     * @param entity 实体对象
     */
    @Override
    public Integer delete(T entity) {
        return baseDao.delete(entity);
    }

    private Example addFilters(Filter... filters) {
        Example example = new Example(entityClass);
        if (filters != null) {
            Example.Criteria criteria = example.createCriteria();
            for (Filter filter : filters) {
                if (filter == null || StringUtils.isBlank(filter.getProperty()))
                    continue;
                filterHandler(example, criteria, filter, true);
            }
        }
        return example;
    }

    private void filterHandler(Example example, Example.Criteria criteria, Filter filter, Boolean hasAnd) {
        if (filter.getOperator() == Filter.Operator.eq && filter.getValue() != null) {
            if (hasAnd)
                criteria.andEqualTo(filter.getProperty(), filter.getValue());
            else
                criteria.orEqualTo(filter.getProperty(), filter.getValue());
        }
        else if (filter.getOperator() == Filter.Operator.ne && filter.getValue() != null) {
            if (hasAnd)
                criteria.andNotEqualTo(filter.getProperty(), filter.getValue());
            else
                criteria.orNotEqualTo(filter.getProperty(), filter.getValue());
        }
        else if (filter.getOperator() == Filter.Operator.gt && filter.getValue() != null) {
            if (hasAnd)
                criteria.andGreaterThan(filter.getProperty(), filter.getValue());
            else
                criteria.orGreaterThan(filter.getProperty(), filter.getValue());
        }
        else if (filter.getOperator() == Filter.Operator.lt && filter.getValue() != null) {
            if (hasAnd)
                criteria.andLessThan(filter.getProperty(), filter.getValue());
            else
                criteria.orLessThan(filter.getProperty(), filter.getValue());
        }
        else if (filter.getOperator() == Filter.Operator.ge && filter.getValue() != null) {
            if (hasAnd)
                criteria.andGreaterThanOrEqualTo(filter.getProperty(), filter.getValue());
            else
                criteria.orGreaterThanOrEqualTo(filter.getProperty(), filter.getValue());
        }
        else if (filter.getOperator() == Filter.Operator.le && filter.getValue() != null) {
            if (hasAnd)
                criteria.andLessThanOrEqualTo(filter.getProperty(), filter.getValue());
            else
                criteria.orLessThanOrEqualTo(filter.getProperty(), filter.getValue());
        }
        else if ((filter.getOperator() == Filter.Operator.like || filter.getOperator() == Filter.Operator.cn) && filter.getValue() != null && filter.getValue() instanceof String) {
            if (hasAnd)
                criteria.andLike(filter.getProperty(), LIKE_OPERATOR + filter.getValue().toString() + LIKE_OPERATOR);
            else
                criteria.orLike(filter.getProperty(), LIKE_OPERATOR + filter.getValue().toString() + LIKE_OPERATOR);
        }
        else if ((filter.getOperator() == Filter.Operator.notLike || filter.getOperator() == Filter.Operator.nc) && filter.getValue() != null && filter.getValue() instanceof String) {
            if (hasAnd)
                criteria.andNotLike(filter.getProperty(), LIKE_OPERATOR + filter.getValue().toString() + LIKE_OPERATOR);
            else
                criteria.orNotLike(filter.getProperty(), LIKE_OPERATOR + filter.getValue().toString() + LIKE_OPERATOR);
        }
        else if (filter.getOperator() == Filter.Operator.in && filter.getValue() != null && filter.getValue() instanceof List) {
            if (hasAnd)
                criteria.andIn(filter.getProperty(), (List) filter.getValue());
            else
                criteria.orIn(filter.getProperty(), (List) filter.getValue());
        }
        else if ((filter.getOperator() == Filter.Operator.notIn || filter.getOperator() == Filter.Operator.ni) && filter.getValue() != null && filter.getValue() instanceof List) {
            if (hasAnd)
                criteria.andNotIn(filter.getProperty(), (List) filter.getValue());
            else
                criteria.orNotIn(filter.getProperty(), (List) filter.getValue());
        }
        else if (filter.getOperator() == Filter.Operator.isNull) {
            if (hasAnd)
                criteria.andIsNull(filter.getProperty());
            else
                criteria.orIsNull(filter.getProperty());
        }
        else if (filter.getOperator() == Filter.Operator.isNotNull) {
            if (hasAnd)
                criteria.andIsNotNull(filter.getProperty());
            else
                criteria.orIsNotNull(filter.getProperty());
        }
        else if (filter.getOperator() == Filter.Operator.between && filter.getValue() != null && filter.getValue() instanceof List) {
            if (hasAnd)
                criteria.andBetween(filter.getProperty(), ((List) filter.getValue()).get(0), ((List) filter.getValue()).get(1));
            else
                criteria.orBetween(filter.getProperty(), ((List) filter.getValue()).get(0), ((List) filter.getValue()).get(1));
        }
        else if (filter.getOperator() == Filter.Operator.notBetween && filter.getValue() != null && filter.getValue() instanceof List) {
            if (hasAnd)
                criteria.andNotBetween(filter.getProperty(), ((List) filter.getValue()).get(0), ((List) filter.getValue()).get(1));
            else
                criteria.orNotBetween(filter.getProperty(), ((List) filter.getValue()).get(0), ((List) filter.getValue()).get(1));
        }
        else if (filter.getOperator() == Filter.Operator.bw && filter.getValue() != null && filter.getValue() instanceof String) {
            if (hasAnd)
                criteria.andLike(filter.getProperty(), filter.getValue().toString() + LIKE_OPERATOR);
            else
                criteria.orLike(filter.getProperty(), filter.getValue().toString() + LIKE_OPERATOR);
        }
        else if (filter.getOperator() == Filter.Operator.ew && filter.getValue() != null && filter.getValue() instanceof String) {
            if (hasAnd)
                criteria.andLike(filter.getProperty(), LIKE_OPERATOR + filter.getValue().toString());
            else
                criteria.orLike(filter.getProperty(), LIKE_OPERATOR + filter.getValue().toString());
        }
        else if (filter.getOperator() == Filter.Operator.bn && filter.getValue() != null && filter.getValue() instanceof String) {
            if (hasAnd)
                criteria.andNotLike(filter.getProperty(), filter.getValue().toString() + LIKE_OPERATOR);
            else
                criteria.orNotLike(filter.getProperty(), filter.getValue().toString() + LIKE_OPERATOR);
        }
        else if (filter.getOperator() == Filter.Operator.en && filter.getValue() != null && filter.getValue() instanceof String) {
            if (hasAnd)
                criteria.andNotLike(filter.getProperty(), LIKE_OPERATOR + filter.getValue().toString());
            else
                criteria.orNotLike(filter.getProperty(), LIKE_OPERATOR + filter.getValue().toString());
        }
        else if (filter.getOperator() == Filter.Operator.or && filter.getValue() != null && filter.getValue() instanceof List) {
            orListHandler(example, (List<Filter>) filter.getValue());
        }
    }

    private void orListHandler(Example example, List<Filter> filters) {
        Example.Criteria temp = example.createCriteria();
        for (Iterator<Filter> iterator = filters.iterator(); iterator.hasNext(); ) {
            Filter next = iterator.next();
            filterHandler(example, temp, next, false);
        }
        example.and(temp);
    }

    private void addOrders(List<Order> orders, Example example) {
        if (orders != null) {
            for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext(); ) {
                Order order = iterator.next();
                if (order == null || StringUtils.isBlank(order.getProperty())) {
                    continue;
                }
                if (order.getDirection() == Order.Direction.asc) {
                    example.orderBy(order.getProperty()).asc();
                }
                else if (order.getDirection() == Order.Direction.desc) {
                    example.orderBy(order.getProperty()).desc();
                }
            }
        }
    }

    private void addOrder(Pageable pageable, Example example) {
        if (StringUtils.isNotBlank(pageable.getOrderProperty()) && pageable.getOrderDirection() != null) {
            if (pageable.getOrderDirection() == Order.Direction.asc) {
                example.orderBy(pageable.getOrderProperty()).asc();
            }
            else if (pageable.getOrderDirection() == Order.Direction.desc) {
                example.orderBy(pageable.getOrderProperty()).desc();
            }
        }
    }
}
