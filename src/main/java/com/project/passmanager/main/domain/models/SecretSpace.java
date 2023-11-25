package com.project.passmanager.main.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель пространства для секретов для domain слоя
 * */
@Data
@AllArgsConstructor
public class SecretSpace {
    private String id;
    private String f_key;
    private String name;
}
