package com.project.passmanager.main.database.repositories;

import com.project.passmanager.main.database.core.SecretsDAO;
import com.project.passmanager.main.database.mappers.SecretMapper;
import com.project.passmanager.main.domain.models.Secret;
import com.project.passmanager.main.domain.repositories.ISecretRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecretRepositoryImpl implements ISecretRepository {
    SecretMapper secretMapper;
    SecretsDAO secretsDAO;

    @Autowired
    public SecretRepositoryImpl(SecretMapper secretMapper, SecretsDAO secretsDAO) {
        this.secretMapper = secretMapper;
        this.secretsDAO = secretsDAO;
    }

    @Override
    public List<Secret> getSecrets(String secretSpaceId) {
        return secretMapper.transformToSecrets(secretsDAO.getSecretsBySecretSpaceId(secretSpaceId));
    }

    @Override
    public List<Secret> getSecretsBySecretSpaceId(String secretSpaceId) {
        return secretMapper.transformToSecrets(secretsDAO.getSecretsBySecretSpaceId(secretSpaceId));
    }

    @Override
    public Secret getSecretById(String secretId) {
        return secretMapper.transform(secretsDAO.getSecretById(secretId));
    }

    @Override
    public Secret getEmptySecret(String secretSpaceId) {
        return new Secret(
                UUID.randomUUID().toString(),
                secretSpaceId,
                "",
                "",
                "",
                "",
                ""
        );
    }

    @Override
    public void saveSecret(Secret secret) {
        secretsDAO.putSecret(secretMapper.transform(secret));
    }

    @Override
    public void deleteSecret(String secretId) {
        secretsDAO.deleteSecretById(secretId);
    }

    @Override
    public void deleteSecretsInSecretSpace(String secretSpaceId) {
        secretsDAO.deleteAllSecretsBySecretSpace(secretSpaceId);
    }
}
