package com.project.passmanager.main.database;

import com.project.passmanager.main.database.core.SecretSpaceDAO;
import com.project.passmanager.main.database.core.SecretsDAO;
import com.project.passmanager.main.database.core.UsersDAO;
import com.project.passmanager.main.database.mappers.SecretMapper;
import com.project.passmanager.main.database.mappers.SecretSpaceMapper;
import com.project.passmanager.main.database.mappers.UserMapper;
import com.project.passmanager.main.database.models.SecretEntity;
import com.project.passmanager.main.database.models.SecretSpaceEntity;
import com.project.passmanager.main.database.models.UserEntity;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class BeansConfiguration {
    @Bean
    public SessionFactory sessionFactory(){
        var properties = new Properties();
        properties.setProperty("hibernate.connection.url", System.getenv("opm.db.url"));
        properties.setProperty("connection.driver_class", System.getenv("opm.db.driver_class"));
        properties.setProperty("hibernate.connection.username", System.getenv("opm.db.username"));
        properties.setProperty("hibernate.connection.password", System.getenv("opm.db.password"));
        properties.setProperty("hibernate.current_session_context_class", "thread");
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

        return new org.hibernate.cfg.Configuration()
                .addProperties(properties)
                .addAnnotatedClass(UserEntity.class)
                .addAnnotatedClass(SecretEntity.class)
                .addAnnotatedClass(SecretSpaceEntity.class)
                .buildSessionFactory();
    }
    @Bean
    public SecretMapper secretMapper(){
        return new SecretMapper();
    }
    @Bean
    public SecretSpaceMapper secretSpaceMapper() {
        return new SecretSpaceMapper();
    }
    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }
    @Bean
    public SecretsDAO secretsDAO(){
        return new SecretsDAO();
    }
    @Bean
    public SecretSpaceDAO secretSpaceDAO() {
        return new SecretSpaceDAO();
    }
    @Bean
    public UsersDAO usersDAO() {
        return new UsersDAO();
    }
}
