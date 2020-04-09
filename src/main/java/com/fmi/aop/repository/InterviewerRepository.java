package com.fmi.aop.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterviewerRepository {
    Optional<Object> findById(Integer interviewerId);
}
