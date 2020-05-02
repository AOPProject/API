package com.fmi.aop.service.impl;


import com.fmi.aop.dto.ChangeInterviewScoreDto;
import com.fmi.aop.dto.InterviewDTO;
import com.fmi.aop.entity.Interview;
import com.fmi.aop.mapper.InterviewMapper;
import com.fmi.aop.repository.InterviewRepository;
import com.fmi.aop.service.IInterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fmi.aop.utils.Constants.*;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewServiceImpl  implements IInterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewMapper interviewMapper;

    @Override
    public Set<InterviewDTO> getInterviewByCandidateEmail(String email) {
        return interviewRepository.findInterviewByCandidateEmail(email).stream()
                .map(this::toInterviewDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<InterviewDTO> getInterviewByDate(LocalDateTime date) {
        return interviewRepository.findAllByDateIsBetween(date, date.plusDays(1)).stream()
                .map(this::toInterviewDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<InterviewDTO> getInterviews() {
        return interviewRepository.findAll().stream()
                .map(this::toInterviewDTO).collect(Collectors.toSet());
    }

    @Override
    public void updateInterviewScore(ChangeInterviewScoreDto changeInterviewScore) {
        Interview interviewToUpdate = interviewRepository.findById(changeInterviewScore.getId()).orElseThrow(() -> new InvalidParameterException(
                String.format(INVALID_PARAMETER_EXCEPTION, INTERVIEW_ID, changeInterviewScore.getId())));

        interviewToUpdate.setScore(changeInterviewScore.getScore());
        interviewRepository.save(interviewToUpdate);
    }

    private InterviewDTO toInterviewDTO(Interview interview){
        return interviewMapper.convertInterviewToInterviewDTO(interview);
    }
}
