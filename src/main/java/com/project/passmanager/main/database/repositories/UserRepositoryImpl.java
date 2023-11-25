package com.project.passmanager.main.database.repositories;

import com.project.passmanager.main.database.core.UsersDAO;
import com.project.passmanager.main.database.mappers.UserMapper;
import com.project.passmanager.main.domain.models.User;
import com.project.passmanager.main.domain.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {
    UserMapper userMapper;
    UsersDAO usersDAO;

    @Autowired
    public UserRepositoryImpl(UserMapper userMapper, UsersDAO usersDAO) {
        this.userMapper = userMapper;
        this.usersDAO = usersDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.transformToUsers(
                usersDAO.getAllUsers()
        );
    }

    @Override
    public User getUser(String userId) {
        return userMapper.transform(
                usersDAO.getUserById(userId)
        );
    }

    @Override
    public User getEmptyUser() {
        return new User(
                UUID.randomUUID().toString(),
                "",
                ""
        );
    }

    @Override
    public User getUserByLogin(String login) {
        return userMapper.transform(
                usersDAO.getUserByLogin(login)
        );
    }

    @Override
    public void saveUser(User user) {
        usersDAO.putUser(
                userMapper.transform(user)
        );
    }

    @Override
    public void deleteUser(String userId) {
        usersDAO.deleteUserById(userId);
    }
}
