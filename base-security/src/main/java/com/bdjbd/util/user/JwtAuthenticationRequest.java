package com.bdjbd.util.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
  * @className JwtAuthenticationRequest
  * @description 鉴权请求 Model
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationRequest implements Serializable {

    private String type;
    private String username;
    private String password;
    private String code;
    private String jsonParams;
}
