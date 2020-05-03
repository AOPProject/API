package com.fmi.aop.service.impl;


import com.fmi.aop.dto.InterviewDTO;
import com.fmi.aop.dto.InterviewerDTO;
import com.fmi.aop.dto.RegistrationDTO;
import com.fmi.aop.entity.Interviewer;
import com.fmi.aop.entity.VerificationToken;
import com.fmi.aop.mapper.InterviewerMapper;
import com.fmi.aop.repository.InterviewerRepository;
import com.fmi.aop.repository.VerificationTokenRepository;
import com.fmi.aop.service.IInterviewerService;
import com.fmi.aop.utils.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.fmi.aop.utils.Constants.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewerServiceImpl implements IInterviewerService {

    private final InterviewerRepository interviewerRepository;
    private final InterviewerMapper interviewerMapper;
    private final VerificationTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public InterviewerDTO getInterviewerByEmail(String email) {
       return  interviewerRepository.findInterviewerByEmail(email)
                .map(this::toInterviewerDTO)
                .orElseThrow(() -> new InvalidParameterException(
                        String.format(INVALID_PARAMETER_EXCEPTION, INTERVIEWER_EMAIL, email)));
    }

    @Override
    public VerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void createVerificationTokenForInterviewer(final Interviewer interviewer, final String token) {
        final VerificationToken myToken = new VerificationToken(token, interviewer);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID()
                .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }

    @Override
    public Token validateVerificationToken(String token) {
        final VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return Token.INVALID;
        }

        final Interviewer interviewer = verificationToken.getInterviewer();
        final LocalDateTime present =  LocalDateTime.now();
        if(verificationToken.getExpiryDate().isBefore(present)){
            tokenRepository.delete(verificationToken);
            return Token.EXPIRED;
        }

        interviewer.setEnabled(true);
        tokenRepository.delete(verificationToken);
        interviewerRepository.save(interviewer);
        return Token.VALID;
    }

    @Override
    public InterviewerDTO registerInterviewer(RegistrationDTO registrationDTO) {
        String hashedPassword = passwordEncoder.encode(registrationDTO.getPassword());
        Interviewer interviewer = interviewerRepository
                .findInterviewerByEmail(registrationDTO.getEmail())
                .orElseThrow(() -> new InvalidParameterException(
                        String.format(INVALID_PARAMETER_EXCEPTION, INTERVIEWER_EMAIL, registrationDTO.getEmail())));
        interviewer.setPassword(hashedPassword);
        return toInterviewerDTO(interviewerRepository.save(interviewer));
    }

    @Override
    public InterviewerDTO getInterviewerById(Integer id) {
        return interviewerRepository.findById(id)
                .map(this::toInterviewerDTO)
                .orElseThrow(() -> new InvalidParameterException(
                        String.format(INVALID_PARAMETER_EXCEPTION, INTERVIEWER_ID, id)));
    }

    private InterviewerDTO toInterviewerDTO(Interviewer interviewer){
        return interviewerMapper.convertInterviewerToInterviewerDTO(interviewer);
    }

    public void changeInterviewerPassword(final Interviewer interviewer, final String password) {
        interviewer.setPassword(passwordEncoder.encode(password));
        interviewerRepository.save(interviewer);
    }

}
