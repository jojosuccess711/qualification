package com.bdjbd.aop;

import com.bdjbd.Message;
import com.bdjbd.aop.bo.ValidateResult;
import com.bdjbd.common.i18n.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author dbc
 * @version 1.0
 * @className AutoValidateParam
 * @description TODO
 * @date 2020/1/9
 **/
@Slf4j
@Aspect
@Component
@Order(Integer.MAX_VALUE)
public class AutoValidateParam {

    /**
     * 定义切入点
     */
    @Pointcut("execution(public * com.bdjbd.controller..save(..)) && args(..,bindingResult) || execution(public * com.bdjbd.controller..edit(..)) && args(..,bindingResult)")
    public void cutService(BindingResult bindingResult) {
    }

    /**
     * 在切入点开始处切入内容
     *
     * @param joinPoint
     */
    @Around("cutService(bindingResult)")
    public Object around(ProceedingJoinPoint joinPoint, BindingResult bindingResult) {
        Object result = null;
        // 验证结果
        ValidateResult validateResult = new ValidateResult(Boolean.TRUE, null);
        // 获取所有的请求参数
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Parameter[] parameters = method.getParameters();
            for(Parameter p : parameters){
                // 获取参数上的注解
                Validated validated = p.getAnnotation(Validated.class);
                if(validated == null) {
                    continue;
                }
                if(bindingResult.hasErrors()){
                    String message = SpringUtils.getMessage(bindingResult.getFieldError().getCodes()[0]);
                    validateResult.setStatus(false);
                    validateResult.setContent(message);
                }
                break;
            }
        }
        // 验证通过执行拦截方法，否则不执行
        if (validateResult.getStatus()) {
            try {
                // 执行拦截方法
                result = joinPoint.proceed();
            } catch (Throwable ex) {
                log.error("AOP执行拦截方法时异常, {}", ex);
            }
        } else {
            result = Message.error(validateResult.getContent());
        }
        return result;
    }
}
