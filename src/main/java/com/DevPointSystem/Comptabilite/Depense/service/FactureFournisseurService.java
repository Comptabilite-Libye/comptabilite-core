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
import java.util.Collection;
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

    private final SoldeCaisseRepo soldeCaisseRepo; 

    public FactureFournisseurService(FactureFournisseurRepo factureFournisseurRepo, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo, DetailsFactureFournisseurRepo detailsFactureFournisseurRepo, SoldeCaisseRepo soldeCaisseRepo) {
        this.factureFournisseurRepo = factureFournisseurRepo;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.detailsFactureFournisseurRepo = detailsFactureFournisseurRepo;
        this.soldeCaisseRepo = soldeCaisseRepo;
    }

     

    @Transactional(readOnly = true)
    public List<FactureFournisseurDTO> findAllFactureFournisseur() {
     
        return FactureFournisseurFactory.listFactureFournisseurToFactureFournisseurDTOs(factureFournisseurRepo.findAllByOrderByCodeSaisieDesc());

    }

    @Transactional(readOnly = true)
    public FactureFournisseurDTO findOne(Integer code) {
        FactureFournisseur domaine = factureFournisseurRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.FactureFournisseurNotFound");
        return FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTOUpdate(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<FactureFournisseurDTO> findByCodeFournisseur(Collection<Integer> codeFournisseur) {
        Collection<FactureFournisseur> result = factureFournisseurRepo.findByCodeFournisseurIn(Helper.removeNullValueFromCollection(codeFournisseur));
        return FactureFournisseurFactory.CollectionfactureFournisseursTofactureFournisseursDTOsCollection(result);
    }
    
    
    
    @Transactional(readOnly = true)
    public List<FactureFournisseurDTO> findByCodeFournisseurAndCodeDeviseAndNotPaid(Integer codeFournisseur,Integer codeDevise, Boolean Paid) {
        List<FactureFournisseur> result = factureFournisseurRepo.findByCodeFournisseurAndCodeDeviseAndPaid(codeFournisseur, codeDevise,Paid);
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

    public FactureFournisseurDTO save(FactureFournisseurDTO dto) {

        FactureFournisseur domaine = FactureFournisseurFactory.factureFournisseurDTOToFactureFournisseur(new FactureFournisseur(), dto); 
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieFF");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = factureFournisseurRepo.save(domaine);
        return FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(domaine);
    }
//
//    public FactureFournisseur update(FactureFournisseurDTO dTO) { 
//        FactureFournisseur domaine = factureFournisseurRepo.getReferenceById(dTO.getCode());
//        Preconditions.checkArgument(true, "error.FactureFournisseurNotFound");
//    
//        domaine.getDetailsFactureFournisseurs().clear();
//        factureFournisseurRepo.flush();
//        FactureFournisseurFactory.factureFournisseurDTOToFactureFournisseur(dTO, domaine);
//        return factureFournisseurRepo.save(domaine);
//    }
//    

//    public FactureFournisseurDTO update(FactureFournisseurDTO dto) {
//
//        FactureFournisseur inBase = factureFournisseurRepo.getReferenceById(dto.getCode());
//        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
////        inBase.getDetailsFactureFournisseurs().clear();
//
//        detailsFactureFournisseurRepo.deleteByCodeFactureFournisseur(inBase.getCode());
//        factureFournisseurRepo.deleteById(inBase.getCode());
//        inBase = FactureFournisseurFactory.factureFournisseurDTOToFactureFournisseur(inBase, dto);
//        inBase = factureFournisseurRepo.save(inBase);
//        FactureFournisseurDTO resultDTO = FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(inBase);
//        return resultDTO;
//    }
    public FactureFournisseurDTO updateNewWithFlush(FactureFournisseurDTO dto) {
        FactureFournisseur inBase = factureFournisseurRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        detailsFactureFournisseurRepo.deleteByCodeFactureFournisseur(dto.getCode()); 
        System.out.println("flush deleted OK Code " + dto.getCode());

        inBase = FactureFournisseurFactory.factureFournisseurDTOToFactureFournisseur(inBase, dto);
        inBase = factureFournisseurRepo.save(inBase);
        FactureFournisseurDTO resultDTO = FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    public void deleteFactureFournisseur(Integer code) {
        Preconditions.checkArgument(factureFournisseurRepo.existsById(code), "error.FactureFournisseurNotFound");
        factureFournisseurRepo.deleteById(code);
    }

    public FactureFournisseurDTO approuveAC(FactureFournisseurDTO dto) {
        FactureFournisseur inBase = factureFournisseurRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        inBase = FactureFournisseurFactory.ApprouveFactureFournisseurDTOToFactureFournisseur(inBase, dto);

         
        inBase = factureFournisseurRepo.save(inBase);
 
        FactureFournisseurDTO resultDTO = FactureFournisseurFactory.factureFournisseurToFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    public FactureFournisseurDTO CancelapprouveAC(FactureFournisseurDTO dto) {
        FactureFournisseur inBase = factureFournisseurRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
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

}
