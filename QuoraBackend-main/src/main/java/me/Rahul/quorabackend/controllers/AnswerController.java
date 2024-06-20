package me.Rahul.quorabackend.controllers;

import me.Rahul.quorabackend.services.AnswerService;
import me.Rahul.quorabackend.adapters.CreateCommentDtoToCommentAdapter;
import me.Rahul.quorabackend.dtos.AnswerDTO;
import me.Rahul.quorabackend.dtos.CommentDTO;
import me.Rahul.quorabackend.dtos.CreateCommentDTO;
import me.Rahul.quorabackend.dtos.QuestionDto;
import me.Rahul.quorabackend.entities.Answer;
import me.Rahul.quorabackend.entities.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/answers")
public class AnswerController {

    private AnswerService answerService;
    private CreateCommentDtoToCommentAdapter createCommentDtoToCommentAdapter;

    public AnswerController(AnswerService answerService, CreateCommentDtoToCommentAdapter createCommentDtoToCommentAdapter) {
        this.answerService = answerService;
        this.createCommentDtoToCommentAdapter = createCommentDtoToCommentAdapter;
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<?> editAnswer(@PathVariable Long answerId, @RequestBody String text) throws Exception{
        try {
            Answer answer = this.answerService.editAnswer(answerId, text);
            AnswerDTO response = AnswerDTO.builder()
                    .id(answer.getId())
                    .answeredBy(answer.getUser().getUsername())
                    .question(QuestionDto.builder().title(answer.getQuestion().getTitle()).build())
                    .content(answer.getText())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{answerId}/comments")
    public ResponseEntity<?>commentOnAnswer(@PathVariable Long answerId, @RequestBody CreateCommentDTO createCommentDTO) throws Exception{
        try {
            Comment comment = this.createCommentDtoToCommentAdapter.convertDTO(createCommentDTO);
            comment = this.answerService.commentOnAnswer(answerId, comment);
            CommentDTO response = CommentDTO.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .userId(comment.getUser().getId())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
