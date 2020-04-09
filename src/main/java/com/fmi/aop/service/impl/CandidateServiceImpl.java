package com.fmi.aop.service.impl;


import com.fmi.aop.dto.CandidateDTO;
import com.fmi.aop.entity.Candidate;
import com.fmi.aop.mapper.CandidateMapper;
import com.fmi.aop.repository.CandidateRepository;
import com.fmi.aop.service.ICandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateServiceImpl implements ICandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    @Override
    public Set<CandidateDTO> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(this::toCandidateDTO)
                .collect(Collectors.toSet());
    }

    private CandidateDTO toCandidateDTO(Candidate candidate){
        return candidateMapper.convertCandidateToCandidateDTO(candidate);
    }
}
