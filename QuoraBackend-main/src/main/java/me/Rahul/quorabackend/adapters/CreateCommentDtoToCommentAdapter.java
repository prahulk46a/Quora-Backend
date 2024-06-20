package me.Rahul.quorabackend.adapters;

import me.Rahul.quorabackend.entities.Comment;
import me.Rahul.quorabackend.dtos.CreateCommentDTO;

public interface CreateCommentDtoToCommentAdapter {
    public Comment convertDTO(CreateCommentDTO createCommentDTO) throws Exception;
}
