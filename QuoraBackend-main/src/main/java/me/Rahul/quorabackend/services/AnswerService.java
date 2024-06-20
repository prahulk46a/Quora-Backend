package me.Rahul.quorabackend.services;

import me.Rahul.quorabackend.entities.Answer;
import me.Rahul.quorabackend.entities.Comment;

public interface AnswerService {
    public Answer editAnswer(Long answerId, String text) throws Exception;
    public Comment commentOnAnswer(Long answerId, Comment comment) throws Exception;
}
