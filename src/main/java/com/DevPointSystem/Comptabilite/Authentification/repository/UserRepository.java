/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Authentification.repository;

/**
 *
 * @author Administrator
 */
 
import com.DevPointSystem.Comptabilite.Authentification.domaine.User; 
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.ListCrudRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository; 

@Repository 
public interface UserRepository extends ListCrudRepository<User, Integer> {
    Optional<User> findUserByUserName(String userName);
}
