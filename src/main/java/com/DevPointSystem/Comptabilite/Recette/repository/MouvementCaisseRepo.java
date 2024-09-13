/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.repository;

import com.DevPointSystem.Comptabilite.Recette.domaine.MouvementCaisse;
import com.DevPointSystem.Comptabilite.Recette.dto.MouvementCaisseDTO;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface MouvementCaisseRepo extends JpaRepository<MouvementCaisse, Integer> {

//    Collection<MouvementCaisse> findMouvementCaisseByCodeModeReglement(Collection<Integer> codeModeReglement);  
//    Collection<MouvementCaisse> findMouvementCaisseByCodeDevise(Collection<Integer> codeDevise);   
//    Collection<MouvementCaisse> findMouvementCaisseByCodeCaisse(Collection<Integer> codeCaisse);  
//    Collection<MouvementCaisse> findMouvementCaisseByCodeTier(Collection<String> codeTier);
    MouvementCaisse findMouvementCaisseByCodeSaisie(String codeSaisie);

//    MouvementCaisse findMouvementCaisseByCodeCaisse(Integer codeCaisse);

    boolean existsByCodeCaisse(Integer codeCaisse);

    @Modifying
    @Query("delete from MouvementCaisse det where det.codeSaisie=?1 ")
    public void deleteByCodeSaisie(String codeSaisie);

    @Query("SELECT NEW com.DevPointSystem.Comptabilite.Recette.dto.MouvementCaisseDTO( det.codeCaisse , Sum(det.debit) , Sum(det.credit) ) "
            + " FROM MouvementCaisse det "
            + "Group by det.codeCaisse"
    )
    List<MouvementCaisseDTO> calculDebitAndCredit();

}
