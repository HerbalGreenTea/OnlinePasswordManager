package com.project.passmanager.main.registration.services;

import com.project.passmanager.main.registration.models.Role;
import com.project.passmanager.main.registration.models.UserRegistration;
import com.project.passmanager.main.registration.repositories.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс реализует интерфейс UserDetailsService (этот интерфейс используется для получения данных пользователя)
 */

@Component
public class UserRegistrationService implements UserDetailsService {
    /**
     * Поле с репозиторием
     */
    private final UserRegistrationRepository userRegistrationRepository;

    /**
     * Конструктор создание нового объекта с определенными значениями
     */
    @Autowired
    public UserRegistrationService(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
    }

    /**
     * Загрузка пользователя по его имени
     * @param username имя пользователя, идентифицирующее пользователя, данные которого требуются
     * @return возвращает найденного пользователя
     * @throws UsernameNotFoundException ошибка, если пользователь с таким именем не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRegistration myUserRegistration = userRegistrationRepository.findByUsername(username);
        return new User(myUserRegistration.getUsername(), myUserRegistration.getPassword(), mapRolesToAthorities(myUserRegistration.getRoles()));
    }

    /**
     * Метод, преобразующий наши роли к "спринговым" ролям
     */
    private List<? extends GrantedAuthority> mapRolesToAthorities (Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+ role.name())).collect(Collectors.toList());
    }

    /**
     * Метод для добавления пользователя в базу данных
     * @param userRegistration пользователь
     * @throws Exception ошибка, если пользователь с таким именем уже существует в базе данных
     */
    public void addUser(UserRegistration userRegistration) throws Exception
    {
        UserRegistration userRegistrationFromDb = userRegistrationRepository.findByUsername(userRegistration.getUsername());
        if (userRegistrationFromDb != null)
        {
            throw new Exception("userRegistration exist");
        }
        userRegistration.setRoles(Collections.singleton(Role.USER));
        userRegistration.setActive(true);
        userRegistrationRepository.save(userRegistration);
    }
}
