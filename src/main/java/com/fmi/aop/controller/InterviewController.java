package com.fmi.aop.controller;

import com.fmi.aop.dto.ChangeInterviewScoreDto;
import com.fmi.aop.dto.InterviewDTO;
import com.fmi.aop.service.IInterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;


@RestController
@RequestMapping("/interview")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewController {

    private final IInterviewService interviewService;

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public Set<InterviewDTO> getInterviewByCandidateEmail(@RequestParam String email){
        return interviewService.getInterviewByCandidateEmail(email);
    }

    @GetMapping("/date")
    @ResponseStatus(HttpStatus.OK)
    public Set<InterviewDTO> getInterviewsByDate(@RequestParam("date") String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDate.parse(date, formatter).atStartOfDay();

        return interviewService.getInterviewByDate(dateTime);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<InterviewDTO> getInterviews(){
        return interviewService.getInterviews();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void changeInterviewScore(@RequestBody ChangeInterviewScoreDto changeInterviewScore) {
        interviewService.updateInterviewScore(changeInterviewScore);
    }

}
