/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.repository;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Beneficiaire; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface BeneficiaireRepo   extends JpaRepository<Beneficiaire, Integer>{
    
}
