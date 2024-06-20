package me.Rahul.quorabackend.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment extends AuditBaseModel {

    @Column(nullable = false)
    private String content;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Answer answer;
}
