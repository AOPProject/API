package com.fmi.aop.controller;

import com.fmi.aop.dto.CandidateDTO;
import com.fmi.aop.service.ICandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public CandidateDTO getCandidateByEmail(@RequestParam String email, HttpServletRequest request){
        return candidateService.getCandidateByEmail(email, request);
    }

}
