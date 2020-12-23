package com.bdjbd.bo;

import com.bdjbd.dao.entity.QaStandardRecordItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className ResultClauseItem 匹配结果（条件子项【分组】）
 * @description TODO
 * @date 2020/3/4
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultClauseItem {

    /** 条件子项（A1 B1 C1 。。。） */
    private String clauseItemCode;

    /** 匹配结果 */
    private Boolean result;

    /** 匹配结果数量 */
    private Integer count;

    /** 要求项 */
    private ClauseItem clauseItem;

    /** 填入标准项 */
    private List<QaStandardRecordItem> qaStandardRecordItems;
}
