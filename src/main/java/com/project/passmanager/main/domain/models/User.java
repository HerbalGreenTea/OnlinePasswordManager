package com.project.passmanager.main.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель пользователя для domain слоя
 * */
@Data
@AllArgsConstructor
public class User {
    private String id;
    private String login;
    private String hashPassword;
}
