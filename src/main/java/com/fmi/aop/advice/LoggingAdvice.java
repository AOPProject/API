package com.fmi.aop.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value="execution(* com.fmi.aop.*.*.*(..) )")
    public void myPointcut() {}

    @Around("myPointcut()")
    public Object applicationLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object[] array = joinPoint.getArgs();
        log.info("method invoked {} : {}() arguments : {}", className, methodName, mapper.writeValueAsString(array) );
        Object object = joinPoint.proceed();
        log.info("{} : {}()" + "Response : {}" ,className,methodName, mapper.writeValueAsString(object));
        return object;
    }
}
