package me.Rahul.quorabackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class Question extends AuditBaseModel{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToMany
    private List<Topic> topics;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
}
