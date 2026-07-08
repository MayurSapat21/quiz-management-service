package com.ms.question_service.mapper;

import com.ms.question_service.DTO.QuestionDTO;
import com.ms.question_service.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionDTO toDTO(Question question);

    Question toEntity(QuestionDTO questionDTO);
}