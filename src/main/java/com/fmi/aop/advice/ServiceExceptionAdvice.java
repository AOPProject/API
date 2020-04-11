package com.fmi.aop.advice;


import com.fmi.aop.dto.CandidateDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceExceptionAdvice {

    @AfterReturning(value="execution(* com.fmi.aop.service.impl.CandidateServiceImpl.*(..))",
            returning="candidateDTO")
    public void afterReturningAdvice(JoinPoint joinPoint, CandidateDTO candidateDTO){
        System.out.println("After Returing method:"+joinPoint.getSignature());
        System.out.println(candidateDTO);
    }

    @AfterThrowing(value="execution(* com.fmi.aop.service.impl.CandidateServiceImpl.*(..))",throwing="exception")
    public void afterThrowingAdvice(JoinPoint joinPoint,Exception exception){
        System.out.println("After Throwing exception in method:"+joinPoint.getSignature());
        System.out.println("Exception is:"+exception.getMessage());
    }
}
