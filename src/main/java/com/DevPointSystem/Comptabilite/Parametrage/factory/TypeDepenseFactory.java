/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.factory;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.TypeDepense;
import com.DevPointSystem.Comptabilite.Parametrage.dto.TypeDepenseDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TypeDepenseFactory {
     public static TypeDepense createTypeDepenseByCode(int code) {
        TypeDepense domaine = new TypeDepense();
        domaine.setCode(code);
        return domaine;
    }

    public static TypeDepense typeDepenseDTOToTypeDepense(TypeDepenseDTO dto, TypeDepense domaine) {
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

    public static TypeDepenseDTO typeDepenseToTypeDepenseDTO(TypeDepense domaine) {

        if (domaine != null) {
            TypeDepenseDTO dto = new TypeDepenseDTO();
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

    public static List<TypeDepenseDTO> listTypeDepenseToTypeDepenseDTOs(List<TypeDepense> typeDepenses) {
        List<TypeDepenseDTO> list = new ArrayList<>();
        for (TypeDepense typeDepense : typeDepenses) {
            list.add(typeDepenseToTypeDepenseDTO(typeDepense));
        }
        return list;
    }
}
