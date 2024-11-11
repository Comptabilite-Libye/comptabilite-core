/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.service;

import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsFactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.domaine.FactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.dto.DetailsFactureFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.dto.FactureFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.factory.DetailsFactureFournisseurFactory;
import com.DevPointSystem.Comptabilite.Depense.factory.FactureFournisseurFactory;
import com.DevPointSystem.Comptabilite.Depense.repository.DetailsFactureFournisseurRepo;
import com.DevPointSystem.Comptabilite.Depense.repository.FactureFournisseurRepo;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Compteur;
import com.DevPointSystem.Comptabilite.Parametrage.service.CompteurService;
import com.DevPointSystem.Comptabilite.Recette.repository.MouvementCaisseRepo;
import com.DevPointSystem.Comptabilite.Recette.repository.SoldeCaisseRepo;
import com.DevPointSystem.Comptabilite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class FactureFournisseurService {

    private final FactureFournisseurRepo factureFournisseurRepo;

    private final CompteurService compteurService;

    private final MouvementCaisseRepo mouvementCaisseRepo;

    private final DetailsFactureFournisseurRepo detailsFactureFournisseurRepo;

    public FactureFournisseurService(FactureFournisseurRepo factureFournisseurRepo, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo, DetailsFactureFournisseurRepo detailsFactureFournisseurRepo) {
        this.factureFournisseurRepo = factureFournisseurRepo;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.detailsFactureFournisseurRepo = detailsFactureFournisseurRepo;
    }

 
 

    @Transactional(readOnly = true)
    public List<FactureFournisseurDTO> findAllFactureFournisseur() {

        return FactureFournisseurFactory.listFactureFournisseurToFactureFournisseurDTOs(factureFournisseurRepo.findAllByOrderByCodeSaisieDesc());

    }

    @Transactional(readOnly = true)
    public FactureFournisseurDTO findOne(Integer code) {
        FactureFournisseur domaine = factureFournisseurRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.FactureFournisseurNotFound");
        return FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTOUpdate(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<FactureFournisseurDTO> findOneCollection(Boolean Paid, Date dateDebut, Date dateFin) { 
        Collection<FactureFournisseur> domaine = factureFournisseurRepo.findByPaidAndDateCreateBetween(Paid, dateDebut, dateFin);
        
          Preconditions.checkArgument(!domaine.isEmpty(), "error.NoData");
        return FactureFournisseurFactory.CollectionfactureFournisseursTofactureFournisseursDTOsCollection(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<FactureFournisseurDTO> findByCodeFournisseur(Collection<Integer> codeFournisseur) {
        Collection<FactureFournisseur> result = factureFournisseurRepo.findByCodeFournisseurIn(Helper.removeNullValueFromCollection(codeFournisseur));
        return FactureFournisseurFactory.CollectionfactureFournisseursTofactureFournisseursDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public List<FactureFournisseurDTO> findByCodeFournisseurAndCodeDeviseAndNotPaid(Integer codeFournisseur, Integer codeDevise, Boolean hasOrdrePaiement) {
        List<FactureFournisseur> result = factureFournisseurRepo.findByCodeFournisseurAndCodeDeviseAndHasOrdrePaiement(codeFournisseur, codeDevise, hasOrdrePaiement);
        return FactureFournisseurFactory.listFactureFournisseurToFactureFournisseurDTOs(result);
    }

    @Transactional(readOnly = true)
    public List<FactureFournisseurDTO> findByCodeFournisseurAndCodeDeviseAndNotPaidAndApprouved(Integer codeFournisseur, Integer codeDevise, Boolean hasOrdrePaiement, Integer EtatApprouve) {
        List<FactureFournisseur> result = factureFournisseurRepo.findByCodeFournisseurAndCodeDeviseAndHasOrdrePaiementAndCodeEtatApprouver(codeFournisseur, codeDevise, hasOrdrePaiement, EtatApprouve);
        return FactureFournisseurFactory.listFactureFournisseurToFactureFournisseurDTOs(result);
    }

    @Transactional(readOnly = true)
    public Collection<FactureFournisseurDTO> findByCodeDevise(Collection<Integer> codeDevise) {
        Collection<FactureFournisseur> result = factureFournisseurRepo.findByCodeDeviseIn(Helper.removeNullValueFromCollection(codeDevise));
        return FactureFournisseurFactory.CollectionfactureFournisseursTofactureFournisseursDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public List<FactureFournisseurDTO> findByEtatApprouver(Integer CodeEtatApprouver) {
        return FactureFournisseurFactory.listFactureFournisseurToFactureFournisseurDTOs(factureFournisseurRepo.findFactureFournisseurByCodeEtatApprouver(CodeEtatApprouver));
    }

    @Transactional(readOnly = true)
    public List<FactureFournisseurDTO> findByEtatApprouverLazy(Integer CodeEtatApprouver) {
        return FactureFournisseurFactory.listFactureFournisseurToFactureFournisseurDTOsLazy(factureFournisseurRepo.findFactureFournisseurByCodeEtatApprouver(CodeEtatApprouver));
    }

    public FactureFournisseurDTO save(FactureFournisseurDTO dto) {

        FactureFournisseur domaine = FactureFournisseurFactory.factureFournisseurDTOToFactureFournisseur(new FactureFournisseur(), dto);
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieFF");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = factureFournisseurRepo.save(domaine);
        return FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(domaine);
    }

    public FactureFournisseurDTO updateNewWithFlush(FactureFournisseurDTO dto) {
        FactureFournisseur inBase = factureFournisseurRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");

        Preconditions.checkArgument(!inBase.getHasOrdrePaiement().equals(Boolean.TRUE), "error.FactureFournisseurHasOrdrePaiement");
        System.out.println("  getHasOrdrePaiement OK Code " + inBase.getHasOrdrePaiement());

        detailsFactureFournisseurRepo.deleteByCodeFactureFournisseur(dto.getCode());
        System.out.println("flush deleted OK Code " + dto.getCode());

        inBase = FactureFournisseurFactory.factureFournisseurDTOToFactureFournisseur(inBase, dto);
        inBase = factureFournisseurRepo.save(inBase);
        FactureFournisseurDTO resultDTO = FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    public void deleteFactureFournisseur(Integer code) {
        Preconditions.checkArgument(factureFournisseurRepo.existsById(code), "error.FactureFournisseurNotFound");
        FactureFournisseur inBase = factureFournisseurRepo.findByCode(code);
        Preconditions.checkArgument(inBase.getHasOrdrePaiement().equals(Boolean.TRUE), "error.FactureFournisseurHasOrdrePaiement");

        factureFournisseurRepo.deleteById(code);
    }

    public FactureFournisseurDTO approuveAC(FactureFournisseurDTO dto) {
        FactureFournisseur inBase = factureFournisseurRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        Preconditions.checkArgument(inBase.getHasOrdrePaiement() != true, "error.FactureFournisseurApprouved");
        inBase = FactureFournisseurFactory.ApprouveFactureFournisseurDTOToFactureFournisseur(inBase, dto);
        inBase = factureFournisseurRepo.save(inBase);
        FactureFournisseurDTO resultDTO = FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    public FactureFournisseurDTO CancelapprouveAC(FactureFournisseurDTO dto) {
        FactureFournisseur inBase = factureFournisseurRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");

        Preconditions.checkArgument(inBase.getHasOrdrePaiement() != true, "error.FactureFournisseurApprouved");

        inBase = FactureFournisseurFactory.CancelFactureFournisseurDTOToFactureFournisseur(inBase, dto);
        mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());
        inBase = factureFournisseurRepo.save(inBase);
        FactureFournisseurDTO resultDTO = FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    @Transactional(readOnly = true)
    public Collection<DetailsFactureFournisseurDTO> findOneWithDetails(Integer code) {
        Collection<DetailsFactureFournisseur> domaine = detailsFactureFournisseurRepo.findByDetailsFactureFournisseurPK_codeFactureFournisseur(code);
        return DetailsFactureFournisseurFactory.detailsFactureFournisseurTodetailsFactureFournisseurDTOCollections(domaine);
    }

//    @Transactional(readOnly = true)
//    public Collection<FactureFournisseurDTO> findOneWithDetailsForDateAndPaied(Boolean HasOrdrePaiement, Long dateDebut, Long dateFin, Integer code) {
//
//        List<FactureFournisseur> x = new ArrayList<>();
//        QFactureFournisseur qFactureAdmission = QFactureFournisseur.factureFournisseur;
//        WhereClauseBuilder builder = new WhereClauseBuilder().optionalAnd(dateDebut, () -> qFactureAdmission.dateFacture.goe(Helper.resetTime(new Date(dateDebut))))
//                .optionalAnd(dateFin, () -> qFactureAdmission.dateFacture.loe(Helper.getLastHourInDate(new Date(dateFin))))
//                .and(qFactureAdmission.admissionFacturation().admission().numSoc.isNotNull())
//                .and(qFactureAdmission.bordereauSociete().code.isNull());
//
//        return FactureFournisseurFactory.CollectionfactureFournisseursTofactureFournisseursDTOsCollection(x);
//    }
    public FactureFournisseurDTO CreatedOrdrePaiement(FactureFournisseurDTO dto) {
        FactureFournisseur inBase = factureFournisseurRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        inBase = FactureFournisseurFactory.CreatedOrdrePaiement(inBase, dto);
        inBase = factureFournisseurRepo.save(inBase);
        FactureFournisseurDTO resultDTO = FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(inBase);
        return resultDTO;
    }
    
        public FactureFournisseurDTO CreatedPaieOrdrePaiement(FactureFournisseurDTO dto) {
        FactureFournisseur inBase = factureFournisseurRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        inBase = FactureFournisseurFactory.PaieOrdrePaiement(inBase, dto);
        inBase = factureFournisseurRepo.save(inBase);
        FactureFournisseurDTO resultDTO = FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    public FactureFournisseurDTO DeleteOrdrePaiement(FactureFournisseurDTO dto) {
        FactureFournisseur inBase = factureFournisseurRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        inBase = FactureFournisseurFactory.DeletedOrdrePaiement(inBase, dto);
        inBase = factureFournisseurRepo.save(inBase);
        FactureFournisseurDTO resultDTO = FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(inBase);
        return resultDTO;
    }
    
       public FactureFournisseurDTO DeletePaieOrdrePaiement(FactureFournisseurDTO dto) {
        FactureFournisseur inBase = factureFournisseurRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        inBase = FactureFournisseurFactory.DeletePaieOrdrePaiement(inBase, dto);
        inBase = factureFournisseurRepo.save(inBase);
        FactureFournisseurDTO resultDTO = FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(inBase);
        return resultDTO;
    }

}
