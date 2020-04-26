package com.fmi.aop.controller;


import com.fmi.aop.dto.InterviewerDTO;
import com.fmi.aop.dto.RegistrationDTO;
import com.fmi.aop.entity.Interviewer;
import com.fmi.aop.entity.JwtRequest;
import com.fmi.aop.entity.JwtResponse;
import com.fmi.aop.exception.AccountAlreadyEnabledException;
import com.fmi.aop.mapper.InterviewerMapper;
import com.fmi.aop.registration.OnRegistrationCompleteEvent;
import com.fmi.aop.service.IInterviewerService;
import com.fmi.aop.service.impl.JwtUserDetailsService;
import com.fmi.aop.utils.JwtTokenUtil;
import com.fmi.aop.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import static com.fmi.aop.utils.Constants.ACCOUNT_ALREADY_ENABLED_EXCEPTION;
import static com.fmi.aop.utils.Constants.TOKEN_EXPIRED;
import static com.fmi.aop.utils.Constants.TOKEN_INVALID;
import static com.fmi.aop.utils.Constants.TOKEN_VALID;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private IInterviewerService service;
    @Autowired
    private InterviewerMapper mapper;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;


    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody final RegistrationDTO registrationDTO, HttpServletRequest request){
        InterviewerDTO registeredInterviewer =  service.getInterviewerByEmail(registrationDTO.getEmail());
        if(registeredInterviewer.isEnabled())
            throw new AccountAlreadyEnabledException(ACCOUNT_ALREADY_ENABLED_EXCEPTION);

        registeredInterviewer = service.registerInterviewer(registrationDTO);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(toInterviewer(registeredInterviewer), request.getLocale(), getAppUrl(request)));
        return new ResponseEntity<>(registeredInterviewer.getEmail(), HttpStatus.OK);

    }

    @GetMapping("/registerConfirmation")
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

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    private Interviewer toInterviewer(InterviewerDTO interviewerDTO){
        return mapper.convertInterviewerDTOToInterviewer(interviewerDTO);
    }
}
