package com.bdjbd.bo;

import com.bdjbd.service.matches.util.CheckResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
public class ResultClauseItemGroup extends CheckResult {

    /** 通过项 */
    private List<ResultClauseItem> pass = new ArrayList<>();

    /** 不通过项 */
    private List<ResultClauseItem> noPass = new ArrayList<>();
}
