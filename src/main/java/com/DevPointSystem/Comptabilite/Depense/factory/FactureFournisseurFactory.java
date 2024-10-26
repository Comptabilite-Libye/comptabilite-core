/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.factory;

import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsFactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsFactureFournisseurPK;
import com.DevPointSystem.Comptabilite.Depense.domaine.FactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.dto.DetailsFactureFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.dto.FactureFournisseurDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CostProfitCentreFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.DeviseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.EtatApprouverFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.FournisseurFactory;
import com.DevPointSystem.Comptabilite.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class FactureFournisseurFactory {

    public static FactureFournisseur createFactureFournisseurByCode(int code) {
        FactureFournisseur domaine = new FactureFournisseur();
        domaine.setCode(code);
        return domaine;
    }

    public static FactureFournisseur factureFournisseurDTOToFactureFournisseur(FactureFournisseur domaine, FactureFournisseurDTO dto) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setObservation(dto.getObservation());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setMontant(dto.getMontant());
            domaine.setUserCreate(dto.getUserCreate());

            domaine.setDateFactureFournisseur(dto.getDateFactureFournisseur());

            domaine.setMontantFactureFrounisseur(dto.getMontantFactureFrounisseur());

            domaine.setNumFactureFournisseur(dto.getNumFactureFournisseur());
            domaine.setPaid(dto.getPaid());

            Preconditions.checkBusinessLogique(dto.getCodeDevise() != null, "error.DeviseRequired");
            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));

            }
            Preconditions.checkBusinessLogique(dto.getCodeFournisseur() != null, "error.FournisseurRequired");
            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));

            }

            Preconditions.checkBusinessLogique(dto.getCodeCostProfitCentre() != null, "error.CostCentreRequired");
            domaine.setCodeCostProfitCentre(dto.getCodeCostProfitCentre());
            if (domaine.getCodeCostProfitCentre() != null) {
                domaine.setCostProfitCentre(CostProfitCentreFactory.createCostCentreByCode(dto.getCodeCostProfitCentre()));

            }

            domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
            if (domaine.getCodeEtatApprouver() != null) {
                domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
            }
            
            
            
            

            if (dto.getDetailsFactureFournisseursDTOs() == null || dto.getDetailsFactureFournisseursDTOs().isEmpty()) {
                throw new IllegalArgumentException("error.DetailsFactureRequired");
            }

            Collection<DetailsFactureFournisseur> detailsCollections = new ArrayList<>();
            dto.getDetailsFactureFournisseursDTOs().forEach(x -> {
                DetailsFactureFournisseur detailsFactureFournisseur = new DetailsFactureFournisseur();

                DetailsFactureFournisseurPK detailsPK = new DetailsFactureFournisseurPK();
                Preconditions.checkBusinessLogique(x.getCodeTypeDepense() != null, "error.TypeDepenseRequired");
                detailsPK.setCodeTypeDepense(x.getCodeTypeDepense());
                detailsPK.setCodeCategorieDepense(x.getCodeCategorieDepense());
                detailsFactureFournisseur.setDetailsFactureFournisseurPK(detailsPK);

                Preconditions.checkBusinessLogique(x.getMontant() != null, "error.MontantRequired");
                detailsFactureFournisseur.setMontant(x.getMontant());

                detailsFactureFournisseur.setDateCreate(domaine.getDateCreate());
                detailsFactureFournisseur.setUsercreate(domaine.getUserCreate());
                detailsFactureFournisseur.setFactureFournisseur(domaine);
                detailsCollections.add(detailsFactureFournisseur);

            });

            if (domaine.getDetailsFactureFournisseurs() != null) {
                domaine.getDetailsFactureFournisseurs().clear();
                domaine.getDetailsFactureFournisseurs().addAll(detailsCollections);
            } else {
                domaine.setDetailsFactureFournisseurs(detailsCollections);
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static FactureFournisseurDTO factureFournisseurToFactureFournisseurDTOUpdate(FactureFournisseur domaine) {

        if (domaine != null) {
            FactureFournisseurDTO dto = new FactureFournisseurDTO();
            dto.setCode(domaine.getCode());
            dto.setObservation(domaine.getObservation());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setMontant(domaine.getMontant());
            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());
            dto.setPaid(domaine.getPaid());
            dto.setDateFactureFournisseur(domaine.getDateFactureFournisseur());
            dto.setNumFactureFournisseur(domaine.getNumFactureFournisseur());
            dto.setMontantFactureFrounisseur(domaine.getMontantFactureFrounisseur());

            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            dto.setCostProfitCentreDTO(CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTO(domaine.getCostProfitCentre()));
            dto.setCodeCostProfitCentre(domaine.getCodeCostProfitCentre());

            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver());

            if (domaine.getDetailsFactureFournisseurs() != null) {
                Collection<DetailsFactureFournisseurDTO> detailsFactureFournisseurDTOsCollection = new ArrayList<>();
                domaine.getDetailsFactureFournisseurs().forEach(x -> {
                    DetailsFactureFournisseurDTO detailsDTO = new DetailsFactureFournisseurDTO();
                    detailsDTO = DetailsFactureFournisseurFactory.DetailsFactureFournisseurToDetailsFactureFournisseurDTOCollectionForUpdate(x);
                    detailsFactureFournisseurDTOsCollection.add(detailsDTO);
                });
                if (dto.getDetailsFactureFournisseursDTOs() != null) {
                    dto.getDetailsFactureFournisseursDTOs().clear();
                    dto.getDetailsFactureFournisseursDTOs().addAll(detailsFactureFournisseurDTOsCollection);
                } else {
                    dto.setDetailsFactureFournisseursDTOs(detailsFactureFournisseurDTOsCollection);
                }
            }

            return dto;
        } else {
            return null;
        }
    }

    public static FactureFournisseurDTO factureFournisseurToFactureFournisseurDTO(FactureFournisseur domaine) {

        if (domaine != null) {
            FactureFournisseurDTO dto = new FactureFournisseurDTO();
            dto.setCode(domaine.getCode());
            dto.setObservation(domaine.getObservation());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setMontant(domaine.getMontant());
            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());
            dto.setPaid(domaine.getPaid());
            dto.setDateFactureFournisseur(domaine.getDateFactureFournisseur());
            dto.setNumFactureFournisseur(domaine.getNumFactureFournisseur());
            dto.setMontantFactureFrounisseur(domaine.getMontantFactureFrounisseur()); 
            dto.setCostProfitCentreDTO(CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTOLazy(domaine.getCostProfitCentre()));
            dto.setCodeCostProfitCentre(domaine.getCodeCostProfitCentre());
          
            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver());

            if (domaine.getDetailsFactureFournisseurs() != null) {
                Collection<DetailsFactureFournisseurDTO> detailsFactureFournisseurDTOsCollection = new ArrayList<>();
                domaine.getDetailsFactureFournisseurs().forEach(x -> {
                    DetailsFactureFournisseurDTO detailsDTO = new DetailsFactureFournisseurDTO();
                    detailsDTO = DetailsFactureFournisseurFactory.DetailsFactureFournisseurToDetailsFactureFournisseurDTOCollection(x);
                    detailsFactureFournisseurDTOsCollection.add(detailsDTO);
                });
                if (dto.getDetailsFactureFournisseursDTOs() != null) {
                    dto.getDetailsFactureFournisseursDTOs().clear();
                    dto.getDetailsFactureFournisseursDTOs().addAll(detailsFactureFournisseurDTOsCollection);
                } else {
                    dto.setDetailsFactureFournisseursDTOs(detailsFactureFournisseurDTOsCollection);
                }
            }

            return dto;
        } else {
            return null;
        }
    }

    public static List<FactureFournisseurDTO> listFactureFournisseurToFactureFournisseurDTOs(List<FactureFournisseur> factureFournisseurs) {
        List<FactureFournisseurDTO> list = new ArrayList<>();
        for (FactureFournisseur factureFournisseur : factureFournisseurs) {
            list.add(factureFournisseurToFactureFournisseurDTOUpdate(factureFournisseur));
        }
        return list;
    }

    public static Collection<FactureFournisseurDTO> CollectionfactureFournisseursTofactureFournisseursDTOsCollection(Collection<FactureFournisseur> factureFournisseurs) {
        List<FactureFournisseurDTO> dtos = new ArrayList<>();
        factureFournisseurs.forEach(x -> {
            dtos.add(factureFournisseurToFactureFournisseurDTO(x));
        });
        return dtos;

    }

    public static FactureFournisseur ApprouveFactureFournisseurDTOToFactureFournisseur(FactureFournisseur domaine, FactureFournisseurDTO dto) {
        domaine.setCode(dto.getCode());
        domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
        if (domaine.getCodeEtatApprouver() != null) {
            domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
        }
        domaine.setCodeUserApprouver(dto.getCodeUserApprouver());
        domaine.setDateApprouve(new Date());

        return domaine;
    }

    public static FactureFournisseur CancelFactureFournisseurDTOToFactureFournisseur(FactureFournisseur domaine, FactureFournisseurDTO dto) {
        domaine.setCode(dto.getCode());
        domaine.setCodeSaisie(dto.getCodeSaisie());

        domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
        if (domaine.getCodeEtatApprouver() != null) {
            domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
        }
        domaine.setCodeUserApprouver(null);
        domaine.setDateApprouve(null);

        return domaine;
    }
}
