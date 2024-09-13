/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.factory;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.TypeCaisse;
import com.DevPointSystem.Comptabilite.Parametrage.dto.TypeCaisseDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TypeCaisseFactory {
     public static TypeCaisse createTypeCaisseByCode(int code) {
        TypeCaisse domaine = new TypeCaisse();
        domaine.setCode(code);
        return domaine;
    }

    public static TypeCaisse typeCaisseDTOToTypeCaisse(TypeCaisseDTO dto, TypeCaisse domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setDesignationAr(dto.getDesignationAr()); 
            domaine.setDesignationLt(dto.getDesignationLt());

         

            return domaine;
        } else {
            return null;
        }
    }

    public static TypeCaisseDTO typeCaisseToTypeCaisseDTO(TypeCaisse domaine) {

        if (domaine != null) {
            TypeCaisseDTO dto = new TypeCaisseDTO();
            dto.setCode(domaine.getCode());   
            dto.setDesignationAr(domaine.getDesignationAr());  
            dto.setDesignationLt(domaine.getDesignationLt());  

            return dto;
        } else {
            return null;
        }
    }

    public static List<TypeCaisseDTO> listTypeCaisseToTypeCaisseDTOs(List<TypeCaisse> typeCaisses) {
        List<TypeCaisseDTO> list = new ArrayList<>();
        for (TypeCaisse typeCaisse : typeCaisses) {
            list.add(typeCaisseToTypeCaisseDTO(typeCaisse));
        }
        return list;
    }
}
