package com.bdjbd.web.entity;

import org.apache.commons.lang3.builder.CompareToBuilder;

import javax.persistence.MappedSuperclass;

/**
  * @className OrderEntity
  * @description 排序基类
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
@MappedSuperclass
public abstract class OrderEntity extends BaseEntity implements Comparable<OrderEntity> {

    /** "排序"属性名称 */
    public static final String ORDER_PROPERTY_NAME = "order";

    private Integer orders;

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    /**
     * 实现compareTo方法
     *
     * @param orderEntity
     *            排序对象
     * @return 比较结果
     */
    public int compareTo(OrderEntity orderEntity) {
        return new CompareToBuilder().append(getOrders(), orderEntity.getOrders()).toComparison();
    }
}
