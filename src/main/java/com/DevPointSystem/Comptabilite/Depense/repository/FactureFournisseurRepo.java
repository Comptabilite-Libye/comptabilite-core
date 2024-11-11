/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.repository;

import com.DevPointSystem.Comptabilite.Depense.domaine.FactureFournisseur;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface FactureFournisseurRepo extends JpaRepository<FactureFournisseur, Integer> {

    FactureFournisseur findByCode(Integer code);

    List<FactureFournisseur> findByCodeFournisseurIn(Collection<Integer> codeFournisseur);

    List<FactureFournisseur> findByCodeDeviseIn(Collection<Integer> codeDevise);

    List<FactureFournisseur> findFactureFournisseurByCodeEtatApprouver(Integer codeEtatApprouver);

    List<FactureFournisseur> findAllByOrderByCodeSaisieDesc();

    List<FactureFournisseur> findByCodeFournisseurAndCodeDeviseAndHasOrdrePaiement(Integer codeFournisseur, Integer codeDevise, Boolean hasOrdrePaiement);

    List<FactureFournisseur> findByCodeFournisseurAndCodeDeviseAndHasOrdrePaiementAndCodeEtatApprouver(Integer codeFournisseur, Integer codeDevise, Boolean hasOrdrePaiement, Integer codeEtatApprouver);

//   @Query("SELECT ff FROM FactureFournisseur ff WHERE ff.dateCreate BETWEEN :dateDebut AND :dateFin AND ff.hasOrdrePaiement=?3" )
//    Collection<FactureFournisseur> findFactureFournisseursByDateCreateBetween(Date dateDebut, Date dateFin,Boolean hasOrdrePaiement);
//       @Query("SELECT ff FROM FactureFournisseur ff WHERE ff.dateCreate BETWEEN ff.dateCreate=?1 AND ff.dateCreate=?2 AND ff.hasOrdrePaiement=?3" )
//    Collection<FactureFournisseur> findFactureFournisseursByDateCreateBetweenAndHasOrdrePaiement(Long dateDebut, Long dateFin,Boolean hasOrdrePaiement);
//    
    Collection<FactureFournisseur> findByDateCreateBetweenAndHasOrdrePaiement(Date dateDebut, Date dateFin, Boolean hasOrdrePaiement);

    @Query("SELECT ff FROM FactureFournisseur ff WHERE ff.paid =?1 AND  ff.dateCreate BETWEEN ?2  AND ?3")
    Collection<FactureFournisseur> findByPaidAndDateCreateBetween(Boolean paid, Date dateDebut, Date dateFin);

}
