/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.factory;

import com.DevPointSystem.Comptabilite.Parametrage.factory.CaisseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.DeviseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.ModeReglementFactory;
import com.DevPointSystem.Comptabilite.Recette.domaine.MouvementCaisse;
import com.DevPointSystem.Comptabilite.Recette.dto.MouvementCaisseDTO;
import com.DevPointSystem.Comptabilite.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class MouvementCaisseFactory {

    public static MouvementCaisse createMouvementCaisseByCode(int code) {
        MouvementCaisse domaine = new MouvementCaisse();
        domaine.setCode(code);
        return domaine;
    }

    public static MouvementCaisse mouvementCaisseDTOToMouvementCaisse(MouvementCaisseDTO dto, MouvementCaisse domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDebit(dto.getDebit());
            domaine.setCredit(dto.getCredit());

            domaine.setDateCreate(dto.getDateCreate()); 
            domaine.setUserCreate(dto.getUserCreate());  
            domaine.setMntDevise(dto.getMntDevise());   
            domaine.setCodeTier(dto.getCodeTier());



            Preconditions.checkBusinessLogique(dto.getCodeCaisse() != null, "error.CaisseRequired");
            domaine.setCodeCaisse(dto.getCodeCaisse());
            if (domaine.getCodeCaisse() != null) {
                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));

            }

            domaine.setCodeCaisseTr(dto.getCodeCaisseTr());
          
            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));

            }
            
            domaine.setCodeModeReglement(dto.getCodeModeReglement());
            if (domaine.getCodeModeReglement() != null) {
                domaine.setModeReglement(ModeReglementFactory.createModeReglementByCode(dto.getCodeModeReglement()));

            }

            return domaine;
        } else {
            return null;
        }
    }

    public static MouvementCaisseDTO mouvementCaisseToMouvementCaisseDTO(MouvementCaisse domaine) {

        if (domaine != null) {
            MouvementCaisseDTO dto = new MouvementCaisseDTO();
            dto.setCode(domaine.getCode());
            dto.setCredit(domaine.getCredit());
            dto.setDebit(domaine.getDebit());    
            dto.setMntDevise(domaine.getMntDevise());

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());
 
            dto.setCodeCaisseTr(domaine.getCodeCaisseTr());

             dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
            dto.setCodeModeReglement(domaine.getCodeModeReglement());
            
            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            return dto;
        } else {
            return null;
        }
    }

    public static List<MouvementCaisseDTO> listMouvementCaisseToMouvementCaisseDTOs(List<MouvementCaisse> mouvementCaisses) {
        List<MouvementCaisseDTO> list = new ArrayList<>();
        for (MouvementCaisse mouvementCaisse : mouvementCaisses) {
            list.add(mouvementCaisseToMouvementCaisseDTO(mouvementCaisse));
        }
        return list;
    }

    public static Collection<MouvementCaisseDTO> CollectionmouvementCaissesTomouvementCaissesDTOsCollection(Collection<MouvementCaisse> mouvementCaisses) {
        List<MouvementCaisseDTO> dTOs = new ArrayList<>();
        mouvementCaisses.forEach(x -> {
            dTOs.add(mouvementCaisseToMouvementCaisseDTO(x));
        });
        return dTOs;

    }
}