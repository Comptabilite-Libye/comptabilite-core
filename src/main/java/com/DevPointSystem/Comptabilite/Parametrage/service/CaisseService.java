/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.service;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Caisse;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CaisseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CaisseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.DeviseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.repository.CaisseRepo;
import com.DevPointSystem.Comptabilite.Recette.domaine.SoldeCaisse;
import com.DevPointSystem.Comptabilite.Recette.repository.MouvementCaisseRepo;
import com.DevPointSystem.Comptabilite.Recette.repository.SoldeCaisseRepo;
import com.DevPointSystem.Comptabilite.Recette.service.SoldeCaisseService;
import com.DevPointSystem.Comptabilite.web.Util.Helper;
import com.google.common.base.Preconditions;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
@Transactional
@Service
public class CaisseService {

    private final CaisseRepo caisseRepo;
    private final SoldeCaisseRepo soldeCaisseRepo;
    private final MouvementCaisseRepo mouvementCaisseRepo;
    private final SoldeCaisseService soldeCaisseService;
    private final static String caisseError = "error.CaisseNotFound";

    public CaisseService(CaisseRepo caisseRepo, SoldeCaisseRepo soldeCaisseRepo, MouvementCaisseRepo mouvementCaisseRepo, SoldeCaisseService soldeCaisseService) {
        this.caisseRepo = caisseRepo;
        this.soldeCaisseRepo = soldeCaisseRepo;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.soldeCaisseService = soldeCaisseService;
    }

    @Transactional(readOnly = true)
    public List<CaisseDTO> findAllCaisse() {
        return CaisseFactory.listCaisseToCaisseDTOs(caisseRepo.findAll());

    }

    @Transactional(readOnly = true)
    public CaisseDTO findOne(Integer code) {
        Caisse domaine = caisseRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, caisseError);
        return CaisseFactory.caisseToCaisseDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<Caisse> findByCodeNotIn(List<Integer> code) {
        return caisseRepo.findByCodeNotIn(Helper.removeNullValueFromCollection(code));
    }

    @Transactional(readOnly = true)
    public List<Caisse> findByCodeTypeCaisse(Integer codeTypeCaisse) {
        return caisseRepo.findByCodeTypeCaisse(codeTypeCaisse);
    }

//
    public CaisseDTO save(CaisseDTO dto) {
        Caisse domaine = CaisseFactory.caisseDTOToCaisse(dto, new Caisse());

        domaine = caisseRepo.save(domaine);

        SoldeCaisse sc = new SoldeCaisse();
        sc.setCodeCaisse(domaine.getCode());
        if (sc.getCodeCaisse() != null) {
            sc.setCaisse(CaisseFactory.createCaisseByCode(domaine.getCode()));
        }
        sc.setCodeDevise(domaine.getCodeDevise());
        if (sc.getCodeDevise() != null) {
            sc.setDevise(DeviseFactory.createDeviseByCode(domaine.getCodeDevise()));
        }
        sc.setCredit(BigDecimal.ZERO);
        sc.setDebit(BigDecimal.ZERO);
        sc.setDateUpdated(dto.getDateCreate());
        sc.setUserUpdated(dto.getUserCreate());
        sc = soldeCaisseRepo.save(sc);

        return CaisseFactory.caisseToCaisseDTO(domaine);
    }

    public Caisse update(CaisseDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.CaisseNotFound");
        Caisse domaine = caisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, caisseError);
        dto.setCode(domaine.getCode());
        CaisseFactory.caisseDTOToCaisse(dto, domaine);
        return caisseRepo.save(domaine);
    }

    public void deleteCaisse(Integer code) {
        Preconditions.checkArgument(caisseRepo.existsById(code), "error.CaisseNotFound");
        Preconditions.checkArgument(!mouvementCaisseRepo.existsByCodeCaisse(code), "error.CaisseMouvementer");
 
        SoldeCaisse soldeCaisse = soldeCaisseRepo.findByCodeCaisse(code);
        soldeCaisseService.deleteSoldeCaisse(soldeCaisse.getCode());
        caisseRepo.deleteById(code);
    }
}
