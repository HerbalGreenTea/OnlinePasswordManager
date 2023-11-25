package com.project.passmanager.main.database.core;

import com.project.passmanager.main.database.models.SecretSpaceEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecretSpaceDAO {
    @Autowired
    SessionFactory sessionFactory;

    public void putSecretSpaceByUser(SecretSpaceEntity secretSpace) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(secretSpace);
            session.getTransaction().commit();
        }
    }

    public List<SecretSpaceEntity> getSecretSpacesByUserId(String userId) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            var secretSpaceEntities = session.createQuery(
                            "SELECT ss FROM SecretSpaceEntity ss WHERE ss.FK_user = :user_id",
                            SecretSpaceEntity.class
                    )
                    .setParameter("user_id", userId)
                    .getResultList();
            session.getTransaction().commit();
            return secretSpaceEntities;
        }
    }

    public void deleteSecretSpaceById(String secretSpaceId) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            var secretSpace = session.find(SecretSpaceEntity.class, secretSpaceId);
            if (secretSpace != null)
                session.remove(secretSpace);
            session.getTransaction().commit();
        }
    }

}
