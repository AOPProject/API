package com.fmi.aop.controller;

import com.fmi.aop.dto.CandidateDTO;
import com.fmi.aop.dto.InterviewDTO;
import com.fmi.aop.service.IInterviewService;
import com.fmi.aop.utils.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

import java.time.LocalDateTime;
import java.util.Set;

import static com.fmi.aop.utils.Constants.*;

@RestController
@RequestMapping("/interview")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewController {

    private final IInterviewService interviewService;

    @GetMapping
    public Set<InterviewDTO> getInterviewByCandidateEmail(@RequestParam String email){
        return interviewService.getInterviewByCandidateEmail(email);
    }

//    @GetMapping("/interview")
//    public ResponseEntity<?> getInterviewsByDate(@RequestParam LocalDateTime date){
//
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }
}
