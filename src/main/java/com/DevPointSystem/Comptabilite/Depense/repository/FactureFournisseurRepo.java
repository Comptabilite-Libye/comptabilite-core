/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.repository;
 
import com.DevPointSystem.Comptabilite.Depense.domaine.FactureFournisseur;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface FactureFournisseurRepo extends JpaRepository<FactureFournisseur, Integer> {

    List<FactureFournisseur> findByCodeFournisseurIn(Collection<Integer> codeFournisseur);

    List<FactureFournisseur> findByCodeDeviseIn(Collection<Integer> codeDevise);

    List<FactureFournisseur> findFactureFournisseurByCodeEtatApprouver(Integer codeEtatApprouver);

    List<FactureFournisseur> findAllByOrderByCodeSaisieDesc();
    
        List<FactureFournisseur> findByCodeFournisseurAndCodeDeviseAndPaid(Integer codeFournisseur,Integer codeDevise, Boolean Paid);
 

}
