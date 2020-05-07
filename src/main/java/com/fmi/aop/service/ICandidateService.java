package com.fmi.aop.service;

import com.fmi.aop.dto.CandidateDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface ICandidateService {
    Set<CandidateDTO> getAllCandidates();

    CandidateDTO getCandidateByEmail(String email, HttpServletRequest request );

    CandidateDTO getCandidateById(Integer id);
}
