package com.fmi.aop.repository;

import com.fmi.aop.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    Optional<Candidate> findById(Integer candidateId);
    List<Candidate> findAllByOrderById();
    Optional<Candidate> findCandidateByEmail(String email);
}
