/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.service;

import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsReglementFactureFrs;
import com.DevPointSystem.Comptabilite.Depense.domaine.ReglementFactureFrs;
import com.DevPointSystem.Comptabilite.Depense.dto.DetailsReglementFactureFrsDTO;
import com.DevPointSystem.Comptabilite.Depense.dto.ReglementFactureFrsDTO;
import com.DevPointSystem.Comptabilite.Depense.factory.DetailsReglementFactureFournisseurFactory;
import com.DevPointSystem.Comptabilite.Depense.factory.ReglementFactureFournisseurFactory;
import com.DevPointSystem.Comptabilite.Depense.repository.DetailsReglementFactureFrsRepo;
import com.DevPointSystem.Comptabilite.Depense.repository.ReglementFactureFrsRepo;
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
public class ReglementFactureFournisseurService {

    private final ReglementFactureFrsRepo reglementFactureFrsRepo;

    private final CompteurService compteurService;

    private final MouvementCaisseRepo mouvementCaisseRepo;

    private final DetailsReglementFactureFrsRepo detailsReglementFactureFrsRepo; 

    private final SoldeCaisseRepo soldeCaisseRepo; 

    public ReglementFactureFournisseurService(ReglementFactureFrsRepo reglementFactureFrsRepo, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo, DetailsReglementFactureFrsRepo detailsReglementFactureFrsRepo, SoldeCaisseRepo soldeCaisseRepo) {
        this.reglementFactureFrsRepo = reglementFactureFrsRepo;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.detailsReglementFactureFrsRepo = detailsReglementFactureFrsRepo;
        this.soldeCaisseRepo = soldeCaisseRepo;
    }

   

     

    @Transactional(readOnly = true)
    public List<ReglementFactureFrsDTO> findAllReglementFactureFournisseur() {
     
        return ReglementFactureFournisseurFactory.listReglementFactureFournisseurToReglementFactureFournisseurDTOs(reglementFactureFrsRepo.findAllByOrderByCodeSaisieDesc());

    }

    @Transactional(readOnly = true)
    public ReglementFactureFrsDTO findOne(Integer code) {
        ReglementFactureFrs domaine = reglementFactureFrsRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.FactureFournisseurNotFound");
        return ReglementFactureFournisseurFactory.reglementfactureFournisseurToReglementFactureFournisseurDTOUpdate(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<ReglementFactureFrsDTO> findByCodeFournisseur(Collection<Integer> codeFournisseur) {
        Collection<ReglementFactureFrs> result = reglementFactureFrsRepo.findByCodeFournisseurIn(Helper.removeNullValueFromCollection(codeFournisseur));
        return ReglementFactureFournisseurFactory.CollectionfactureFournisseursTofactureFournisseursDTOsCollection(result);
    }
    
    
    
    @Transactional(readOnly = true)
    public List<ReglementFactureFrsDTO> findByCodeFournisseurAndCodeDevise(Integer codeFournisseur,Integer codeDevise) {
        List<ReglementFactureFrs> result = reglementFactureFrsRepo.findByCodeFournisseurAndCodeDevise(codeFournisseur, codeDevise);
        return ReglementFactureFournisseurFactory.listReglementFactureFournisseurToReglementFactureFournisseurDTOs(result);
    }
    
    
    

    @Transactional(readOnly = true)
    public Collection<ReglementFactureFrsDTO> findByCodeDevise(Collection<Integer> codeDevise) {
        Collection<ReglementFactureFrs> result = reglementFactureFrsRepo.findByCodeDeviseIn(Helper.removeNullValueFromCollection(codeDevise));
        return ReglementFactureFournisseurFactory.CollectionfactureFournisseursTofactureFournisseursDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public List<ReglementFactureFrsDTO> findByEtatApprouver(Integer CodeEtatApprouver) {
        return ReglementFactureFournisseurFactory.listReglementFactureFournisseurToReglementFactureFournisseurDTOs(reglementFactureFrsRepo.findReglementFactureFrsByCodeEtatApprouver(CodeEtatApprouver));
    }

    public ReglementFactureFrsDTO save(ReglementFactureFrsDTO dto) {

        ReglementFactureFrs domaine = ReglementFactureFournisseurFactory.reglementfactureFournisseurDTOToReglementFactureFournisseur(new ReglementFactureFrs(), dto); 
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieFF");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = reglementFactureFrsRepo.save(domaine);
        return ReglementFactureFournisseurFactory.reglementfactureFournisseurToReglementFactureFournisseurDTO(domaine);
    } 
    public ReglementFactureFrsDTO updateNewWithFlush(ReglementFactureFrsDTO dto) {
        ReglementFactureFrs inBase = reglementFactureFrsRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        detailsReglementFactureFrsRepo.deleteByCodeReglementFactureFournisseur(dto.getCode()); 
        System.out.println("flush deleted OK Code " + dto.getCode());

        inBase = ReglementFactureFournisseurFactory.reglementfactureFournisseurDTOToReglementFactureFournisseur(inBase, dto);
        inBase = reglementFactureFrsRepo.save(inBase);
        ReglementFactureFrsDTO resultDTO = ReglementFactureFournisseurFactory.reglementfactureFournisseurToReglementFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    public void deleteReglementFactureFournisseur(Integer code) {
        Preconditions.checkArgument(reglementFactureFrsRepo.existsById(code), "error.FactureFournisseurNotFound");
        reglementFactureFrsRepo.deleteById(code);
    }

    public ReglementFactureFrsDTO approuveRegFactFrs(ReglementFactureFrsDTO dto) {
        ReglementFactureFrs inBase = reglementFactureFrsRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        inBase = ReglementFactureFournisseurFactory.ApprouveReglementFactureFournisseurDTOToReglementFactureFournisseur(inBase, dto);

         
        inBase = reglementFactureFrsRepo.save(inBase);
 
        ReglementFactureFrsDTO resultDTO = ReglementFactureFournisseurFactory.reglementfactureFournisseurToReglementFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    public ReglementFactureFrsDTO CancelapprouveRegFactFrs(ReglementFactureFrsDTO dto) {
        ReglementFactureFrs inBase = reglementFactureFrsRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        inBase = ReglementFactureFournisseurFactory.CancelReglementFactureFournisseurDTOToReglementFactureFournisseur(inBase, dto);

      
        mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());

        inBase = reglementFactureFrsRepo.save(inBase);
        ReglementFactureFrsDTO resultDTO = ReglementFactureFournisseurFactory.reglementfactureFournisseurToReglementFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    @Transactional(readOnly = true)
    public Collection<DetailsReglementFactureFrsDTO> findOneWithDetails(Integer code) {
        Collection<DetailsReglementFactureFrs> domaine = detailsReglementFactureFrsRepo.findByDetailsReglementFactureFrsPK_codeReglementFactureFournisseur(code);
        return DetailsReglementFactureFournisseurFactory.detailsFactureFournisseurTodetailsFactureFournisseurDTOCollections(domaine);
    }

}
