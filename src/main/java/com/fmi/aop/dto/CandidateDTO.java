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
public class CandidateDTO {

    private Integer id;

    @NotEmpty(message = "Candidate must have a first name specified!")
    private String firstName;

    @NotEmpty(message = "Candidate must have a last name specified!")
    private String lastName;

    @NotEmpty(message = "Candidate must have an email specified!")
    private String email;

    @NotEmpty(message = "Candidate must have a phone specified!")
    private String phone;

    private Set<InterviewDTO> interviews;
}
