/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.repository;

/**
 *
 * @author Administrator
 */
import java.io.Serializable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository; 

/**
 *
 * @author Administrateur
 * @param <T>
 * @param <ID>
 */

@NoRepositoryBean
public interface CustomCommonRepository <T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>, QuerydslPredicateExecutor<T> {
    
    /**
     *
     * @param entity
     * @param tableName
     */
    void persistEntityUsingTableName(T entity,String tableName);
  
    /**
     *
     * @param entity
     */
    void persistEntity(T entity);

}
