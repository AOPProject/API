package com.fmi.aop.mapper;

import com.fmi.aop.dto.InterviewDTO;
import com.fmi.aop.entity.Interview;
import com.fmi.aop.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = Constants.SPRING)
public interface InterviewMapper {

    @Mapping(target = "candidate.id", source = "candidateId")
    @Mapping(target = "interviewer.id", source = "interviewerId")
    Interview convertInterviewDTOToInterview(InterviewDTO interviewDTO);

    @Mapping(target = "candidateId", source = "candidate.id")
    @Mapping(target = "interviewerId", source = "interviewer.id")
    InterviewDTO convertInterviewToInterviewDTO(Interview interview);

    Set<Interview> convertInterviewDTOToInterviewList(Set<InterviewDTO> interviewDTOSet);

    Set<InterviewDTO> convertInterviewToInterviewDTOList(Set<Interview> interviewSet);
}
