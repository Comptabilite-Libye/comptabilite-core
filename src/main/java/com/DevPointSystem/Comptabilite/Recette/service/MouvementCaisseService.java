/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.service;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Compteur;
import com.DevPointSystem.Comptabilite.Parametrage.service.CompteurService;
import com.DevPointSystem.Comptabilite.Recette.domaine.MouvementCaisse;
import com.DevPointSystem.Comptabilite.Recette.dto.MouvementCaisseDTO;
import com.DevPointSystem.Comptabilite.Recette.dto.SoldeCaisseDTO;
import com.DevPointSystem.Comptabilite.Recette.factory.MouvementCaisseFactory;
import com.DevPointSystem.Comptabilite.Recette.repository.MouvementCaisseRepo;
import com.DevPointSystem.Comptabilite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
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
public class MouvementCaisseService {

    private final MouvementCaisseRepo mouvementCaisseRepo;

    private final CompteurService compteurService;

    public MouvementCaisseService(MouvementCaisseRepo mouvementCaisseRepo, CompteurService compteurService) {
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<MouvementCaisseDTO> findAllMouvementCaisse() {
        return MouvementCaisseFactory.listMouvementCaisseToMouvementCaisseDTOs(mouvementCaisseRepo.findAll());

    }
    
//    @Transactional(readOnly = true)
//    public List<MouvementCaisseDTO>findAllMouvementCaisseGroupeed() {
//        return  MouvementCaisseFactory.listMouvementCaisseToMouvementCaisseDTOs(mouvementCaisseRepo.calculDebitAndCredit());
//
//    }
    
      @Transactional(readOnly = true)
    public List<MouvementCaisseDTO> findAllMouvementCaisseGroupeed() {
        
//         List<MouvementCaisseDTO> list = mouvementCaisseRepo.calculDebitAndCredit();
//         List<SoldeCaisseDTO> sld = new ArrayList<>();
//         sld.iterator().next().setCodeCaisse(list.iterator().next().getCodeCaisse());
         
        return mouvementCaisseRepo.calculDebitAndCredit();
    }


    @Transactional(readOnly = true)
    public MouvementCaisseDTO findOne(Integer code) {
        MouvementCaisse domaine = mouvementCaisseRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.MouvementCaisseNotFound");
        return MouvementCaisseFactory.mouvementCaisseToMouvementCaisseDTO(domaine);
    }

   

    @Transactional(readOnly = true)
    public MouvementCaisseDTO findByCodeSaisie(String codeCaisse) {
        MouvementCaisse result = mouvementCaisseRepo.findMouvementCaisseByCodeSaisie(codeCaisse);
        return MouvementCaisseFactory.mouvementCaisseToMouvementCaisseDTO(result);
    }
//
//    @Transactional(readOnly = true)
//    public Collection<MouvementCaisseDTO> findByCodeModeReglement(Collection<Integer> codeModeReglement) {
//        Collection<MouvementCaisse> result = mouvementCaisseRepo.findMouvementCaisseByCodeModeReglement(codeModeReglement);
//        return MouvementCaisseFactory.CollectionmouvementCaissesTomouvementCaissesDTOsCollection(result);
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<MouvementCaisseDTO> findByCodeTier(Collection<String> codeTier) {
//        Collection<MouvementCaisse> result = mouvementCaisseRepo.findMouvementCaisseByCodeTier(codeTier);
//        return MouvementCaisseFactory.CollectionmouvementCaissesTomouvementCaissesDTOsCollection(result);
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<MouvementCaisseDTO> findByCodeDevise(Collection<Integer> codeDevise) {
//        Collection<MouvementCaisse> result = mouvementCaisseRepo.findMouvementCaisseByCodeDevise(codeDevise);
//        return MouvementCaisseFactory.CollectionmouvementCaissesTomouvementCaissesDTOsCollection(result);
//    }

    public MouvementCaisseDTO save(MouvementCaisseDTO dTO) {
        MouvementCaisse domaine = MouvementCaisseFactory.mouvementCaisseDTOToMouvementCaisse(dTO, new MouvementCaisse());
        domaine = mouvementCaisseRepo.save(domaine);
        return MouvementCaisseFactory.mouvementCaisseToMouvementCaisseDTO(domaine);
    }

}
