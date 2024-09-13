/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Authentification.repository;

import com.DevPointSystem.Comptabilite.Authentification.domaine.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface AccessUserRepo extends JpaRepository<User, Integer> {

    User findFirstBy();
//    AccessUser findAccessUserByUserName(String UserName);

    User findUserByUserName(String userName);

}