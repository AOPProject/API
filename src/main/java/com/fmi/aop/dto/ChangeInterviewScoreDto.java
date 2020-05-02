package com.fmi.aop.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ChangeInterviewScoreDto {
    int id;
    int score;
}
