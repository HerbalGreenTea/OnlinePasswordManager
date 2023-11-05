package com.project.passmanager.main.registration.repositories;

import com.project.passmanager.main.registration.models.UserRegistration;
import org.springframework.data.repository.CrudRepository;

public interface UserRegistrationRepository extends CrudRepository<UserRegistration, Long> {
    UserRegistration findByUsername(String username);
}
