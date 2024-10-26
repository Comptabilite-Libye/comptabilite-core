/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.factory;

import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsFactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsFactureFournisseurPK;
import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsReglementFactureFrs;
import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsReglementFactureFrsPK;
import com.DevPointSystem.Comptabilite.Depense.domaine.FactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.domaine.ReglementFactureFrs;
import com.DevPointSystem.Comptabilite.Depense.dto.DetailsFactureFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.dto.DetailsReglementFactureFrsDTO;
import com.DevPointSystem.Comptabilite.Depense.dto.ReglementFactureFrsDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.BanqueFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CaisseFactory;
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
public class ReglementFactureFournisseurFactory {

    public static FactureFournisseur createReglementFactureFournisseurByCode(int code) {
        FactureFournisseur domaine = new FactureFournisseur();
        domaine.setCode(code);
        return domaine;
    }

    public static ReglementFactureFrs reglementfactureFournisseurDTOToReglementFactureFournisseur(ReglementFactureFrs domaine, ReglementFactureFrsDTO dto) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setObservation(dto.getObservation());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setMontant(dto.getMontant());
            domaine.setUserCreate(dto.getUserCreate());

            domaine.setNumPiece(dto.getNumPiece());

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

            domaine.setCodeBanque(dto.getCodeBanque());
            if (domaine.getCodeBanque() != null) {
                domaine.setBanque(BanqueFactory.createBanqueByCode(dto.getCodeBanque()));
            }

            domaine.setCodeCaisse(dto.getCodeCaisse());
            if (domaine.getCodeCaisse() != null) {
                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));
            }

            if (dto.getDetailsReglementFactureFrsDTOs() == null || dto.getDetailsReglementFactureFrsDTOs().isEmpty()) {
                throw new IllegalArgumentException("error.DetailsRegelementFactureRequired");
            }

            Collection<DetailsReglementFactureFrs> detailsCollections = new ArrayList<>();
            dto.getDetailsReglementFactureFrsDTOs().forEach(x -> {
                DetailsReglementFactureFrs detailsFactureFournisseur = new DetailsReglementFactureFrs();

                DetailsReglementFactureFrsPK detailsPK = new DetailsReglementFactureFrsPK();
                Preconditions.checkBusinessLogique(x.getCodeTypeDepense() != null, "error.TypeDepenseRequired");
                detailsPK.setCodeTypeDepense(x.getCodeTypeDepense());
                detailsPK.setCodeCategorieDepense(x.getCodeCategorieDepense());
                detailsFactureFournisseur.setDetailsReglementFactureFrsPK(detailsPK);

                Preconditions.checkBusinessLogique(x.getMontant() != null, "error.MontantRequired");
                detailsFactureFournisseur.setMontant(x.getMontant());

                detailsFactureFournisseur.setDateCreate(domaine.getDateCreate());
                detailsFactureFournisseur.setUsercreate(domaine.getUserCreate());
                detailsFactureFournisseur.setReglementFactureFrs(domaine);
                detailsCollections.add(detailsFactureFournisseur);

            });

            if (domaine.getDetailsReglementFactureFrses() != null) {
                domaine.getDetailsReglementFactureFrses().clear();
                domaine.getDetailsReglementFactureFrses().addAll(detailsCollections);
            } else {
                domaine.setDetailsReglementFactureFrses(detailsCollections);
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static ReglementFactureFrsDTO reglementfactureFournisseurToReglementFactureFournisseurDTOUpdate(ReglementFactureFrs domaine) {

        if (domaine != null) {
            ReglementFactureFrsDTO dto = new ReglementFactureFrsDTO();
            dto.setCode(domaine.getCode());
            dto.setObservation(domaine.getObservation());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setMontant(domaine.getMontant());
            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());
            dto.setNumPiece(domaine.getNumPiece());

            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());

            dto.setBanqueDTO(BanqueFactory.banqueToBanqueDTO(domaine.getBanque()));
            dto.setCodeBanque(domaine.getCodeBanque());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            dto.setCostProfitCentreDTO(CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTO(domaine.getCostProfitCentre()));
            dto.setCodeCostProfitCentre(domaine.getCodeCostProfitCentre());

            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver());

            if (domaine.getDetailsReglementFactureFrses() != null) {
                Collection<DetailsReglementFactureFrsDTO> detailsReglementFactureFrses = new ArrayList<>();
                domaine.getDetailsReglementFactureFrses().forEach(x -> {
                    DetailsReglementFactureFrsDTO detailsDTO = new DetailsReglementFactureFrsDTO();
                    detailsDTO = DetailsReglementFactureFournisseurFactory.DetailsReglementFactureFournisseurToDetailsReglementFactureFournisseurDTOCollectionForUpdate(x);
                    detailsReglementFactureFrses.add(detailsDTO);
                });
                if (dto.getDetailsReglementFactureFrsDTOs() != null) {
                    dto.getDetailsReglementFactureFrsDTOs().clear();
                    dto.getDetailsReglementFactureFrsDTOs().addAll(detailsReglementFactureFrses);
                } else {
                    dto.setDetailsReglementFactureFrsDTOs(detailsReglementFactureFrses);
                }
            }

            return dto;
        } else {
            return null;
        }
    }

    public static ReglementFactureFrsDTO reglementfactureFournisseurToReglementFactureFournisseurDTO(ReglementFactureFrs domaine) {

        if (domaine != null) {
            ReglementFactureFrsDTO dto = new ReglementFactureFrsDTO();
            dto.setCode(domaine.getCode());
            dto.setObservation(domaine.getObservation());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setMontant(domaine.getMontant());
            dto.setNumPiece(domaine.getNumPiece());

            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());
            dto.setCostProfitCentreDTO(CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTOLazy(domaine.getCostProfitCentre()));
            dto.setCodeCostProfitCentre(domaine.getCodeCostProfitCentre());

            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());

            dto.setBanqueDTO(BanqueFactory.banqueToBanqueDTO(domaine.getBanque()));
            dto.setCodeBanque(domaine.getCodeBanque());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver());

            if (domaine.getDetailsReglementFactureFrses() != null) {
                Collection<DetailsReglementFactureFrsDTO> detailsFactureFournisseurDTOsCollection = new ArrayList<>();
                domaine.getDetailsReglementFactureFrses().forEach(x -> {
                    DetailsReglementFactureFrsDTO detailsDTO = new DetailsReglementFactureFrsDTO();
                    detailsDTO = DetailsReglementFactureFournisseurFactory.DetailsReglementFactureFournisseurToDetailsReglementFactureFournisseurDTOCollection(x);
                    detailsFactureFournisseurDTOsCollection.add(detailsDTO);
                });
                if (dto.getDetailsReglementFactureFrsDTOs() != null) {
                    dto.getDetailsReglementFactureFrsDTOs().clear();
                    dto.getDetailsReglementFactureFrsDTOs().addAll(detailsFactureFournisseurDTOsCollection);
                } else {
                    dto.setDetailsReglementFactureFrsDTOs(detailsFactureFournisseurDTOsCollection);
                }
            }

            return dto;
        } else {
            return null;
        }
    }

    public static List<ReglementFactureFrsDTO> listReglementFactureFournisseurToReglementFactureFournisseurDTOs(List<ReglementFactureFrs> factureFournisseurs) {
        List<ReglementFactureFrsDTO> list = new ArrayList<>();
        for (ReglementFactureFrs factureFournisseur : factureFournisseurs) {
            list.add(reglementfactureFournisseurToReglementFactureFournisseurDTOUpdate(factureFournisseur));
        }
        return list;
    }

    public static Collection<ReglementFactureFrsDTO> CollectionfactureFournisseursTofactureFournisseursDTOsCollection(Collection<ReglementFactureFrs> factureFournisseurs) {
        List<ReglementFactureFrsDTO> dtos = new ArrayList<>();
        factureFournisseurs.forEach(x -> {
            dtos.add(reglementfactureFournisseurToReglementFactureFournisseurDTO(x));
        });
        return dtos;

    }

    public static ReglementFactureFrs ApprouveReglementFactureFournisseurDTOToReglementFactureFournisseur(ReglementFactureFrs domaine, ReglementFactureFrsDTO dto) {
        domaine.setCode(dto.getCode());
        domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
        if (domaine.getCodeEtatApprouver() != null) {
            domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
        }
        domaine.setCodeUserApprouver(dto.getCodeUserApprouver());
        domaine.setDateApprouve(new Date());

        return domaine;
    }

    public static ReglementFactureFrs CancelReglementFactureFournisseurDTOToReglementFactureFournisseur(ReglementFactureFrs domaine, ReglementFactureFrsDTO dto) {
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
