package com.fmi.aop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class InterviewDTO {

    private Integer id;

    @NotEmpty(message = "Interview must have a reserved room specified!")
    private String reservedRoom;

    @NotEmpty(message = "Interview must have a interview type specified!")
    private String type;

    @NotEmpty(message = "Interview must have a date specified!")
    private LocalDateTime date;

    private Integer score;

    private CandidateDTO candidate;

    private InterviewerDTO interviewer;

}
