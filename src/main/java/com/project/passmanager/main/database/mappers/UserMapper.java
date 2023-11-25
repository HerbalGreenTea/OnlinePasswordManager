package com.project.passmanager.main.database.mappers;

import com.project.passmanager.main.database.models.UserEntity;
import com.project.passmanager.main.domain.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserEntity transform(User user) {
        return new UserEntity(
                user.getId(),
                user.getLogin(),
                user.getHashPassword()
        );
    }

    public List<UserEntity> transformToSecretsEntity(List<User> secrets) {
        return secrets
                .stream()
                .map(this::transform)
                .toList();
    }

    public User transform(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getLogin(),
                userEntity.getHashPassword()
        );
    }

    public List<User> transformToUsers(List<UserEntity> userEntities) {
        return userEntities
                .stream()
                .map(this::transform)
                .toList();
    }
}
