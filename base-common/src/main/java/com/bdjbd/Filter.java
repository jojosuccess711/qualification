package com.bdjbd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
  * @className Filter
  * @description 查询过滤
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public class Filter implements Serializable {

    /**
     * 运算符
     */
    public enum Operator {

        /** 等于 */
        eq,

        /** 不等于 */
        ne,

        /** 大于 */
        gt,

        /** 小于 */
        lt,

        /** 大于等于 */
        ge,

        /** 小于等于 */
        le,

        /** 相似 */
        like,

        /** 不相似 */
        notLike,

        /** 包含 */
        in,

        /** 不包含 */
        notIn,

        /** 为Null */
        isNull,

        /** 不为Null */
        isNotNull,

        /** 两者之间 （value 为 List） */
        between,

        /** 不在两者之间 （value 为 List） */
        notBetween,

        /** begins with */
        bw,

        /** does not begin with */
        bn,

        /** is not in */
        ni,

        /** ends with */
        ew,

        /** does not end with */
        en,

        /** contains */
        cn,

        /** does not contain */
        nc,

        dateYMDEq,

        or;

        /**
         * 从String中获取Operator
         *
         * @param value
         *            值
         * @return String对应的operator
         */
        public static Operator fromString(String value) {
            return Operator.valueOf(value.toLowerCase());
        }
    }

    /** 属性 */
    private String property;

    /** 运算符 */
    private Operator operator;

    /** 值 */
    private Object value;

    /**
     * 初始化一个新创建的Filter对象
     */
    public Filter() {
    }

    /**
     * 初始化一个新创建的Filter对象
     *
     * @param property
     *            属性
     * @param operator
     *            运算符
     * @param value
     *            值
     */
    public Filter(String property, Operator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public static Filter filter(String property, Operator operator, Object value){
        return new Filter(property, operator, value);
    }

    /**
     * 返回等于筛选
     *
     * @param property
     *            属性
     * @param value
     *            值
     * @return 等于筛选
     */
    public static Filter eq(String property, Object value) {
        return new Filter(property, Operator.eq, value);
    }

    /**
     * 返回不等于筛选
     *
     * @param property
     *            属性
     * @param value
     *            值
     * @return 不等于筛选
     */
    public static Filter ne(String property, Object value) {
        return new Filter(property, Operator.ne, value);
    }

    /**
     * 返回大于筛选
     *
     * @param property
     *            属性
     * @param value
     *            值
     * @return 大于筛选
     */
    public static Filter gt(String property, Object value) {
        return new Filter(property, Operator.gt, value);
    }

    /**
     * 返回小于筛选
     *
     * @param property
     *            属性
     * @param value
     *            值
     * @return 小于筛选
     */
    public static Filter lt(String property, Object value) {
        return new Filter(property, Operator.lt, value);
    }

    /**
     * 返回大于等于筛选
     *
     * @param property
     *            属性
     * @param value
     *            值
     * @return 大于等于筛选
     */
    public static Filter ge(String property, Object value) {
        return new Filter(property, Operator.ge, value);
    }

    /**
     * 返回小于等于筛选
     *
     * @param property
     *            属性
     * @param value
     *            值
     * @return 小于等于筛选
     */
    public static Filter le(String property, Object value) {
        return new Filter(property, Operator.le, value);
    }

    /**
     * 返回相似筛选
     *
     * @param property
     *            属性
     * @param value
     *            值
     * @return 相似筛选
     */
    public static Filter like(String property, Object value) {
        return new Filter(property, Operator.like, value);
    }

    /**
     * 返回包含筛选
     *
     * @param property
     *            属性
     * @param value
     *            值
     * @return 包含筛选
     */
    public static Filter in(String property, Object value) {
        return new Filter(property, Operator.in, value);
    }

    /**
     * 返回为Null筛选
     *
     * @param property
     *            属性
     * @return 为Null筛选
     */
    public static Filter isNull(String property) {
        return new Filter(property, Operator.isNull, null);
    }

    /**
     * 返回不为Null筛选
     *
     * @param property
     *            属性
     * @return 不为Null筛选
     */
    public static Filter isNotNull(String property) {
        return new Filter(property, Operator.isNotNull, null);
    }

    /**
     * or
     * @param filters
     * @return
     */
    public static Filter or(List<Filter> filters) {
        return new Filter(Operator.or.name(), Operator.or, filters);
    }

    /**
     * 获取属性
     *
     * @return 属性
     */
    public String getProperty() {
        return property;
    }

    /**
     * 设置属性
     *
     * @param property
     *            属性
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * 获取运算符
     *
     * @return 运算符
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * 设置运算符
     *
     * @param operator
     *            运算符
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public Object getValue() {
        return value;
    }

    /**
     * 设置值
     *
     * @param value
     *            值
     */
    public void setValue(Object value) {
        this.value = value;
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
        Filter other = (Filter) obj;
        return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getOperator(), other.getOperator()).append(getValue(), other.getValue()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
    }
}
