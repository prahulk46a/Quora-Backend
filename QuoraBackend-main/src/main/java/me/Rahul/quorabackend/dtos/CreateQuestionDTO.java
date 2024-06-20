package me.Rahul.quorabackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateQuestionDTO {
    private String title;
    private String description;
    private Long userId;
    private List<Long> topics;
}
