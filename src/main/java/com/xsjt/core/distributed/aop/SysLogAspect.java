//package com.xsjt.core.distributed.aop;
//
//import com.alibaba.fastjson.JSON;
//import com.xsjt.core.jackson.JsonUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
///**
// * @author Harriss
// * @Date 2021/11/18 11:36
// * @Desc 系统日志：切面处理类
// */
//@Aspect
//@Component
//@Slf4j
//public class SysLogAspect {
//
//    @Pointcut("execution(public * com.xsjt.order.controller.*.*(..))")
//    public void webLog(){
//    }
//
//    @Around("webLog()")
//    public Object saveSysLog(ProceedingJoinPoint proceedingJoinPoint) {
//        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//        Method method = signature.getMethod();
//        String className = proceedingJoinPoint.getTarget().getClass().getName();
//        String methodName = method.getName();
//        //请求的参数
//        Object[] args = proceedingJoinPoint.getArgs();
//        String params = JSON.toJSONString(args);
//        log.info(className + "--" + methodName + "\tparams:{}",params);
//        Object result =null;
//        try {
//            result = proceedingJoinPoint.proceed();
//            log.info(className + "--" + methodName + "\tresult:{}", JsonUtil.toJson(result));
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            log.info("throwable:{}",throwable.getMessage());
//        }
//        return result;
//    }
//}
