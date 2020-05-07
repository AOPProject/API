package com.fmi.aop.advice;

import com.fmi.aop.dto.CandidateDTO;
import com.fmi.aop.event.OnCandidateArrivalEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.servlet.http.HttpServletRequest;

public class NotificationAspect {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private Logger log = LoggerFactory.getLogger(NotificationAspect.class);

    @AfterReturning(value="execution(* com.fmi.aop.service.impl.CandidateServiceImpl.getCandidateByEmail(..))",
            returning="candidateDTO", argNames = "candidateDTO, request")
    public void afterReturningAdvice(JoinPoint joinPoint, CandidateDTO candidateDTO, HttpServletRequest request){
        log.info(" Candidate with id {} arrived! Notifying interviewer!", candidateDTO.getId());
        log.info(" Email notification event from method {}!", joinPoint.getSignature().getName());
        eventPublisher.publishEvent(new OnCandidateArrivalEvent(candidateDTO, request.getLocale(), getAppUrl(request)));
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
