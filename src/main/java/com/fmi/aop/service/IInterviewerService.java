package com.fmi.aop.service;

import com.fmi.aop.dto.InterviewDTO;
import com.fmi.aop.dto.InterviewerDTO;
import com.fmi.aop.dto.RegistrationDTO;
import com.fmi.aop.entity.Interviewer;
import com.fmi.aop.entity.VerificationToken;
import com.fmi.aop.utils.Token;

public interface IInterviewerService {

    InterviewerDTO getInterviewerByEmail(String email);
    void createVerificationTokenForInterviewer(Interviewer interviewer, String token);
    VerificationToken getVerificationToken(String verificationToken);
    VerificationToken generateNewVerificationToken(String token);
    Token validateVerificationToken(String token);

    InterviewerDTO registerInterviewer(RegistrationDTO registrationDTO);

    InterviewerDTO getInterviewerById(Integer id);
}
