package com.bdjbd.service.auth;

import com.bdjbd.Message;
import com.bdjbd.common.exception.auth.UserInvalidException;
import com.bdjbd.common.util.jwt.IJWTInfo;
import com.bdjbd.common.util.jwt.JWTInfo;
import com.bdjbd.common.vo.LoginUserInfo;
import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.util.user.JwtAuthenticationRequest;
import com.bdjbd.util.user.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
  * @className AuthServiceImpl
  * @description 用户鉴权服务
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SysAdminService adminService;

    /**
     * 用户登录
     *
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @Override
    public String login(JwtAuthenticationRequest authenticationRequest, HttpServletRequest request) throws Exception {
        Message<LoginUserInfo> info = null;
        if("ADMIN".equals(authenticationRequest.getType())){
            info = adminService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword(), authenticationRequest.getCode(), request.getSession().getId());
        }else{
//            info = userInfoService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        }
        LoginUserInfo user = info.getData();
        if (user != null && !StringUtils.isEmpty(user.getId())) {
            return jwtTokenUtil.generateToken(new JWTInfo(user.getUsername(), user.getId(), user.getName()));
        }
        throw new UserInvalidException(StringUtils.isEmpty(info.getMessage()) ? "用户不存在或账户密码错误!" : info.getMessage());
    }

    /**
     * 用户刷新token
     *
     * @param oldToken
     * @return
     * @throws Exception
     */
    @Override
    public String refresh(String oldToken) throws Exception {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }

    /**
     * 用户验证token
     *
     * @param token
     * @throws Exception
     */
    @Override
    public IJWTInfo validate(String token) throws Exception {
        return jwtTokenUtil.getInfoFromToken(token);
    }
}
