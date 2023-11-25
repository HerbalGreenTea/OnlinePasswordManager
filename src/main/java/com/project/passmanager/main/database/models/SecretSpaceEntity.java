package com.project.passmanager.main.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Модель пространства для секретов для database слоя
 */
@Getter
@Setter
@Entity
@Table(name="secret_spaces")
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class SecretSpaceEntity {
    @Id
    @Column(name="id")
    private String id = UUID.randomUUID().toString();

    @Column(name="user_id")
    private String FK_user;

    @Column(name="name", nullable=false)
    private String name;

    public SecretSpaceEntity(String FK_user, String name) {
        this.FK_user = FK_user;
        this.name = name;
    }
}
