/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.factory;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Beneficiaire;
import com.DevPointSystem.Comptabilite.Parametrage.dto.BeneficiaireDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class BeneficiaireFactory {
     public static Beneficiaire createBeneficiaireByCode(int code) {
        Beneficiaire domaine = new Beneficiaire();
        domaine.setCode(code);
        return domaine;
    }

    public static Beneficiaire beneficiaireDTOToBeneficiaire(BeneficiaireDTO dto, Beneficiaire domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());        
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setActif(dto.isActif());
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate());

            return domaine;
        } else {
            return null;
        }
    }

    public static BeneficiaireDTO beneficiaireToBeneficiaireDTO(Beneficiaire domaine) {

        if (domaine != null) {
            BeneficiaireDTO dto = new BeneficiaireDTO();
            dto.setCode(domaine.getCode());

            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<BeneficiaireDTO> listBeneficiaireToBeneficiaireDTOs(List<Beneficiaire> beneficiaires) {
        List<BeneficiaireDTO> list = new ArrayList<>();
        for (Beneficiaire beneficiaire : beneficiaires) {
            list.add(beneficiaireToBeneficiaireDTO(beneficiaire));
        }
        return list;
    }
}
