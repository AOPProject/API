package com.fmi.aop.registration;


import com.fmi.aop.entity.Interviewer;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final Interviewer interviewer;

    public OnRegistrationCompleteEvent(final Interviewer interviewer, final Locale locale, final String appUrl) {
        super(interviewer);
        this.interviewer = interviewer;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    //

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

}