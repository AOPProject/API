package com.fmi.aop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CandidateDTO {

    private Integer id;

    @NotEmpty(message = "Candidate must have a first name specified!")
    private String firstName;

    @NotEmpty(message = "Candidate must have a last name specified!")
    private String lastName;

    @Email(message = "Please specify a valid email address!")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Candidate must have a phone number of 10 digits!")
    private String phone;

    private Set<InterviewDTO> interviews;
}
