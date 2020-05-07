package com.fmi.aop.controller;

import com.fmi.aop.dto.CandidateDTO;
import com.fmi.aop.dto.InterviewDTO;
import com.fmi.aop.dto.InterviewerDTO;
import com.fmi.aop.entity.Interviewer;
import com.fmi.aop.mapper.InterviewerMapper;
import com.fmi.aop.service.IInterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interviewer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class InterviewerController {

    private final IInterviewerService service;

    private final InterviewerMapper mapper;

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public InterviewerDTO getInterviewerByEmail(@RequestParam("email") String email){
        return service.getInterviewerByEmail(email);
    }

    @GetMapping("getInterviewer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InterviewerDTO getInterviewerById(@PathVariable Integer id){
        return service.getInterviewerById(id);
    }

    private Interviewer toInterviewer(InterviewerDTO interviewerDTO){
        return mapper.convertInterviewerDTOToInterviewer(interviewerDTO);
    }
}
