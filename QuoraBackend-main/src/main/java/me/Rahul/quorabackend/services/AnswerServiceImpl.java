package me.Rahul.quorabackend.services;

import jakarta.persistence.EntityNotFoundException;
import me.Rahul.quorabackend.entities.Answer;
import me.Rahul.quorabackend.entities.Comment;
import me.Rahul.quorabackend.repositories.AnswerRepository;
import me.Rahul.quorabackend.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;
    private CommentRepository commentRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository, CommentRepository commentRepository) {
        this.answerRepository = answerRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Answer editAnswer(Long answerId, String text) throws Exception{
        try {
            Answer answer = this.answerRepository.findById(answerId).orElseThrow(EntityNotFoundException::new);
            answer.setText(text);
            return this.answerRepository.save(answer);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Unable to update the answer currently try later");
        }
    }
    @Override
    public Comment commentOnAnswer(Long answerId, Comment comment) throws Exception{
        try {
            Answer answer = this.answerRepository.findById(answerId).orElseThrow(EntityNotFoundException::new);
            comment.setAnswer(answer);
            comment = this.commentRepository.save(comment);
            return comment;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Unable to update the comment on answer with id "+ answerId + " currently try later");
        }
    }
}
