package com.fmi.aop.repository;

import com.fmi.aop.entity.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Integer> {

    Optional<Interviewer> findById(Integer interviewerId);
    Optional<Interviewer> findInterviewerByEmail(String email);
}
