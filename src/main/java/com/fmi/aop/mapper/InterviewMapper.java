package com.fmi.aop.mapper;

import com.fmi.aop.dto.InterviewDTO;
import com.fmi.aop.entity.Interview;
import com.fmi.aop.utils.Constants;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = Constants.SPRING)
public interface InterviewMapper {

    Interview convertInterviewDTOToInterview(InterviewDTO interviewDTO);

    InterviewDTO convertInterviewToInterviewDTO(Interview interview);

    Set<Interview> convertInterviewDTOToInterviewList(Set<InterviewDTO> interviewDTOSet);

    Set<InterviewDTO> convertInterviewToInterviewDTOList(Set<Interview> interviewSet);
}
