package com.vti.vti_champion.service.interfaces;

import com.vti.vti_champion.constant.DifficultyLevel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class FilterQuestionRequest {
    private String content;
    private DifficultyLevel difficultyLevel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    private LocalDate toDate;
}

