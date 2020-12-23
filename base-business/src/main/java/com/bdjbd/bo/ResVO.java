package com.bdjbd.bo;

import com.bdjbd.Page;
import com.bdjbd.dao.entity.BaseParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 8:11 PM 2020/5/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResVO {


    private Page<BaseParameter> resultPage;

    private boolean isShow = true;
}
