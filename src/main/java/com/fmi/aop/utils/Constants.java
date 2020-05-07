package com.fmi.aop.utils;

public class Constants {

    private Constants() {}

    //Mappers
    public static final String SPRING = "spring";

    //Exception messages
    public static final String INVALID_PARAMETER_EXCEPTION = "Parameter %s with value %s is invalid";
    public static final String ACCOUNT_ALREADY_ENABLED_EXCEPTION = "The account corresponding to this email has already been activated!";

    public static final String CANDIDATE_ID = "Candidate id";
    public static final String CANDIDATE_EMAIL = "Candidate email";
    public static final String INTERVIEWER_ID = "Interviewer id";
    public static final String INTERVIEWER_EMAIL = "Interviewer email";
    public static final String INTERVIEW_ID = "Interview email";

    //Registration token status
    public static final String TOKEN_INVALID = "Specified token is invalid!";
    public static final String TOKEN_EXPIRED = "Specified token is expired!";
    public static final String TOKEN_VALID = "Specified token related account has been validated!";



}
