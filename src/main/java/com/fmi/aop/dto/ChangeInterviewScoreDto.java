package com.fmi.aop.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ChangeInterviewScoreDto {
    Integer id;
    Integer score;
}
