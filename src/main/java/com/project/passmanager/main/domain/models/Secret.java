package com.project.passmanager.main.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель секркета для domain слоя
 * */
@Data
@AllArgsConstructor
public class Secret {
    private String id;
    private String secretSpaceId;
    private String name;
    private String login;
    private String password;
    private String note;
    private String url;
}
