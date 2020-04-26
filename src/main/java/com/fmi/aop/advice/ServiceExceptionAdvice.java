package com.fmi.aop.advice;


import com.fmi.aop.dto.CandidateDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceExceptionAdvice {

    private Logger log = LoggerFactory.getLogger(ServiceExceptionAdvice.class);

    @AfterReturning(value="execution(* com.fmi.aop.service.impl.CandidateServiceImpl.*(..))",
            returning="candidateDTO")
    public void afterReturningAdvice(JoinPoint joinPoint, CandidateDTO candidateDTO){
        log.info("Returning with Candidate id: {}", candidateDTO.getId());
    }


    @AfterThrowing(value="execution(* com.fmi.aop.service.impl.*.*(..))" +
            " && !within(com.fmi.aop.service.impl.JwtUserDetailsService)",
            throwing="exception")
    public void afterThrowingAdvice(JoinPoint joinPoint,Exception exception){
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        log.info("Throwing exception from method {} of class {}", methodName, className);
        log.info("Exception message is: {}", exception.getMessage());
    }
}
