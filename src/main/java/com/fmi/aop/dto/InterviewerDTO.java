package com.fmi.aop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class InterviewerDTO {

    private Integer id;

    @NotEmpty(message = "Interviewer must have a first name specified!")
    private String firstName;

    @NotEmpty(message = "Interviewer must have a last name specified!")
    private String lastName;

    @NotEmpty(message = "Interviewer must have an email specified!")
    private String email;

    private boolean enabled;
    private String department;
    @Size(max = 60, message = "Password can't be more than 60 characters long!")
    private String password;

    private Set<InterviewDTO> interviews;
}
