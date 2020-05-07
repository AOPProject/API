package com.fmi.aop.controller;

import com.fmi.aop.dto.InterviewerDTO;
import com.fmi.aop.service.IInterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interviewer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewerController {

    private final IInterviewerService service;


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

}
