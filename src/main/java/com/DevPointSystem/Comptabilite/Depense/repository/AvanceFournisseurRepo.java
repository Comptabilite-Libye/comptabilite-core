/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.repository;

import com.DevPointSystem.Comptabilite.Depense.domaine.AvanceFournisseur;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface AvanceFournisseurRepo extends JpaRepository<AvanceFournisseur, Integer> {

    List<AvanceFournisseur> findByCodeFournisseurIn(Collection<Integer> codeFournisseur);

    List<AvanceFournisseur> findByCodeDeviseIn(Collection<Integer> codeDevise);

    List<AvanceFournisseur> findAvanceFournisseurByCodeEtatApprouver(Integer codeEtatApprouver);

    List<AvanceFournisseur> findByCodeFournisseurAndApurer(Integer codeFournisseur, Boolean apurer);

    List<AvanceFournisseur> findAllByOrderByCodeSaisieDesc();

    AvanceFournisseur findByCodeSaisie(String codeSaisie);

    AvanceFournisseur findByCode(Integer code);

    @Query("SELECT ff FROM AvanceFournisseur ff WHERE ff.paid =?1 AND  ff.dateCreate BETWEEN ?2  AND ?3")
    Collection<AvanceFournisseur> findByPaidAndDateCreateBetween(Boolean paid, Date dateDebut, Date dateFin);

    @Query("SELECT ff FROM AvanceFournisseur ff WHERE   ff.dateCreate BETWEEN ?1  AND ?1")
    Collection<AvanceFournisseur> findByDateCreateBetween(Date dateDebut, Date dateFin);

}
