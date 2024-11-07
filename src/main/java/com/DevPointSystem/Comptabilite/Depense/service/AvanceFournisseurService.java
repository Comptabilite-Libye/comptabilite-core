/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.service;

import com.DevPointSystem.Comptabilite.Depense.domaine.AvanceFournisseur;
import com.DevPointSystem.Comptabilite.Depense.dto.AvanceFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.factory.AvanceFournisseurFactory;
import com.DevPointSystem.Comptabilite.Depense.repository.AvanceFournisseurRepo;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Compteur;
import com.DevPointSystem.Comptabilite.Parametrage.service.CompteurService;
import com.DevPointSystem.Comptabilite.Recette.repository.MouvementCaisseRepo;
import com.DevPointSystem.Comptabilite.Recette.repository.SoldeCaisseRepo;
import com.DevPointSystem.Comptabilite.web.Util.Helper;
import com.google.common.base.Preconditions;
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
public class AvanceFournisseurService {

    private final AvanceFournisseurRepo avanceFournisseurRepo;

    private final CompteurService compteurService;

    private final MouvementCaisseRepo mouvementCaisseRepo;

    private final SoldeCaisseRepo soldeCaisseRepo;

    public AvanceFournisseurService(AvanceFournisseurRepo avanceFournisseurRepo, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo, SoldeCaisseRepo soldeCaisseRepo) {
        this.avanceFournisseurRepo = avanceFournisseurRepo;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.soldeCaisseRepo = soldeCaisseRepo;
    }

    @Transactional(readOnly = true)
    public List<AvanceFournisseurDTO> findAllAvanceFournisseur() {

        return AvanceFournisseurFactory.listAvanceFournisseurToAvanceFournisseurDTOs(avanceFournisseurRepo.findAllByOrderByCodeSaisieDesc());

    }

    @Transactional(readOnly = true)
    public AvanceFournisseurDTO findOne(Integer code) {
        AvanceFournisseur domaine = avanceFournisseurRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.AvanceFournisseurNotFound");
        return AvanceFournisseurFactory.avanceFournisseurToAvanceFournisseurDTOUpdate(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<AvanceFournisseurDTO> findOneCollection(Boolean Paid, Date dateDebut, Date dateFin) {
        Collection<AvanceFournisseur> domaine = avanceFournisseurRepo.findByPaidAndDateCreateBetween(Paid, dateDebut, dateFin);

        Preconditions.checkArgument(!domaine.isEmpty(), "error.NoData");
        return AvanceFournisseurFactory.CollectionfAvanceToAvanceFournisseursDTOsCollection(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<AvanceFournisseurDTO> findByCodeFournisseur(Collection<Integer> codeFournisseur) {
        Collection<AvanceFournisseur> result = avanceFournisseurRepo.findByCodeFournisseurIn(Helper.removeNullValueFromCollection(codeFournisseur));
        return AvanceFournisseurFactory.CollectionfAvanceToAvanceFournisseursDTOsCollection(result);
    }

//    @Transactional(readOnly = true)
//    public List<AvanceFournisseurDTO> findByCodeFournisseurAndCodeDeviseAndNotPaid(Integer codeFournisseur, Integer codeDevise, Boolean hasOrdrePaiement) {
//        List<AvanceFournisseur> result = avanceFournisseurRepo.findByCodeFournisseurAndCodeDeviseAndHasOrdrePaiement(codeFournisseur, codeDevise, hasOrdrePaiement);
//        return AvanceFournisseurFactory.listAvanceFournisseurToAvanceFournisseurDTOs(result);
//    }
//    @Transactional(readOnly = true)
//    public List<AvanceFournisseurDTO> findByCodeFournisseurAndCodeDeviseAndNotPaidAndApprouved(Integer codeFournisseur, Integer codeDevise, Boolean hasOrdrePaiement, Integer EtatApprouve) {
//        List<AvanceFournisseur> result = avanceFournisseurRepo.findByCodeFournisseurAndCodeDeviseAndHasOrdrePaiementAndCodeEtatApprouver(codeFournisseur, codeDevise, hasOrdrePaiement, EtatApprouve);
//        return AvanceFournisseurFactory.listAvanceFournisseurToAvanceFournisseurDTOs(result);
//    }
    @Transactional(readOnly = true)
    public Collection<AvanceFournisseurDTO> findByCodeDevise(Collection<Integer> codeDevise) {
        Collection<AvanceFournisseur> result = avanceFournisseurRepo.findByCodeDeviseIn(Helper.removeNullValueFromCollection(codeDevise));
        return AvanceFournisseurFactory.CollectionfAvanceToAvanceFournisseursDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public List<AvanceFournisseurDTO> findByEtatApprouver(Integer CodeEtatApprouver) {
        return AvanceFournisseurFactory.listAvanceFournisseurToAvanceFournisseurDTOs(avanceFournisseurRepo.findAvanceFournisseurByCodeEtatApprouver(CodeEtatApprouver));
    }

    @Transactional(readOnly = true)
    public List<AvanceFournisseurDTO> findByEtatApprouverLazy(Integer CodeEtatApprouver) {
        return AvanceFournisseurFactory.listAvanceFournisseurToAvanceFournisseurDTOsLazy(avanceFournisseurRepo.findAvanceFournisseurByCodeEtatApprouver(CodeEtatApprouver));
    }

    public AvanceFournisseurDTO save(AvanceFournisseurDTO dto) {

        AvanceFournisseur domaine = AvanceFournisseurFactory.avanceForunisseurDTOTOAvanceFornisseur(new AvanceFournisseur(), dto);
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieFF");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = avanceFournisseurRepo.save(domaine);
        return AvanceFournisseurFactory.avanceFournisseurToAvanceFournisseurDTO(domaine);
    }

    public AvanceFournisseurDTO updateNewWithFlush(AvanceFournisseurDTO dto) {
        AvanceFournisseur inBase = avanceFournisseurRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.AvanceFournisseurNotFound");

        Preconditions.checkArgument(!inBase.getPaid().equals(Boolean.TRUE), "error.AvanceFournisseurHasOrdrePaiement");

        inBase = AvanceFournisseurFactory.avanceForunisseurDTOTOAvanceFornisseur(inBase, dto);
        inBase = avanceFournisseurRepo.save(inBase);
        AvanceFournisseurDTO resultDTO = AvanceFournisseurFactory.avanceFournisseurToAvanceFournisseurDTO(inBase);
        return resultDTO;
    }

    public void deleteAvanceFournisseur(Integer code) {
        Preconditions.checkArgument(avanceFournisseurRepo.existsById(code), "error.AvanceFournisseurNotFound");
        AvanceFournisseur inBase = avanceFournisseurRepo.getReferenceById(code);
        Preconditions.checkArgument(inBase.getPaid().equals(Boolean.TRUE), "error.AvanceFournisseurPaied");

        avanceFournisseurRepo.deleteById(code);
    }

    public AvanceFournisseurDTO approuveAvanceFournisseur(AvanceFournisseurDTO dto) {
        AvanceFournisseur inBase = avanceFournisseurRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.AvanceFournisseurNotFound");
        Preconditions.checkArgument(inBase.getPaid() != true, "error.AvanceFournisseurApprouved");
        inBase = AvanceFournisseurFactory.ApprouveAvanceFournisseurDTOToAvanceFournisseur(inBase, dto);
        inBase = avanceFournisseurRepo.save(inBase);
        AvanceFournisseurDTO resultDTO = AvanceFournisseurFactory.avanceFournisseurToAvanceFournisseurDTO(inBase);
        return resultDTO;
    }

    public AvanceFournisseurDTO CancelapprouveAvanceFournisseur(AvanceFournisseurDTO dto) {
        AvanceFournisseur inBase = avanceFournisseurRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.AvanceFournisseurNotFound");

        Preconditions.checkArgument(inBase.getPaid() != true, "error.AvanceFournisseurPaied");

        inBase = AvanceFournisseurFactory.CancelAvanceFournisseurDTOToAvanceFournisseur(inBase, dto);
        mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());
        inBase = avanceFournisseurRepo.save(inBase);
        AvanceFournisseurDTO resultDTO = AvanceFournisseurFactory.avanceFournisseurToAvanceFournisseurDTO(inBase);
        return resultDTO;
    }
 
//    public AvanceFournisseurDTO CreatedOrdrePaiement(AvanceFournisseurDTO dto) {
//        AvanceFournisseur inBase = avanceFournisseurRepo.getReferenceById(dto.getCode());
//        Preconditions.checkArgument(inBase != null, "error.AvanceFournisseurNotFound");
////        inBase = AvanceFournisseurFactory.CreatedOrdrePaiement(inBase, dto);
//        inBase = avanceFournisseurRepo.save(inBase);
//        AvanceFournisseurDTO resultDTO = AvanceFournisseurFactory.avanceFournisseurToAvanceFournisseurDTO(inBase);
//        return resultDTO;
//    }
//
//    public AvanceFournisseurDTO CreatedPaieOrdrePaiement(AvanceFournisseurDTO dto) {
//        AvanceFournisseur inBase = avanceFournisseurRepo.getReferenceById(dto.getCode());
//        Preconditions.checkArgument(inBase != null, "error.AvanceFournisseurNotFound");
//        inBase = AvanceFournisseurFactory.PaieOrdrePaiement(inBase, dto);
//        inBase = avanceFournisseurRepo.save(inBase);
//        AvanceFournisseurDTO resultDTO = AvanceFournisseurFactory.avanceFournisseurToAvanceFournisseurDTO(inBase);
//        return resultDTO;
//    }
//
//    public AvanceFournisseurDTO DeleteOrdrePaiement(AvanceFournisseurDTO dto) {
//        AvanceFournisseur inBase = avanceFournisseurRepo.getReferenceById(dto.getCode());
//        Preconditions.checkArgument(inBase != null, "error.AvanceFournisseurNotFound");
//        inBase = AvanceFournisseurFactory.DeletedOrdrePaiement(inBase, dto);
//        inBase = avanceFournisseurRepo.save(inBase);
//        AvanceFournisseurDTO resultDTO = AvanceFournisseurFactory.avanceFournisseurToAvanceFournisseurDTO(inBase);
//        return resultDTO;
//    }
//
//    public AvanceFournisseurDTO DeletePaieOrdrePaiement(AvanceFournisseurDTO dto) {
//        AvanceFournisseur inBase = avanceFournisseurRepo.getReferenceById(dto.getCode());
//        Preconditions.checkArgument(inBase != null, "error.AvanceFournisseurNotFound");
//        inBase = AvanceFournisseurFactory.DeletePaieOrdrePaiement(inBase, dto);
//        inBase = avanceFournisseurRepo.save(inBase);
//        AvanceFournisseurDTO resultDTO = AvanceFournisseurFactory.avanceFournisseurToAvanceFournisseurDTO(inBase);
//        return resultDTO;
//    }
}
