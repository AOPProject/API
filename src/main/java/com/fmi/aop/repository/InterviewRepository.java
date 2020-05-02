package com.fmi.aop.repository;

import com.fmi.aop.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {

    @Query("FROM Interview i where i.candidate.email = :email")
    Set<Interview> findInterviewByCandidateEmail(String email);

//    @Query("From Interview i where i.date = :date")
    Set<Interview> findAllByDateIsBetween(LocalDateTime startDate, LocalDateTime enDate);
}
