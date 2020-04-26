package com.fmi.aop.repository;

import com.fmi.aop.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {

    @Query("FROM Interview i where i.candidate.email = :email")
    Set<Interview> findInterviewByCandidateEmail(String email);
}
