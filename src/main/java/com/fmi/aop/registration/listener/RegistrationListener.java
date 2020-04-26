package com.fmi.aop.registration.listener;


import com.fmi.aop.entity.Interviewer;
import com.fmi.aop.registration.OnRegistrationCompleteEvent;
import com.fmi.aop.service.IInterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final IInterviewerService service;
    private final JavaMailSender mailSender;
    private final Environment env;

    // API

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final Interviewer interviewer = event.getInterviewer();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForInterviewer(interviewer, token);

        final SimpleMailMessage email = constructEmailMessage(event, interviewer, token);
        mailSender.send(email);
    }

    //

    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final Interviewer interviewer, final String token) {
        final String recipientAddress = interviewer.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/registerConfirmation?token=" + token;
        final String message = "You registered successfully. We will send you a confirmation message to your email account.";
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(Objects.requireNonNull(env.getProperty("support.email")));
        return email;
    }


}