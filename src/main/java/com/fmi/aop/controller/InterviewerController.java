package com.fmi.aop.controller;


import com.fmi.aop.dto.InterviewerDTO;
import com.fmi.aop.dto.RegistrationDTO;
import com.fmi.aop.entity.Interviewer;
import com.fmi.aop.exception.AccountAlreadyEnabledException;
import com.fmi.aop.mapper.InterviewerMapper;
import com.fmi.aop.registration.OnRegistrationCompleteEvent;
import com.fmi.aop.service.IInterviewerService;
import com.fmi.aop.utils.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import static com.fmi.aop.utils.Constants.ACCOUNT_ALREADY_ENABLED_EXCEPTION;
import static com.fmi.aop.utils.Constants.TOKEN_EXPIRED;
import static com.fmi.aop.utils.Constants.TOKEN_INVALID;
import static com.fmi.aop.utils.Constants.TOKEN_VALID;

@RestController
@RequestMapping("/interviewer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewerController {

    private final IInterviewerService service;

    private final InterviewerMapper mapper;

    private final MessageSource messages;

    private final JavaMailSender mailSender;

    private final ApplicationEventPublisher eventPublisher;

    private final Environment env;

    private AuthenticationManager authenticationManager;


    @PostMapping("/registration")
    public ResponseEntity<?> registerAccount(@RequestBody final RegistrationDTO registrationDTO, HttpServletRequest request){
        InterviewerDTO registeredInterviewer =  service.getInterviewerByEmail(registrationDTO.getEmail());
        if(registeredInterviewer.isEnabled())
            throw new AccountAlreadyEnabledException(ACCOUNT_ALREADY_ENABLED_EXCEPTION);

        registeredInterviewer = service.registerInterviewer(registrationDTO);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(toInterviewer(registeredInterviewer), request.getLocale(), getAppUrl(request)));
        return new ResponseEntity<>(registeredInterviewer.getEmail(), HttpStatus.OK);

    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<?> confirmToken(@PathParam("token") String token){
        Token status = service.validateVerificationToken(token);
        String message;
        switch(status){
            case VALID:{ message = TOKEN_VALID;
                        break;}
            case EXPIRED: {message = TOKEN_EXPIRED;
                        break;}
            default: message = TOKEN_INVALID;
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }




    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    private Interviewer toInterviewer(InterviewerDTO interviewerDTO){
        return mapper.convertInterviewerDTOToInterviewer(interviewerDTO);
    }
}
