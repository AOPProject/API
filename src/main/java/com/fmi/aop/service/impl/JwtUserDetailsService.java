package com.fmi.aop.service.impl;

import com.fmi.aop.entity.Interviewer;
import com.fmi.aop.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

import static com.fmi.aop.utils.Constants.CANDIDATE_EMAIL;
import static com.fmi.aop.utils.Constants.INVALID_PARAMETER_EXCEPTION;
import static java.util.Collections.emptyList;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private InterviewerRepository interviewerRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        Interviewer interviewer = interviewerRepository.findInterviewerByEmail(email)
                .orElseThrow(() -> new InvalidParameterException(
                String.format(INVALID_PARAMETER_EXCEPTION, CANDIDATE_EMAIL, email)));
        return new User(interviewer.getEmail(), interviewer.getPassword(), emptyList());
    }
}
