package com.bdjbd.jwt;

import com.bdjbd.common.exception.auth.UserTokenException;
import com.bdjbd.common.util.jwt.IJWTInfo;
import com.bdjbd.common.util.jwt.JWTUtil;
import com.bdjbd.config.UserAuthConfig;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
  * @className UserAuthUtil
  * @description 用户鉴权
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Configuration
public class UserAuthUtil {

    @Autowired
    private UserAuthConfig userAuthConfig;

    /**
     * 通过token获取JWT信息
     * @param token
     * @return
     * @throws Exception
     */
    public IJWTInfo getInfoFromToken(String token) throws Exception {
        try {
            return JWTUtil.getInfoFromToken(token, userAuthConfig.getPubKeyByte());
        }catch (ExpiredJwtException ex){
            throw new UserTokenException("User token expired!");
        }catch (SignatureException ex){
            throw new UserTokenException("User token signature error!");
        }catch (IllegalArgumentException ex){
            throw new UserTokenException("User token is null or empty!");
        }
    }
}
