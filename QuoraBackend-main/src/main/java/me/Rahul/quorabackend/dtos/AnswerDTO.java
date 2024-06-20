package me.Rahul.quorabackend.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private Long id;
    private String content;
    private String answeredBy;
    private QuestionDto question;
}
