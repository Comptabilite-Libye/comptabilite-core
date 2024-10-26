/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.factory;

import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsReglementFactureFrs;
import com.DevPointSystem.Comptabilite.Depense.dto.DetailsReglementFactureFrsDTO;
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
public class DetailsReglementFactureFournisseurFactory {

    public static DetailsReglementFactureFrsDTO DetailsReglementFactureFournisseurToDetailsReglementFactureFournisseurDTOCollectionForUpdate(DetailsReglementFactureFrs domaine) {
        if (domaine != null) {
            DetailsReglementFactureFrsDTO dto = new DetailsReglementFactureFrsDTO(); 
            dto.setCodeRegelementFactureFournisseur(domaine.getDetailsReglementFactureFrsPK().getCodeReglementFactureFournisseur());
            dto.setCodeRegelementFactureFournisseur(domaine.getReglementFactureFrs().getCode());
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
            dto.setCodeSaisieReglementFactureFournisseur(domaine.getReglementFactureFrs().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }

    public static Collection<DetailsReglementFactureFrsDTO> detailsFactureFournisseurTodetailsFactureFournisseurDTOCollections(Collection<DetailsReglementFactureFrs> details) {
        Collection<DetailsReglementFactureFrsDTO> detailsFactureFournisseurDTOs = new ArrayList<>();
        for (DetailsReglementFactureFrs detailsReglementFactureFrs : details) {
            detailsFactureFournisseurDTOs.add(DetailsReglementFactureFournisseurToDetailsReglementFactureFournisseurDTOCollectionForUpdate(detailsReglementFactureFrs));
        }
        return detailsFactureFournisseurDTOs;
    }

    public static DetailsReglementFactureFrsDTO DetailsReglementFactureFournisseurToDetailsReglementFactureFournisseurDTOCollection(DetailsReglementFactureFrs domaine) {
        if (domaine != null) {
            DetailsReglementFactureFrsDTO dto = new DetailsReglementFactureFrsDTO();

            dto.setCodeRegelementFactureFournisseur(domaine.getDetailsReglementFactureFrsPK().getCodeReglementFactureFournisseur());
            dto.setCodeRegelementFactureFournisseur(domaine.getReglementFactureFrs().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());
            dto.setCodeTypeDepense(domaine.getCodeTypeDepense());
            dto.setTypeDepenseDTO(TypeDepenseFactory.typeDepenseToTypeDepenseDTO(domaine.getTypeDepense()));

            dto.setCodeCategorieDepense(domaine.getCodeCategorieDepense());
            dto.setCategorieDepenseDTO(CategorieDepenseFactory.categorieDepenseToCategorieDepenseDTO(domaine.getCategorieDepense()));
 
            return dto;
        } else {
            return null;
        }

    }

}
