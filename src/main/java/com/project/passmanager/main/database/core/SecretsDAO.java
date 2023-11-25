package com.project.passmanager.main.database.core;

import com.project.passmanager.main.database.models.SecretEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecretsDAO {
    @Autowired
    SessionFactory sessionFactory;

    public List<SecretEntity> getSecretsBySecretSpaceId(String FK_secretSpace) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            var secrets = session.createQuery(
                            "SELECT secret FROM SecretEntity secret WHERE secret.FK_secretSpace = :secretSpace_id",
                            SecretEntity.class)
                    .setParameter("secretSpace_id", FK_secretSpace)
                    .getResultList();
            session.getTransaction().commit();
            return secrets;
        }

    }

    public SecretEntity getSecretById(String secretId) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            var secret = session.find(SecretEntity.class, secretId);
            session.getTransaction().commit();
            if (secret != null)
                return secret;
            else
                throw new TransactionException("Database query error");
        }
    }

    public void putSecret(SecretEntity secret) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.merge(secret);
            session.getTransaction().commit();
        }
    }

    public void deleteSecretById(String secretId) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            var secret = session.find(SecretEntity.class, secretId);
            if (secret != null)
                session.remove(secret);
            session.getTransaction().commit();
        }
    }

    public void deleteAllSecretsBySecretSpace(String FK_secretSpace) throws TransactionException {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            var secrets = session
                    .createQuery(
                            "SELECT secret FROM SecretEntity secret WHERE secret.FK_secretSpace = :secretSpace_id",
                            SecretEntity.class
                    )
                    .setParameter("secretSpace_id", FK_secretSpace)
                    .getResultList();

            if (secrets != null)
                for (var secret : secrets) {
                    session.remove(secret);
                }

            session.getTransaction().commit();
        }
    }

}
