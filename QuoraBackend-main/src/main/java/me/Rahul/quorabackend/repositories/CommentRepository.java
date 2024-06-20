package me.Rahul.quorabackend.repositories;

import me.Rahul.quorabackend.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
