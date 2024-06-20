package me.Rahul.quorabackend.adapters;

import me.Rahul.quorabackend.dtos.CreateQuestionDTO;
import me.Rahul.quorabackend.entities.Question;

public interface CreateQuestionDtoToQuestionAdapter {
    public Question convertDTO(CreateQuestionDTO createQuestionDTO) throws Exception;
}
