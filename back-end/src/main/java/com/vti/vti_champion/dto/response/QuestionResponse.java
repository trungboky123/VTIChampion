package com.vti.vti_champion.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private Integer questionId;
    private String content;
    private String difficultyLevel;
    private List<AnswerResponse> answers;
}
