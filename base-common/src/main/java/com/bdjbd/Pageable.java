package com.bdjbd;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
  * @className Pageable
  * @description 分页信息
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public class Pageable implements Serializable {

    /** 默认页码 */
    public static final Integer DEFAULT_PAGE_NUMBER = 1;

    /** 默认每页记录数 */
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    /** 最大每页记录数 */
    public static final Integer MAX_PAGE_SIZE = 1000;

    /** 页码 */
    private Integer pageNum = DEFAULT_PAGE_NUMBER;

    /** 每页记录数 */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /** 是否搜索 */
    private Boolean isSearch;

    /** 搜索属性名称 */
    private String searchField;

    /** 搜索属性值 */
    private String searchString;

    /** 搜索条件公式 */
    private Filter.Operator searchOper;

    /** 排序属性 */
    private String orderProperty;

    /** 排序方向 */
    private Order.Direction orderDirection;

    private String filters;

    private String order;

    /** 筛选 */
    private List<Filter> filterList = new ArrayList<>();

    /** 排序 */
    private List<Order> orders = new ArrayList<>();

    /**
     * 初始化一个新创建的Pageable对象
     */
    public Pageable() {
    }

    /**
     * 初始化一个新创建的Pageable对象
     *
     * @param pageNum
     *            页码
     * @param pageSize
     *            每页记录数
     */
    public Pageable(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageNum >= 1) {
            this.pageNum = pageNum;
        }
        if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
            this.pageSize = pageSize;
        }
    }

    /**
     * 获取页码
     *
     * @return 页码
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * 设置页码
     *
     * @param pageNum
     *            页码
     */
    public void setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum < 1) {
            pageNum = DEFAULT_PAGE_NUMBER;
        }
        this.pageNum = pageNum;
    }

    /**
     * 获取每页记录数
     *
     * @return 每页记录数
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页记录数
     *
     * @param pageSize
     *            每页记录数
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public Boolean getSearch() {
        return isSearch;
    }

    public void setSearch(Boolean search) {
        isSearch = search;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public Filter.Operator getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(Filter.Operator searchOper) {
        this.searchOper = searchOper;
    }

    /**
     * 获取排序属性
     *
     * @return 排序属性
     */
    public String getOrderProperty() {
        return orderProperty;
    }

    /**
     * 设置排序属性
     *
     * @param orderProperty
     *            排序属性
     */
    public void setOrderProperty(String orderProperty) {
        this.orderProperty = orderProperty;
    }

    /**
     * 获取排序方向
     *
     * @return 排序方向
     */
    public Order.Direction getOrderDirection() {
        return orderDirection;
    }

    /**
     * 设置排序方向
     *
     * @param orderDirection
     *            排序方向
     */
    public void setOrderDirection(Order.Direction orderDirection) {
        this.orderDirection = orderDirection;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<Filter> getFilterList() {
        if(getSearch() != null && getSearch() && StringUtils.isNotBlank(getSearchField()) && getSearchOper() != null){
            Filter filter = new Filter(getSearchField(), getSearchOper(), getSearchString());
            this.filterList.add(filter);
        }
        if(StringUtils.isNotBlank(filters)){
            JSONObject jsonObject = JSONObject.parseObject(filters);
            filters = null;
            if(jsonObject != null && jsonObject.get("rules") != null){
                JSONArray rules = (JSONArray)jsonObject.get("rules");
                for(int i = 0 ; i < rules.size(); i++){
                    if(rules.get(i) != null){
                        JSONObject item = (JSONObject)rules.get(i);
                        if(item.get("value") == null || StringUtils.isBlank(item.get("value").toString()))
                            continue;
                        Filter filter = new Filter(item.get("property").toString(), Filter.Operator.fromString(item.get("operator").toString()), item.get("value").toString());
                        this.filterList.add(filter);
                    }
                }
            }
        }
        return filterList;
    }

    public void setFilterList(List<Filter> filterList) {
        this.filterList = filterList;
    }

    /**
     * 获取排序
     *
     * @return 排序
     */
    public List<Order> getOrders() {
        if(StringUtils.isNotBlank(order)){
            JSONObject jsonObject = JSONObject.parseObject(order);
            order = null;
            if(jsonObject != null && jsonObject.get("rules") != null){
                JSONArray rules = (JSONArray)jsonObject.get("rules");
                for(int i = 0 ; i < rules.size(); i++){
                    if(rules.get(i) != null){
                        JSONObject item = (JSONObject)rules.get(i);
                        if(item == null || item.get("property") == null || item.get("direction") == null
                                || StringUtils.isBlank(item.get("property").toString())
                                || StringUtils.isBlank(item.get("direction").toString()))
                            continue;
                        Order order = new Order(item.get("property").toString(), Order.Direction.valueOf(item.get("direction").toString()));
                        this.orders.add(order);
                    }
                }
            }
        }
        return orders;
    }

    /**
     * 设置排序
     *
     * @param orders
     *            排序
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getFirst(){
        return (getPageNum() - 1) * getPageSize();
    }

    public Integer getCount(){
        return getPageSize();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Pageable other = (Pageable) obj;
        return new EqualsBuilder().append(getPageNum(), other.getPageNum()).append(getPageSize(), other.getPageSize()).append(getSearch(), other.getSearch()).append(getSearchField(), other.getSearchField()).append(getSearchString(), other.getSearchString()).append(getSearchOper(), other.getSearchOper()).append(getOrderProperty(), other.getOrderProperty()).append(getOrderDirection(), other.getOrderDirection()).append(getFilters(), other.getFilters())
                .append(getOrders(), other.getOrders()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPageNum()).append(getPageSize()).append(getSearch()).append(getSearchField()).append(getSearchString()).append(getSearchOper()).append(getOrderProperty()).append(getOrderDirection()).append(getFilters()).append(getOrders()).toHashCode();
    }
}
