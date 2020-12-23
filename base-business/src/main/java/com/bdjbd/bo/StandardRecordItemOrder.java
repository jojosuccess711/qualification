package com.bdjbd.bo;

import com.bdjbd.dao.entity.QaStandardRecordItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zhuzhe
 * @date 2020/4/20 18:33
 * @email zhuzhe_mail@163.com
 */
@Data
public class StandardRecordItemOrder {

    public static final String ASC = "asc";
    public static final String DESC = "desc";

    private Integer order;

    // 排序时间
    private Date orderTime;

    // asc desc
    private String orderType;

    private List<QaStandardRecordItem> items;
}
