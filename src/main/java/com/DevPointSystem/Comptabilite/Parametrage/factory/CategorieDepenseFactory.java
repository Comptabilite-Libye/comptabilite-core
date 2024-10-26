/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.factory;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.CategorieDepense;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CategorieDepenseDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class CategorieDepenseFactory {
    
    public static CategorieDepense createCategorieDepenseByCode(int code) {
        CategorieDepense domaine = new CategorieDepense();
        domaine.setCode(code);
        return domaine;
    }

    public static CategorieDepense categorieDepenseDTOToCategorieDepense(CategorieDepenseDTO dto, CategorieDepense domaine) {
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

    public static CategorieDepenseDTO categorieDepenseToCategorieDepenseDTO(CategorieDepense domaine) {

        if (domaine != null) {
            CategorieDepenseDTO dto = new CategorieDepenseDTO();
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

    public static List<CategorieDepenseDTO> listCategorieDepenseToCategorieDepenseDTOs(List<CategorieDepense> categorieDepenses) {
        List<CategorieDepenseDTO> list = new ArrayList<>();
        for (CategorieDepense categorieDepense : categorieDepenses) {
            list.add(categorieDepenseToCategorieDepenseDTO(categorieDepense));
        }
        return list;
    }
}
