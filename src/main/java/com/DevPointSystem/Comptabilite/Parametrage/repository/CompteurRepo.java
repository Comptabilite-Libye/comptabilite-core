/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.repository;
 
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Compteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface CompteurRepo  extends JpaRepository<Compteur, Integer> {
     Compteur findByCompteur(String compteur);
}
