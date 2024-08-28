/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.service;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Caisse;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CaisseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CaisseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.repository.CaisseRepo;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.base.Preconditions;
/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class CaisseService {
     private final CaisseRepo caisseRepo;

    public CaisseService(CaisseRepo caisseRepo) {
        this.caisseRepo = caisseRepo;
    }

    @Transactional(readOnly = true)
    public List<CaisseDTO> findAllCaisse() {
        return CaisseFactory.listCaisseToCaisseDTOs(caisseRepo.findAll());

    }

    @Transactional(readOnly = true)
    public CaisseDTO findOne(Integer code) {
        Caisse domaine = caisseRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.CaisseNotFound");
        return CaisseFactory.caisseToCaisseDTO(domaine);
    }

//
    public CaisseDTO save(CaisseDTO dto) {
        Caisse domaine = CaisseFactory.caisseDTOToCaisse(dto, new Caisse());
        domaine = caisseRepo.save(domaine);
        return CaisseFactory.caisseToCaisseDTO(domaine);
    }

    public Caisse update(CaisseDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.CaisseNotFound");
        Caisse domaine = caisseRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(true, "error.CaisseNotFound");
        dto.setCode(domaine.getCode());
        CaisseFactory.caisseDTOToCaisse(dto, domaine);
        return caisseRepo.save(domaine);
    }

    public void deleteCaisse(Integer code) {
        Preconditions.checkArgument(caisseRepo.existsById(code), "error.CaisseNotFound");
        caisseRepo.deleteById(code);
    }
}
