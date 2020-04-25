package com.fmi.aop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "interviewer_id")
    private Interviewer interviewer;

    private LocalDateTime expiryDate;

    public VerificationToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpireDate();
    }

    public VerificationToken(final String token, final Interviewer interviewer) {
        this.token = token;
        this.interviewer = interviewer;
        this.expiryDate = calculateExpireDate();
    }

    private LocalDateTime calculateExpireDate() {
        LocalDateTime expireDate = LocalDateTime.now();
        return expireDate.plusMinutes(VerificationToken.EXPIRATION);
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpireDate();
    }
}
