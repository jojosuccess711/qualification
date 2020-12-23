package com.bdjbd.bo;

import com.bdjbd.dao.entity.QaStandardRecordItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className RecordItemGroup
 * @description TODO
 * @date 2020/4/23
 **/
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordItemGroup implements Serializable {

    //条件组
    private List<QaStandardRecordItem> items = new ArrayList<>();
    //条件组序号
    private Integer orders;
    //参数组id
    private String parameterGroupId;
}
