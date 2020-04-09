package com.fmi.aop.mapper;


import com.fmi.aop.dto.InterviewerDTO;
import com.fmi.aop.entity.Interviewer;
import com.fmi.aop.utils.Constants;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = Constants.SPRING)
public interface InterviewerMapper {

    Interviewer convertInterviewerDTOToInterviewer(InterviewerDTO interviewerDTO);

    InterviewerDTO convertInterviewerToInterviewerDTO(Interviewer interviewer);

    Set<Interviewer> convertInterviewerDTOToInterviewerList(Set<InterviewerDTO> interviewerDTOSet);

    Set<InterviewerDTO> convertInterviewerToInterviewerDTOList(Set<Interviewer> interviewerSet);
}
