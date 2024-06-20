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
public class User extends AuditBaseModel{
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    private String bio;
    @ManyToMany
    private List<User> follower;
    @ManyToMany
    private List<User> following;
}
