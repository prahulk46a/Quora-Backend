package me.Rahul.quorabackend.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private String content;
    private Long userId;
    private Date createdAt;
    private Date updatedAt;
}
