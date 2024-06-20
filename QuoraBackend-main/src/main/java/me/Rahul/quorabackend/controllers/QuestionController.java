package me.Rahul.quorabackend.controllers;

import me.Rahul.quorabackend.services.QuestionService;
import me.Rahul.quorabackend.adapters.CreateAnswerDtoToAnswerAdapter;
import me.Rahul.quorabackend.adapters.CreateQuestionDtoToQuestionAdapter;
import me.Rahul.quorabackend.dtos.AnswerDTO;
import me.Rahul.quorabackend.dtos.CreateAnswerDTO;
import me.Rahul.quorabackend.dtos.CreateQuestionDTO;
import me.Rahul.quorabackend.dtos.QuestionDto;
import me.Rahul.quorabackend.entities.Answer;
import me.Rahul.quorabackend.entities.Question;
import me.Rahul.quorabackend.entities.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private QuestionService questionService;
    private CreateQuestionDtoToQuestionAdapter createQuestionDtoToQuestionAdapter;
    private CreateAnswerDtoToAnswerAdapter createAnswerDtoToAnswerAdapter;

    public QuestionController(QuestionService questionService,
                              CreateQuestionDtoToQuestionAdapter createQuestionDtoToQuestionAdapter,
                              CreateAnswerDtoToAnswerAdapter createAnswerDtoToAnswerAdapter
    )
    {
        this.questionService = questionService;
        this.createQuestionDtoToQuestionAdapter = createQuestionDtoToQuestionAdapter;
        this.createAnswerDtoToAnswerAdapter = createAnswerDtoToAnswerAdapter;
    }

    @PostMapping
    public ResponseEntity<?> postQuestion(@RequestBody CreateQuestionDTO questionDTO) {
        try {
            Question convertedQuestion = this.createQuestionDtoToQuestionAdapter.convertDTO(questionDTO);
            Question savedQuestion = this.questionService.postQuestion(convertedQuestion);
            QuestionDto response = createQuestionDtoResponse(savedQuestion);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchQuestion(@RequestParam String text, @RequestParam String tag){
        try {
            List<Question> matchedQuestions = this.questionService.searchQuestions(text, tag);
            List<QuestionDto> questionDtos = new ArrayList<>();
            for(Question matchedQuestion : matchedQuestions){
                QuestionDto questionDto = createQuestionDtoResponse(matchedQuestion);
                questionDtos.add(questionDto);
            }
            return new ResponseEntity<>(questionDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private QuestionDto createQuestionDtoResponse(Question matchedQuestion) {
        List<Topic> topics = matchedQuestion.getTopics();
        List<String> topicNames = new ArrayList<>();
        for (Topic topic : topics) {
            topicNames.add(topic.getName());
        }
        return QuestionDto.builder()
                .id(matchedQuestion.getId())
                .title(matchedQuestion.getTitle())
                .author(matchedQuestion.getUser().getId())
                .description(matchedQuestion.getDescription())
                .tags(topicNames)
                .createdAt(matchedQuestion.getCreatedAt())
                .updatedAt(matchedQuestion.getUpdatedAt())
                .build();
    }

    @PostMapping("/{questionId}/answers")
    public ResponseEntity<?> postAnswerToQuestion(@PathVariable Long questionId, @RequestBody CreateAnswerDTO createAnswerDTO){
        try {
            Answer incomingAnswer = this.createAnswerDtoToAnswerAdapter.convertDto(createAnswerDTO);
            Answer savedAnswer = this.questionService.postAnswer(questionId, incomingAnswer);
            List<Topic> topics = savedAnswer.getQuestion().getTopics();
            List<String> tags = new ArrayList<>();
            for (Topic topic : topics) {
                tags.add(topic.getName());
            }
            QuestionDto questionDto = QuestionDto.builder()
                                                .id(savedAnswer.getQuestion().getId())
                                                .title(savedAnswer.getQuestion().getTitle())
                                                .description(savedAnswer.getQuestion().getDescription())
                                                .author(savedAnswer.getQuestion().getUser().getId())
                                                .tags(tags)
                                                .createdAt(savedAnswer.getQuestion().getCreatedAt())
                                                .updatedAt(savedAnswer.getQuestion().getUpdatedAt())
                                                .build();
            AnswerDTO response = AnswerDTO.builder()
                                            .id(savedAnswer.getId())
                                            .answeredBy(savedAnswer.getUser().getUsername())
                                            .content(savedAnswer.getText())
                                            .question(questionDto)
                                            .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
