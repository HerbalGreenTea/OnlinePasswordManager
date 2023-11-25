package com.project.passmanager.main.network.services;

import com.project.passmanager.main.domain.models.SecretSpace;
import com.project.passmanager.main.domain.repositories.ISpaceSecretsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * Сущность для работы с иточниками данных для страницы пространств с секретами
 * */
@Service
@RequiredArgsConstructor
public class SpaceSecretsService {
    private final ISpaceSecretsRepository spaceSecretsRepository;

    public List<SecretSpace> getSecretSpaces() {
        var spaces = new ArrayList<>(spaceSecretsRepository.getSecretSpaces("509ec6ee-7e2a-4610-8214-4bc3e2f606b9"));
        spaces.sort(Comparator.comparing(SecretSpace::getName));
        return spaces;
    }

    public void saveSecretSpace(SecretSpace secretSpace) {
        secretSpace.setId(UUID.randomUUID().toString());
        spaceSecretsRepository.saveSecretSpace(secretSpace);
    }

    public void deleteSecretSpace(String secretSpaceId) {
        spaceSecretsRepository.deleteSecretSpace(secretSpaceId);
    }
}
