package com.fmi.aop.event;

import com.fmi.aop.dto.CandidateDTO;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@SuppressWarnings("serial")
public class OnCandidateArrivalEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final CandidateDTO candidateDTO;

    public OnCandidateArrivalEvent(final CandidateDTO candidateDTO, final Locale locale, final String appUrl) {
        super(candidateDTO);
        this.candidateDTO = candidateDTO;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public CandidateDTO getCandidateDTO() {
        return candidateDTO;
    }
}
