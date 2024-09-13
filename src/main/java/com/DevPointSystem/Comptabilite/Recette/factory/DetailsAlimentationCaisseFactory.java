/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.factory;

import com.DevPointSystem.Comptabilite.Parametrage.factory.TypeRecetteFactory;
import com.DevPointSystem.Comptabilite.Recette.domaine.DetailsAlimentationCaisse;
import com.DevPointSystem.Comptabilite.Recette.dto.DetailsAlimentationCaisseDTO;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsAlimentationCaisseFactory {
    
    public static DetailsAlimentationCaisseDTO DetailsAlimentationCaisseToDetailsAlimentationCaisseDTOCollectionForUpdate(DetailsAlimentationCaisse domaine) { 
        if (domaine != null) {
            DetailsAlimentationCaisseDTO dto = new DetailsAlimentationCaisseDTO();
          
             dto.setCodeAlimentationCaisse(domaine.getDetailsAlimentationCaissePK().getCodeAlimentationCaisse());
            dto.setCodeAlimentationCaisse(domaine.getAlimentationCaisse().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());      
            dto.setMontant(domaine.getMontant());   
            dto.setMontantDevise(domaine.getMontantDevise()); 
            dto.setCodeTypeRecette(domaine.getCodeTypeRecette());  
            dto.setTypeRecetteDTO(TypeRecetteFactory.typeRecetteToTypeRecetteDTO(domaine.getTypeRecette())); 

            dto.setDesignationArTypeRecette(domaine.getTypeRecette().getDesignationAr());   
            dto.setDesignationLtTypeRecette(domaine.getTypeRecette().getDesignationLt());   
            dto.setCodeSaisieTypeRecette(domaine.getTypeRecette().getCodeSaisie());    
            dto.setCodeSaisieAlimentationCaisse(domaine.getAlimentationCaisse().getCodeSaisie());
 

            return dto;
        } else {
            return null;
        }

    }
    
     

    public static Collection<DetailsAlimentationCaisseDTO> detailsAlimentationCaisseTodetailsAlimentationCaisseDTOCollections(Collection<DetailsAlimentationCaisse> detailsAlimentationCaisses) {
        Collection<DetailsAlimentationCaisseDTO> detailsAlimentationCaisseDTOs = new ArrayList<>();
        for (DetailsAlimentationCaisse detailsAlimentationCaisse : detailsAlimentationCaisses) {
            detailsAlimentationCaisseDTOs.add(DetailsAlimentationCaisseToDetailsAlimentationCaisseDTOCollectionForUpdate(detailsAlimentationCaisse));
        }
        return detailsAlimentationCaisseDTOs;
    }
    
     public static DetailsAlimentationCaisseDTO DetailsAlimentationCaisseToDetailsAlimentationCaisseDTOCollection(DetailsAlimentationCaisse domaine) { 
        if (domaine != null) {
            DetailsAlimentationCaisseDTO dto = new DetailsAlimentationCaisseDTO();
          
             dto.setCodeAlimentationCaisse(domaine.getDetailsAlimentationCaissePK().getCodeAlimentationCaisse());
            dto.setCodeAlimentationCaisse(domaine.getAlimentationCaisse().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());      
            dto.setMontant(domaine.getMontant());   
            dto.setMontantDevise(domaine.getMontantDevise()); 
            dto.setCodeTypeRecette(domaine.getCodeTypeRecette());  
            dto.setTypeRecetteDTO(TypeRecetteFactory.typeRecetteToTypeRecetteDTO(domaine.getTypeRecette())); 
    
            dto.setCodeSaisieAlimentationCaisse(domaine.getAlimentationCaisse().getCodeSaisie());
 

            return dto;
        } else {
            return null;
        }

    }
    
}
