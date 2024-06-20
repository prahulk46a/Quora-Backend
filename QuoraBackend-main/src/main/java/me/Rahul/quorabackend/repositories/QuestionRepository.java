package me.Rahul.quorabackend.repositories;

import me.Rahul.quorabackend.entities.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByTitleContainsOrDescriptionContains(String title, String description);
}
