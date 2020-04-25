package com.fmi.aop.controller;


import com.fmi.aop.dto.InterviewerDTO;
import com.fmi.aop.entity.Interviewer;
import com.fmi.aop.mapper.InterviewerMapper;
import com.fmi.aop.service.IInterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interviewer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewerController {

    private final IInterviewerService service;
    private final InterviewerMapper mapper;



    private Interviewer toInterviewer(InterviewerDTO interviewerDTO){
        return mapper.convertInterviewerDTOToInterviewer(interviewerDTO);
    }
}