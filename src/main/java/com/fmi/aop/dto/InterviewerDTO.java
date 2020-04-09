package com.fmi.aop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterviewerDTO {

    private Integer id;

    @NotEmpty(message = "Interviewer must have a first name specified!")
    private String firstName;

    @NotEmpty(message = "Interviewer must have a last name specified!")
    private String lastName;

    @NotEmpty(message = "Interviewer must have an email specified!")
    private String email;

    private String department;
    private String activationCode;
    private String password;
    private Boolean isActive;

    private Set<InterviewDTO> interviews;
}
