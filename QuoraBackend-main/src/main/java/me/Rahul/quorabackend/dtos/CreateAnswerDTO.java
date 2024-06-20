package me.Rahul.quorabackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnswerDTO {
    private String text;
    private Long userId;
}
