package com.fmi.aop.validator;

import com.fmi.aop.repository.InterviewerRepository;
import com.fmi.aop.validator.constraints.InterviewerExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class InterviewerIdValidator implements ConstraintValidator<InterviewerExist, Integer> {

    @Autowired
    private InterviewerRepository interviewerRepository;

    @Override
    public boolean isValid(Integer interviewerId, ConstraintValidatorContext cxt){
        if(Objects.isNull(interviewerId)) {
            return true;
        }
        return interviewerRepository.findById(interviewerId).isPresent();
    }
}
