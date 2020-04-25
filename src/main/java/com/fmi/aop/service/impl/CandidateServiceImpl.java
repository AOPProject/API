package com.fmi.aop.service.impl;


import com.fmi.aop.dto.CandidateDTO;
import com.fmi.aop.entity.Candidate;
import com.fmi.aop.mapper.CandidateMapper;
import com.fmi.aop.repository.CandidateRepository;
import com.fmi.aop.service.ICandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fmi.aop.utils.Constants.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateServiceImpl implements ICandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    @Override
    public Set<CandidateDTO> getAllCandidates() {
        return candidateRepository.findAllByOrderById().stream()
                .map(this::toCandidateDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public CandidateDTO getCandidateByEmail(String email) {
        return candidateRepository.findCandidateByEmail(email)
                .map(this::toCandidateDTO)
                .orElseThrow(() -> new InvalidParameterException(
                        String.format(INVALID_PARAMETER_EXCEPTION, CANDIDATE_EMAIL, email)));
    }

    @Override
    public CandidateDTO getCandidateById(Integer id) {
        return candidateRepository.findById(id)
                .map(this::toCandidateDTO)
                .orElseThrow(() -> new InvalidParameterException(
                        String.format(INVALID_PARAMETER_EXCEPTION, CANDIDATE_ID, id)));
    }

    private CandidateDTO toCandidateDTO(Candidate candidate){
        return candidateMapper.convertCandidateToCandidateDTO(candidate);
    }
}
