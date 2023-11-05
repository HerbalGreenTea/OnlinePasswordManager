package com.project.passmanager.main.network.services;

import com.project.passmanager.main.domain.models.SecretSpace;
import com.project.passmanager.main.domain.repositories.ISpaceSecretsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceSecretsService {
    private final ISpaceSecretsRepository spaceSecretsRepository;

    public List<SecretSpace> getSecretSpaces() {
        var spaces = new ArrayList<>(spaceSecretsRepository.getSecretSpaces());
        spaces.sort(Comparator.comparing(SecretSpace::getName));
        return spaces;
    }
}
