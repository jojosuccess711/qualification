package com.bdjbd.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
  * @className LoginUserInfo
  * @description 用户信息
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserInfo implements Serializable{
    public String id;
    public String username;
    public String password;
    public String name;
    private String description;
}
