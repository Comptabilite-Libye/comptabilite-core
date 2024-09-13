/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;

/**
 *
 * @author Administrator
 */
public class CustomCommonRepositoryImpl<T, ID extends Serializable> extends QuerydslJpaRepository<T, ID> implements CustomCommonRepository<T, ID> {

    private final EntityManager entityManager;
    private final Logger log = LoggerFactory.getLogger(CustomCommonRepositoryImpl.class);

    public CustomCommonRepositoryImpl(JpaEntityInformation<T, ID> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);

        // This is the recommended method for accessing inherited class dependencies.
        this.entityManager = entityManager;
    }

    /**
     *
     * @param entity (entity to be persisted)
     * @param tableName ( Table Name in Database )
     *
     */
    @Override
    public void persistEntityUsingTableName(T entity, String tableName) {
        Session session = entityManager.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    connection.setAutoCommit(false);
                    Statement statement = connection.createStatement();
                    statement.execute(String.format("SET IDENTITY_INSERT [param].%s OFF", tableName));
                    statement.execute(String.format("SET IDENTITY_INSERT [param].%s ON", tableName));
                    session.merge(entity);
                    session.flush();
                    statement.execute(String.format("SET IDENTITY_INSERT [param].%s OFF", tableName));

                } catch (SQLException | HibernateException e) {
                    connection.createStatement().execute(String.format("SET IDENTITY_INSERT [param].%s OFF", tableName));
                }
            }

        });
    }

 
    @Override
    public void persistEntity(T entity) {
        String tableName = entity.getClass().getAnnotation(Table.class).name();
        log.debug("getting Table Annotation From Class : {}", tableName);
        persistEntityUsingTableName(entity, tableName);
    }

}
