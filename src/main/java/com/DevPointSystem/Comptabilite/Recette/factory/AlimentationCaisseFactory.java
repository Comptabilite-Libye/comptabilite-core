/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.factory;

import com.DevPointSystem.Comptabilite.Parametrage.factory.CaisseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.DeviseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.ModeReglementFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.TypeRecetteFactory;
import com.DevPointSystem.Comptabilite.Recette.domaine.AlimentationCaisse;
import com.DevPointSystem.Comptabilite.Recette.dto.AlimentationCaisseDTO;
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
public class AlimentationCaisseFactory {

    public static AlimentationCaisse createAlimentationCaisseByCode(int code) {
        AlimentationCaisse domaine = new AlimentationCaisse();
        domaine.setCode(code);
        return domaine;
    }

    public static AlimentationCaisse alimentationCaisseDTOToAlimentationCaisse(AlimentationCaisseDTO dto, AlimentationCaisse domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setObservation(dto.getObservation());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setMontant(dto.getMontant());
            domaine.setDateCreate(dto.getDateCreate());

            domaine.setUserCreate(dto.getUserCreate());

            Preconditions.checkBusinessLogique(dto.getCodeCaisse() != null, "error.CaisseRequired");
            domaine.setCodeCaisse(dto.getCodeCaisse());
            if (domaine.getCodeCaisse() != null) {
                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));

            }

            domaine.setCodeTypeRecette(dto.getCodeTypeRecette());
            if (domaine.getCodeTypeRecette() != null) {
                domaine.setTypeRecette(TypeRecetteFactory.createTypeRecetteByCode(dto.getCodeTypeRecette()));

            }

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

    public static AlimentationCaisseDTO alimentationCaisseToAlimentationCaisseDTO(AlimentationCaisse domaine) {

        if (domaine != null) {
            AlimentationCaisseDTO dto = new AlimentationCaisseDTO();
            dto.setCode(domaine.getCode());
            dto.setObservation(domaine.getObservation());
            dto.setMontant(domaine.getMontant());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());

            dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
            dto.setCodeModeReglement(domaine.getCodeModeReglement());

            dto.setTypeRecetteDTO(TypeRecetteFactory.typeRecetteToTypeRecetteDTO(domaine.getTypeRecette()));
            dto.setCodeTypeRecette(domaine.getCodeTypeRecette());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            return dto;
        } else {
            return null;
        }
    }

    public static List<AlimentationCaisseDTO> listAlimentationCaisseToAlimentationCaisseDTOs(List<AlimentationCaisse> alimentationCaisses) {
        List<AlimentationCaisseDTO> list = new ArrayList<>();
        for (AlimentationCaisse alimentationCaisse : alimentationCaisses) {
            list.add(alimentationCaisseToAlimentationCaisseDTO(alimentationCaisse));
        }
        return list;
    }

    public static Collection<AlimentationCaisseDTO> CollectionalimentationCaissesToalimentationCaissesDTOsCollection(Collection<AlimentationCaisse> alimentationCaisses) {
        List<AlimentationCaisseDTO> dTOs = new ArrayList<>();
        alimentationCaisses.forEach(x -> {
            dTOs.add(alimentationCaisseToAlimentationCaisseDTO(x));
        });
        return dTOs;

    }
}
