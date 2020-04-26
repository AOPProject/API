package com.fmi.aop.mapper;

import com.fmi.aop.dto.CandidateDTO;
import com.fmi.aop.entity.Candidate;
import com.fmi.aop.utils.Constants;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = Constants.SPRING)
public interface CandidateMapper {

    Candidate convertCandidateDTOToCandidate(CandidateDTO candidateDTO);

    CandidateDTO convertCandidateToCandidateDTO(Candidate candidate);

    Set<Candidate> convertCandidateDTOToCandidateList(Set<CandidateDTO> candidateDTOSet);

    Set<CandidateDTO> convertCandidateToCandidateDTOList(Set<Candidate> candidateSet);


}
