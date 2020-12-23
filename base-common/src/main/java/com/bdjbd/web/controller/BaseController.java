package com.bdjbd.web.controller;

import com.bdjbd.common.i18n.SpringUtils;
import com.bdjbd.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;

/**
  * @className BaseController
  * @description 控制器基类
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public class BaseController<S extends BaseService, T> {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected S baseService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    public String message(String code, Object... args){
        return SpringUtils.getMessage(code, args);
    }
}
