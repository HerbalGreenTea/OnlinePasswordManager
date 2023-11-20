package com.project.passmanager.main.registration.repositories;

import com.project.passmanager.main.registration.models.UserRegistration;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий позволяет добавлять, сохранять и получать пользователей из базы данных
 */
public interface UserRegistrationRepository extends CrudRepository<UserRegistration, Long> {
    /**
     * Метод, получающий пользователя по имени
     *
     * @param username - имя пользователя
     */
    UserRegistration findByUsername(String username);
}
