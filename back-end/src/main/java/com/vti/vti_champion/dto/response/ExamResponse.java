package com.vti.vti_champion.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExamResponse {
    private Integer examId;
    private String title;
    private String code;
    private Integer duration;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    private String creatorName;
    private String className;

    private List<QuestionResponse> questions;

}
