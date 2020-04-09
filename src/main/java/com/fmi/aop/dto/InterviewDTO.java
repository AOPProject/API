package com.fmi.aop.dto;

import com.fmi.aop.validator.constraints.CandidateExist;
import com.fmi.aop.validator.constraints.InterviewerExist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class InterviewDTO {

    private Integer id;

    @CandidateExist
    private Integer candidateId;

    @InterviewerExist
    private Integer interviewerId;

    @NotEmpty(message = "Interview must have a reserved room specified!")
    private String reservedRoom;

    @NotEmpty(message = "Interview must have a interview type specified!")
    private String type;

    @Future(message = "Please specify a date in future for the interview!")
    @DateTimeFormat(pattern = "YYYY-MM-DDTHH24:MM:SS")
    @NotNull(message = "Interview date must not be null")
    private LocalDateTime date;

    private Integer score;


}
