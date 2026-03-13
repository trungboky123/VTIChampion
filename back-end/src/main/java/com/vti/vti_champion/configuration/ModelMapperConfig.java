package com.vti.vti_champion.configuration;

import com.vti.vti_champion.dto.response.AnswerResponse;
import com.vti.vti_champion.dto.response.ExamResponse;
import com.vti.vti_champion.dto.response.QuestionResponse;
import com.vti.vti_champion.entity.Answer;
import com.vti.vti_champion.entity.Exam;
import com.vti.vti_champion.entity.Question;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Cấu hình riêng
        modelMapper.typeMap(Question.class, QuestionResponse.class).addMappings(mapper -> {
            // Chỉ rõ: lấy getId từ Entity gán vào setQuestionId của DTO
            mapper.map(Question::getId, QuestionResponse::setQuestionId);
        });

        modelMapper.typeMap(Answer.class, AnswerResponse.class).addMappings(mapper -> {

            mapper.map(Answer::getId, AnswerResponse::setAnswerId);
        });

        modelMapper.typeMap(Exam.class, ExamResponse.class).addMappings(mapper -> {

            mapper.map(Exam::getId, ExamResponse::setExamId);
        });
        
        return modelMapper;
    }
}
