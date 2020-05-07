package com.fmi.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLoggingAspect {

    private final Logger log = LoggerFactory.getLogger(PerformanceLoggingAspect.class);

    @Pointcut(value="execution(* com.fmi.aop.*.*.*())" +
            " && !execution(* org.springframework.web.filter.GenericFilterBean.*())")
    public void myPointcut() {}

    @Around("myPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Total time of method execution in ms : {}",(endTime-startTime));
        return object;
    }

    @Before("myPointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object[] array = joinPoint.getArgs();
        log.info("Executing method {} of class {} with the following parameters",methodName, className);
        for(Object parameter : array){
            log.info("{} = {} ", parameter.getClass().getName(), parameter );
        }
    }

    @After("myPointcut()")
    public void afterAdvice(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        log.info("Returning from method {} of class {}", methodName, className);
    }
}