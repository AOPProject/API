package com.fmi.aop.event.listener;


import com.fmi.aop.dto.CandidateDTO;
import com.fmi.aop.dto.InterviewDTO;
import com.fmi.aop.dto.InterviewerDTO;
import com.fmi.aop.event.OnCandidateArrivalEvent;
import com.fmi.aop.service.IInterviewService;
import com.fmi.aop.service.IInterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import static com.fmi.aop.utils.Constants.CANDIDATE_ID;
import static com.fmi.aop.utils.Constants.INVALID_PARAMETER_EXCEPTION;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateArrivalListener implements ApplicationListener<OnCandidateArrivalEvent> {

    private final IInterviewerService interviewerService;
    private final IInterviewService interviewService;
    private final JavaMailSender mailSender;
    private final Environment env;

    @Override
    public void onApplicationEvent(final OnCandidateArrivalEvent event) {
        this.confirmArrival(event);
    }

    private void confirmArrival(final OnCandidateArrivalEvent event) {
        final CandidateDTO candidateDTO = event.getCandidateDTO();
        final Set<InterviewDTO> interviewSet = interviewService.getInterviewByCandidateEmail(candidateDTO.getEmail());
        final InterviewDTO interviewDTO = getTodayInterview(interviewSet, candidateDTO);
        final InterviewerDTO interviewerDTO = interviewerService.getInterviewerById(interviewDTO.getInterviewerId());
        final SimpleMailMessage email = constructEmailMessage(event, candidateDTO, interviewerDTO,interviewDTO);
        mailSender.send(email);
    }

    private InterviewDTO getTodayInterview(Set<InterviewDTO> interviewSet, CandidateDTO candidateDTO) {
        return interviewSet.stream().
                filter(interviewDTO -> interviewDTO.getDate().getDayOfYear() == LocalDate.now().getDayOfYear())
                .findFirst().orElseThrow(() -> new InvalidParameterException(
                String.format(INVALID_PARAMETER_EXCEPTION, CANDIDATE_ID, candidateDTO.getId())));
    }

    private final SimpleMailMessage constructEmailMessage(final OnCandidateArrivalEvent event, final CandidateDTO candidate, final InterviewerDTO interviewer, final InterviewDTO interview) {
        final String recipientAddress = interviewer.getEmail();
        final String subject = "Candidate Arrival";
        final String message = "Your candidate "+ candidate.getLastName() + " " + candidate.getFirstName() + " has arrived!";
        final String details = "He is waiting in room " + interview.getReservedRoom() + " for his " + interview.getType() + " interview!";
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + details);
        email.setFrom(Objects.requireNonNull(env.getProperty("support.email")));
        return email;
    }
}
