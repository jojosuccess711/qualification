package com.bdjbd.util.user;

import com.bdjbd.common.util.jwt.IJWTInfo;
import com.bdjbd.common.util.jwt.JWTUtil;
import com.bdjbd.config.KeyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
  * @className JwtTokenUtil
  * @description 用户 token 工具类
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Component
public class JwtTokenUtil {

    @Value("${jwt.expire}")
    private int expire;
    @Autowired
    private KeyConfiguration keyConfiguration;

    /**
     * 通过JWT信息生成token (配置默认过期时间)
     * @param jwtInfo
     * @return String
     * @throws Exception
     */
    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        return JWTUtil.generateToken(jwtInfo, keyConfiguration.getUserPriKey(), expire);
    }

    /**
     * 通过token获取JWT信息
     * @param token
     * @return IJWTInfo
     * @throws Exception
     */
    public IJWTInfo getInfoFromToken(String token) throws Exception {
        return JWTUtil.getInfoFromToken(token, keyConfiguration.getUserPubKey());
    }
}
