package com.project.passmanager.main.network.services;

import com.project.passmanager.main.domain.models.User;
import com.project.passmanager.main.domain.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Класс реализует интерфейс UserDetailsService (этот интерфейс используется для получения данных пользователя)
 */
@RequiredArgsConstructor
@Component
public class UserRegistrationService {
    private final IUserRepository userRepository;


    /**
     * Загрузка пользователя по его имени
     *
     * @param login имя пользователя, идентифицирующее пользователя, данные которого требуются
     * @return возвращает найденного пользователя
     */
    public User getUserByUsername(String login) {
        return userRepository.getUserByLogin(login);
    }


    /**
     * Метод для добавления пользователя в базу данных
     *
     * @param user пользователь
     * @throws Exception ошибка, если пользователь с таким именем уже существует в базе данных
     */
    public void saveUser(User user) throws Exception {
        if (userRepository.getUserByLogin(user.getLogin()) != null) {
            throw new Exception("userRegistration exist");
        }
        userRepository.saveUser(user);
    }
}
