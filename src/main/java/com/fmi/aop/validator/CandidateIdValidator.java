package com.fmi.aop.validator;

import com.fmi.aop.repository.CandidateRepository;
import com.fmi.aop.validator.constraints.CandidateExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class CandidateIdValidator  implements ConstraintValidator<CandidateExist, Integer> {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public boolean isValid(Integer candidateId, ConstraintValidatorContext cxt){
        if(Objects.isNull(candidateId)) {
            return true;
        }
        return candidateRepository.findById(candidateId).isPresent();
    }
}
