/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.factory;

import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsFactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.dto.DetailsFactureFournisseurDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CategorieDepenseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.TypeDepenseFactory;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsFactureFournisseurFactory {

    public static DetailsFactureFournisseurDTO DetailsFactureFournisseurToDetailsFactureFournisseurDTOCollectionForUpdate(DetailsFactureFournisseur domaine) {
        if (domaine != null) {
            DetailsFactureFournisseurDTO dto = new DetailsFactureFournisseurDTO(); 
            dto.setCodeFactureFournisseur(domaine.getDetailsFactureFournisseurPK().getCodeFactureFournisseur());
            dto.setCodeFactureFournisseur(domaine.getFactureFournisseur().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());
            dto.setMontant(domaine.getMontant());
            dto.setCodeTypeDepense(domaine.getCodeTypeDepense());
            dto.setTypeDepenseDTO(TypeDepenseFactory.typeDepenseToTypeDepenseDTO(domaine.getTypeDepense()));

            dto.setCodeCategorieDepense(domaine.getCodeCategorieDepense());
            dto.setCategorieDepenseDTO(CategorieDepenseFactory.categorieDepenseToCategorieDepenseDTO(domaine.getCategorieDepense()));

            dto.setDesignationArTypeDepense(domaine.getTypeDepense().getDesignationAr());
            dto.setDesignationLtTypeDepense(domaine.getTypeDepense().getDesignationLt());
            dto.setCodeSaisieTypeDepense(domaine.getTypeDepense().getCodeSaisie());
            dto.setCodeSaisieFactureFournisseur(domaine.getFactureFournisseur().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }

    public static Collection<DetailsFactureFournisseurDTO> detailsFactureFournisseurTodetailsFactureFournisseurDTOCollections(Collection<DetailsFactureFournisseur> detailsFactureFournisseurs) {
        Collection<DetailsFactureFournisseurDTO> detailsFactureFournisseurDTOs = new ArrayList<>();
        for (DetailsFactureFournisseur detailsFactureFournisseur : detailsFactureFournisseurs) {
            detailsFactureFournisseurDTOs.add(DetailsFactureFournisseurToDetailsFactureFournisseurDTOCollectionForUpdate(detailsFactureFournisseur));
        }
        return detailsFactureFournisseurDTOs;
    }

    public static DetailsFactureFournisseurDTO DetailsFactureFournisseurToDetailsFactureFournisseurDTOCollection(DetailsFactureFournisseur domaine) {
        if (domaine != null) {
            DetailsFactureFournisseurDTO dto = new DetailsFactureFournisseurDTO();

            dto.setCodeFactureFournisseur(domaine.getDetailsFactureFournisseurPK().getCodeFactureFournisseur());
            dto.setCodeFactureFournisseur(domaine.getFactureFournisseur().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());
            dto.setCodeTypeDepense(domaine.getCodeTypeDepense());
            dto.setTypeDepenseDTO(TypeDepenseFactory.typeDepenseToTypeDepenseDTO(domaine.getTypeDepense()));

            dto.setCodeCategorieDepense(domaine.getCodeCategorieDepense());
            dto.setCategorieDepenseDTO(CategorieDepenseFactory.categorieDepenseToCategorieDepenseDTO(domaine.getCategorieDepense()));

//            dto.setDesignationArTypeDepense(domaine.getTypeDepense().getDesignationAr());
//            dto.setDesignationLtTypeDepense(domaine.getTypeDepense().getDesignationLt());
//            dto.setCodeSaisieTypeDepense(domaine.getTypeDepense().getCodeSaisie());

//            dto.setCodeSaisieFactureFournisseur(domaine.getFactureFournisseur().getCodeSaisie());
            return dto;
        } else {
            return null;
        }

    }

}
