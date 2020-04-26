package com.fmi.aop.repository;

import com.fmi.aop.entity.Interviewer;
import com.fmi.aop.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
    VerificationToken findByInterviewer(Interviewer interviewer);
    Stream<VerificationToken> findAllByExpiryDateLessThan(LocalDateTime now);
    void deleteByExpiryDateLessThan(LocalDateTime now);

    @Modifying
    @Query("delete from VerificationToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(LocalDateTime now);
}