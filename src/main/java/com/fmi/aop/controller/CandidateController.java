package com.fmi.aop.controller;

import com.fmi.aop.dto.CandidateDTO;
import com.fmi.aop.service.ICandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateController {

    private final ICandidateService candidateService;

    @GetMapping
    public Set<CandidateDTO> getAllCandidate(){
        return candidateService.getAllCandidates();
    }
}
