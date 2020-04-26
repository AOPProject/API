package com.fmi.aop.service;

import com.fmi.aop.dto.InterviewDTO;

import java.util.Set;

public interface IInterviewService {
    Set<InterviewDTO> getInterviewByCandidateEmail(String email);
}
