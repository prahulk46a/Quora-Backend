package me.Rahul.quorabackend.adapters;

import me.Rahul.quorabackend.entities.Answer;
import me.Rahul.quorabackend.dtos.CreateAnswerDTO;

public interface CreateAnswerDtoToAnswerAdapter {
    public Answer convertDto(CreateAnswerDTO createAnswerDTO) throws Exception;
}
