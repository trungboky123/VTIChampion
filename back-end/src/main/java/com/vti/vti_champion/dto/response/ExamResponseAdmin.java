package com.vti.vti_champion.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ExamResponseAdmin {
    private Integer examId;
    private String title;
    private String code;
    private Integer duration;
    private Boolean isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    private String creatorName;
    private String className;
    private Integer classId;
    private String type;
    private Integer maxAttempts;
    private List<QuestionResponse> questions;
}
