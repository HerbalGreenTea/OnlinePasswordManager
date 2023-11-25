package com.project.passmanager.main.domain.repositories;

import com.project.passmanager.main.domain.models.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAllUsers();

    User getUser(String userId);

    User getEmptyUser();

    User getUserByLogin(String login);

    void saveUser(User user);

    void deleteUser(String userId);
}
