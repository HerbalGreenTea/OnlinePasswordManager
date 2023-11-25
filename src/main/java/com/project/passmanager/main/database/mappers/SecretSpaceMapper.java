package com.project.passmanager.main.database.mappers;


import com.project.passmanager.main.database.models.SecretSpaceEntity;
import com.project.passmanager.main.domain.models.SecretSpace;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * маппинг модели SecretSpace слоев domain и database
 */
@Component
public class SecretSpaceMapper {
    public SecretSpaceEntity transform(SecretSpace secretSpace) {
        return new SecretSpaceEntity(
                secretSpace.getId(),
                secretSpace.getF_key(),
                secretSpace.getName()
        );
    }

    public List<SecretSpaceEntity> transformToSecretSpacesEntity(List<SecretSpace> secretSpaces) {
        return secretSpaces
                .stream()
                .map(this::transform)
                .toList();
    }

    public SecretSpace transform(SecretSpaceEntity secretSpaceEntity) {
        return new SecretSpace(
                secretSpaceEntity.getId(),
                secretSpaceEntity.getFK_user(),
                secretSpaceEntity.getName()
        );
    }

    public List<SecretSpace> transformToSecretSpaces(List<SecretSpaceEntity> secretSpaceEntities) {
        return secretSpaceEntities
                .stream()
                .map(this::transform)
                .toList();
    }
}