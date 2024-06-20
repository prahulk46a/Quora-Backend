package me.Rahul.quorabackend.dtos;

import lombok.*;
import me.Rahul.quorabackend.entities.Question;

import java.util.Date;
import java.util.List;

/**
 * DTO for {@link Question}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    private String title;
    private String description;
    private Long author;
    private List<String> tags;
    private Date createdAt;
    private Date updatedAt;
}