/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.factory;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Caisse;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CaisseDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class CaisseFactory {

    public static Caisse createCaisseByCode(int code) {
        Caisse domaine = new Caisse();
        domaine.setCode(code);
        return domaine;
    }

    public static Caisse caisseDTOToCaisse(CaisseDTO dto, Caisse domaine) {
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

    public static CaisseDTO caisseToCaisseDTO(Caisse domaine) {

        if (domaine != null) {
            CaisseDTO dto = new CaisseDTO();
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

    public static List<CaisseDTO> listCaisseToCaisseDTOs(List<Caisse> caisses) {
        List<CaisseDTO> list = new ArrayList<>();
        for (Caisse caisse : caisses) {
            list.add(caisseToCaisseDTO(caisse));
        }
        return list;
    }
}
