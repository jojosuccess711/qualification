package com.bdjbd.aop.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dbc
 * @version 1.0
 * @className ValidateResult
 * @description TODO
 * @date 2020/1/9
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateResult {

    private Boolean status;

    private String content;
}
