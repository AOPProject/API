package com.fmi.aop.service;

import com.fmi.aop.dto.ChangeInterviewScoreDto;
import com.fmi.aop.dto.InterviewDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public interface IInterviewService {
    Set<InterviewDTO> getInterviewByCandidateEmail(String email);

    Set<InterviewDTO> getInterviewByDate(LocalDateTime date);

    Set<InterviewDTO> getInterviews();

    void updateInterviewScore(ChangeInterviewScoreDto changeInterviewScore);
}
