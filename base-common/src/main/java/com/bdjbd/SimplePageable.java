package com.bdjbd;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
  * @className SimplePageable
  * @description 简单分页信息
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public class SimplePageable implements Serializable {

    /** 默认页码 */
    private static final Integer DEFAULT_PAGE_NUMBER = 1;

    /** 默认每页记录数 */
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    /** 最大每页记录数 */
    private static final Integer MAX_PAGE_SIZE = 1000;

    /** 页码 */
    private Integer pageNum = DEFAULT_PAGE_NUMBER;

    /** 每页记录数 */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 初始化一个新创建的Pageable对象
     */
    public SimplePageable() {
    }

    /**
     * 初始化一个新创建的Pageable对象
     *
     * @param pageNum
     *            页码
     * @param pageSize
     *            每页记录数
     */
    public SimplePageable(Integer pageNum, Integer pageSize) {
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
        if (pageNum < 1) {
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
        if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
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
        SimplePageable other = (SimplePageable) obj;
        return new EqualsBuilder().append(getPageNum(), other.getPageNum()).append(getPageSize(), other.getPageSize()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPageNum()).append(getPageSize()).toHashCode();
    }
}
