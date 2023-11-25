package com.project.passmanager.main.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "hashpassword")
    private String hashPassword;

    public UserEntity(String login, String hashPassword) {
        this.login = login;
        this.hashPassword = hashPassword;
    }
}
