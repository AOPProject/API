package com.fmi.aop.validator.constraints;


import com.fmi.aop.validator.CandidateIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CandidateIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Interviewer id must not be null!")
public @interface InterviewerExist {
    String message() default "Interviewer with id: ${validatedValue} does not exist!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
