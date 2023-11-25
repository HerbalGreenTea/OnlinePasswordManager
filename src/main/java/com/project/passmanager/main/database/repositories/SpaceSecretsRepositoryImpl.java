package com.project.passmanager.main.database.repositories;

import com.project.passmanager.main.database.core.SecretSpaceDAO;
import com.project.passmanager.main.database.mappers.SecretSpaceMapper;
import com.project.passmanager.main.domain.models.SecretSpace;
import com.project.passmanager.main.domain.repositories.ISpaceSecretsRepository;
import com.project.passmanager.main.network.controllers.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class SpaceSecretsRepositoryImpl implements ISpaceSecretsRepository {
    SecretSpaceMapper secretSpaceMapper;
    SecretSpaceDAO secretsSpaceDAO;

    @Autowired
    public SpaceSecretsRepositoryImpl(SecretSpaceMapper secretSpaceMapper, SecretSpaceDAO secretSpaceDAO) {
        this.secretSpaceMapper = secretSpaceMapper;
        this.secretsSpaceDAO = secretSpaceDAO;
    }

    @Override
    public List<SecretSpace> getSecretSpaces(String userId) {
        return secretSpaceMapper.transformToSecretSpaces(
                secretsSpaceDAO.getSecretSpacesByUserId(userId)
        );
    }

    @Override
    public SecretSpace getEmptySecretSpace(String userId) {
        return new SecretSpace(
                UUID.randomUUID().toString(),
                userId,
                ""
        );
    }

    @Override
    public void saveSecretSpace(SecretSpace secretSpace) {
        //TODO: remove defaultUserId
        secretSpace.setF_key(UserController.defaultUserId);
        secretsSpaceDAO.putSecretSpaceByUser(
                secretSpaceMapper.transform(secretSpace)
        );
    }

    @Override
    public void deleteSecretSpace(String secretSpaceId) {
        secretsSpaceDAO.deleteSecretSpaceById(secretSpaceId);
    }
}
