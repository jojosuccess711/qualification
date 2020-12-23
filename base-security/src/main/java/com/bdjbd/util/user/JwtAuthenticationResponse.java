package com.bdjbd.util.user;

import java.io.Serializable;

/**
  * @className JwtAuthenticationResponse
  * @description 鉴权响应 Model
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class JwtAuthenticationResponse implements Serializable {

    private final String token;

    /**
     * 全参构造
     * @param token
     */
    public JwtAuthenticationResponse(String token) {
            this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
