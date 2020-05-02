package com.fmi.aop.controller;

import com.fmi.aop.dto.CandidateDTO;
import com.fmi.aop.service.ICandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateController {

    private final ICandidateService candidateService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<CandidateDTO> getAllCandidates(){
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateDTO getCandidateById(@PathVariable Integer id){
        return candidateService.getCandidateById(id);
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public CandidateDTO getCandidateByEmail(@RequestParam String email){
        return candidateService.getCandidateByEmail(email);
    }

}
