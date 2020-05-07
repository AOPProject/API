package com.fmi.aop.advice;


import com.fmi.aop.dto.InterviewerDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InterviewerServiceAspect {

    private Logger log = LoggerFactory.getLogger(InterviewerServiceAspect.class);

    @AfterReturning(value="execution(* com.fmi.aop.service.impl.InterviewerServiceImpl.*(..))",
            returning="interviewerDTO")
    public void afterReturningAdvice(JoinPoint joinPoint, InterviewerDTO interviewerDTO){
        log.info("Returning with Candidate id: {}", interviewerDTO.getId());
    }


    @Around(value="execution(* com.fmi.aop.service.impl.InterviewerServiceImpl.*(..))" +
            " && !within(com.fmi.aop.service.impl.JwtUserDetailsService)")
    public void AroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable exception) {
            log.info("Exception message is: {}", exception.getMessage());
        }
    }
}