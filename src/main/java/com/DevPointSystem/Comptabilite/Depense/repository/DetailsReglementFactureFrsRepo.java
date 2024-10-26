/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.repository;

import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsReglementFactureFrs;
import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsReglementFactureFrsPK;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Administrator
 */
public interface DetailsReglementFactureFrsRepo extends JpaRepository<DetailsReglementFactureFrs, DetailsReglementFactureFrsPK> {

    Collection<DetailsReglementFactureFrs> findByDetailsReglementFactureFrsPK_codeReglementFactureFournisseur(Integer codeReglementFactureFournisseur);

    @Modifying
    @Query("delete from DetailsReglementFactureFrs det where det.detailsReglementFactureFrsPK.codeReglementFactureFournisseur=?1 ")
    public void deleteByCodeReglementFactureFournisseur(Integer codeReglementFactureFournisseur);

}
