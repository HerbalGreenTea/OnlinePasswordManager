package com.project.passmanager.main.domain.repositories;

import com.project.passmanager.main.domain.models.SecretSpace;

import java.util.List;

/**
 * Репозторий для работы с моделью SecretSpace
 * */
public interface ISpaceSecretsRepository {
    List<SecretSpace> getSecretSpaces(String userId);

    SecretSpace getEmptySecretSpace(String userId);

    void saveSecretSpace(SecretSpace secretSpace);
    void deleteSecretSpace(String secretSpaceId);
}
