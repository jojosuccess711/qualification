package com.bdjbd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
  * @className Page
  * @description 分页信息
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public class SimplePage<T> implements Serializable {

    /** 内容 */
    private final List<T> content = new ArrayList<T>();

    /** 总记录数 */
    private final Long total;

    /** 分页信息 */
    private final SimplePageable simplePageable;

    /**
     * 初始化一个新创建的Page对象
     */
    public SimplePage() {
        this.total = 0L;
        this.simplePageable = new SimplePageable();
    }

    /**
     * @param content
     *            内容
     * @param total
     *            总记录数
     * @param simplePageable
     *            分页信息
     */
    public SimplePage(List<T> content, long total, SimplePageable simplePageable) {
        this.content.addAll(content);
        this.total = total;
        this.simplePageable = simplePageable;
    }

    /**
     * 获取页码
     *
     * @return 页码
     */
    public int getPageNum() {
        return simplePageable.getPageNum();
    }

    /**
     * 获取每页记录数
     *
     * @return 每页记录数
     */
    public int getPageSize() {
        return simplePageable.getPageSize();
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    public int getTotalPages() {
        return (int) Math.ceil((double) getTotal() / (double) getPageSize());
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public List<T> getContent() {
        return content;
    }

    /**
     * 获取总记录数
     *
     * @return 总记录数
     */
    public long getTotal() {
        return total;
    }

    /**
     * 获取分页信息
     *
     * @return 分页信息
     */
    public SimplePageable getSimplePageable() {
        return simplePageable;
    }
}
