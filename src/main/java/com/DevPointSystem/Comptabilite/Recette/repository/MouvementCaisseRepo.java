/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.repository;

import com.DevPointSystem.Comptabilite.Recette.domaine.MouvementCaisse;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface MouvementCaisseRepo extends JpaRepository<MouvementCaisse, Integer>{
    
//    Collection<MouvementCaisse> findMouvementCaisseByCodeModeReglement(Collection<Integer> codeModeReglement);  
//    Collection<MouvementCaisse> findMouvementCaisseByCodeDevise(Collection<Integer> codeDevise);   
//    Collection<MouvementCaisse> findMouvementCaisseByCodeCaisse(Collection<Integer> codeCaisse);  
//    Collection<MouvementCaisse> findMouvementCaisseByCodeTier(Collection<String> codeTier);



    
}
