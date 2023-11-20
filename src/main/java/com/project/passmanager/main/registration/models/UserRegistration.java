package com.project.passmanager.main.registration.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс, в котором создается сущность пользователя
 */

@Entity
@Table(name = "my users")
public class UserRegistration {
    @Id
    @GeneratedValue
    /** Поле id */
    private Long id;
    /** Поле имени пользователя */
    private String username;
    /** Поле пароля */
    private String password;
    /** Поле статуса пользователя */
    private boolean active;

    /**
     * Аннотация ElementCollection указывает, что это элемент коллекции класса Role
     * параметр fetch Eager получает роли сразу при получении пользователя
     * CollectionTable указывает таблицу, в которой будут сохраняться пользователи
     * Enumerated указывает, что данные будут сохраняться в виде строки
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    public UserRegistration()
    {
    }

    /**
     * Конструктор для получения нового объекта
     * @param name имя пользователя
     * @param password пароль пользователя
     * @param role роль пользователя
     */
    public UserRegistration(String name, String password, Role role) {
        this.username = name;
        this.password = password;
        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
