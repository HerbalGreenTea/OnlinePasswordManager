package com.project.passmanager.main.database.core;

import com.project.passmanager.main.database.models.UserEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<UserEntity> getAllUsers() throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            var users = session
                    .createQuery("FROM UserEntity", UserEntity.class)
                    .getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    public UserEntity getUserById(String userId) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            var user = session.createQuery(
                            "FROM UserEntity WHERE id = :userId",
                            UserEntity.class
                    )
                    .setParameter("userId", userId)
                    .uniqueResult();
            session.getTransaction().commit();
            return user;
        }
    }

    public UserEntity getUserByLogin(String login) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            var user = session.createQuery(
                            "SELECT u FROM UserEntity u WHERE u.login = :login",
                            UserEntity.class
                    )
                    .setParameter("login", login)
                    .uniqueResult();
            session.getTransaction().commit();
            return user;
        }
    }

    public void putUser(UserEntity user) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    public void deleteUserById(String userId) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            var user = session.find(UserEntity.class, userId);
            if (user != null)
                session.remove(user);
            session.getTransaction().commit();
        }
    }
}
