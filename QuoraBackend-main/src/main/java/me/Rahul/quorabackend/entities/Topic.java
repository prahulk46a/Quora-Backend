package me.Rahul.quorabackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Topic extends AuditBaseModel {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "topics")
    private List<Question> questions;
}
