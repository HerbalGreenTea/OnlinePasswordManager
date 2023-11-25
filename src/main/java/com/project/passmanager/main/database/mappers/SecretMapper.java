package com.project.passmanager.main.database.mappers;

import com.project.passmanager.main.database.models.SecretEntity;
import com.project.passmanager.main.domain.models.Secret;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * маппинг модели Secret слоев domain и database
 */
@Component
public class SecretMapper {
    public SecretEntity transform(Secret secret) {
        return new SecretEntity(
                secret.getId(),
                secret.getSecretSpaceId(),
                secret.getName(),
                secret.getLogin(),
                secret.getPassword(),
                secret.getNote(),
                secret.getUrl()
        );
    }

    public List<SecretEntity> transformToSecretsEntity(List<Secret> secrets) {
        return secrets
                .stream()
                .map(new SecretMapper()::transform)
                .toList();
    }

    public Secret transform(SecretEntity secretEntity) {
        return new Secret(
                secretEntity.getId(),
                secretEntity.getFK_secretSpace(),
                secretEntity.getName(),
                secretEntity.getLogin(),
                secretEntity.getPassword(),
                secretEntity.getNote(),
                secretEntity.getUrl()
        );
    }

    public List<Secret> transformToSecrets(List<SecretEntity> secrets) {
        return secrets
                .stream()
                .map(new SecretMapper()::transform)
                .toList();
    }
}
