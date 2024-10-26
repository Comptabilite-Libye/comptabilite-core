/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.repository;

import com.DevPointSystem.Comptabilite.Depense.domaine.ReglementFactureFrs;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface ReglementFactureFrsRepo extends JpaRepository<ReglementFactureFrs, Integer> {

    List<ReglementFactureFrs> findByCodeFournisseurIn(Collection<Integer> codeFournisseur);

    List<ReglementFactureFrs> findByCodeDeviseIn(Collection<Integer> codeDevise);

    List<ReglementFactureFrs> findReglementFactureFrsByCodeEtatApprouver(Integer codeEtatApprouver);

    List<ReglementFactureFrs> findAllByOrderByCodeSaisieDesc();

    List<ReglementFactureFrs> findByCodeFournisseurAndCodeDevise(Integer codeFournisseur, Integer codeDevise);

}
