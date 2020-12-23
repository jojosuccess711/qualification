package com.bdjbd.interceptor;

import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.common.context.BaseContextHandler;
import com.bdjbd.common.util.jwt.IJWTInfo;
import com.bdjbd.config.UserAuthConfig;
import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.jwt.UserAuthUtil;
import com.bdjbd.service.sys.SysAuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
  * @className UserAuthRestInterceptor
  * @description 用户鉴权拦截器
  * @author dbc
  * @date 2019/7/9
  * @version 1.0
  **/
@Slf4j
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserAuthUtil userAuthUtil;
    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private SysAuthorityService authorityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 配置该注解，说明不进行用户拦截
            IgnoreAuthority annotation = handlerMethod.getBeanType().getAnnotation(IgnoreAuthority.class);
            if (annotation == null) {
                annotation = handlerMethod.getMethodAnnotation(IgnoreAuthority.class);
            }
            if (annotation != null) {
                return super.preHandle(request, response, handler);
            }
        }

        String token = request.getHeader(userAuthConfig.getTokenHeader());
        if (StringUtils.isEmpty(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(userAuthConfig.getTokenHeader())) {
                        token = cookie.getValue();
                    }
                }
            }
        }

        try{
            IJWTInfo infoFromToken = userAuthUtil.getInfoFromToken(token);
            List<SysAuthority> authorities = authorityService.getByAdminId(infoFromToken.getId());
            PathMatcher pathMatcher = new AntPathMatcher();
            boolean match = false;
            loop:for(Iterator<SysAuthority> iterator = authorities.iterator(); iterator.hasNext();){
                SysAuthority next = iterator.next();
                if(next != null && next.getUrl() != null){
                    String[] split = next.getUrl().split(",");
                    for(String item : split){
                        if(StringUtils.isBlank(item))
                            continue;
                        match = pathMatcher.match(item, request.getRequestURI());
                        if(match)
                            break loop;
                    }
                }
            }
            if(!match){
                RequestDispatcher view = request.getRequestDispatcher("/error/authority");
                view.forward(request, response);
                return false;
            }
        }catch (Exception e){
            log.error("权限处理异常", e);
            RequestDispatcher view = request.getRequestDispatcher("/error/authority");
            view.forward(request, response);
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
