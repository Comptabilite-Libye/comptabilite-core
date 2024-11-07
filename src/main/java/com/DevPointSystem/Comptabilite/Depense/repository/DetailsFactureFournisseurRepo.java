/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.repository;

import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsFactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsFactureFournisseurPK;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsFactureFournisseurRepo extends JpaRepository<DetailsFactureFournisseur, DetailsFactureFournisseurPK> {

    Collection<DetailsFactureFournisseur> findByDetailsFactureFournisseurPK_codeFactureFournisseur(Integer codeFactureFournisseur);
 

    @Modifying
    @Query("delete from DetailsFactureFournisseur det where det.detailsFactureFournisseurPK.codeFactureFournisseur=?1 ")
    public void deleteByCodeFactureFournisseur(Integer codeFactureFournisseur);

}
