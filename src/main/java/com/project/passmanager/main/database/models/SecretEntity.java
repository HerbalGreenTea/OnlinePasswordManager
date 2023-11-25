package com.project.passmanager.main.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Модель секркета для database слоя
 */
@Getter
@Setter
@Entity
@Table(name = "secrets")
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class SecretEntity {
    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Column(name = "secret_space_id")
    private String FK_secretSpace;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "note")
    private String note;

    @Column(name = "url")
    private String url;

    public SecretEntity(String FK_secretSpace, String name, String login, String password, String note, String url) {
        this.FK_secretSpace = FK_secretSpace;
        this.name = name;
        this.login = login;
        this.password = password;
        this.note = note;
        this.url = url;
    }
}
