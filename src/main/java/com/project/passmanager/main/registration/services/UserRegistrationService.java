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

@Component
public class UserRegistrationService implements UserDetailsService {
    private final UserRegistrationRepository userRegistrationRepository;
@Autowired
    public UserRegistrationService(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRegistration myUserRegistration = userRegistrationRepository.findByUsername(username);
        return new User(myUserRegistration.getUsername(), myUserRegistration.getPassword(), mapRolesToAthorities(myUserRegistration.getRoles()));
    }
    private List<? extends GrantedAuthority> mapRolesToAthorities (Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+ role.name())).collect(Collectors.toList());
    }
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
