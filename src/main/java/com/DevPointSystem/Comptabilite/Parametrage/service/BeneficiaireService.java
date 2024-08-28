/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.service;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Beneficiaire;
import com.DevPointSystem.Comptabilite.Parametrage.dto.BeneficiaireDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.BeneficiaireFactory;
import com.DevPointSystem.Comptabilite.Parametrage.repository.BeneficiaireRepo;
import com.google.common.base.Preconditions;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */

@Service
@Transactional
public class BeneficiaireService {
    private final BeneficiaireRepo beneficiaireRepo;

    public BeneficiaireService(BeneficiaireRepo beneficiaireRepo) {
        this.beneficiaireRepo = beneficiaireRepo;
    }

    @Transactional(readOnly = true)
    public List<BeneficiaireDTO> findAllBeneficiaire() {
        return BeneficiaireFactory.listBeneficiaireToBeneficiaireDTOs(beneficiaireRepo.findAll());

    }

    @Transactional(readOnly = true)
    public BeneficiaireDTO findOne(Integer code) {
        Beneficiaire domaine = beneficiaireRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.BeneficiaireNotFound");
        return BeneficiaireFactory.beneficiaireToBeneficiaireDTO(domaine);
    }

//
    public BeneficiaireDTO save(BeneficiaireDTO dto) {
        Beneficiaire domaine = BeneficiaireFactory.beneficiaireDTOToBeneficiaire(dto, new Beneficiaire());
        domaine = beneficiaireRepo.save(domaine);
        return BeneficiaireFactory.beneficiaireToBeneficiaireDTO(domaine);
    }

    public Beneficiaire update(BeneficiaireDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.BeneficiaireNotFound");
        Beneficiaire domaine = beneficiaireRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(true, "error.BeneficiaireNotFound");
        dto.setCode(domaine.getCode());
        BeneficiaireFactory.beneficiaireDTOToBeneficiaire(dto, domaine);
        return beneficiaireRepo.save(domaine);
    }

    public void deleteBeneficiaire(Integer code) {
        Preconditions.checkArgument(beneficiaireRepo.existsById(code), "error.BeneficiaireNotFound");
        beneficiaireRepo.deleteById(code);
    }
}
