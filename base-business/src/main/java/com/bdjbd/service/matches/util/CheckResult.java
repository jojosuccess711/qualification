package com.bdjbd.service.matches.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dbc
 * @version 1.0
 * @className CheckResult
 * @description TODO
 * @date 2020/3/3
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckResult {

    /** 条件子项（A1 B1 C1 。。。） */
    private String relationCode;

    /** 条件结果 */
    private Boolean result;
}
