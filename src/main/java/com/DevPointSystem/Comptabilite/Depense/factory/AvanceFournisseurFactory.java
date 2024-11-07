/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.factory;

import com.DevPointSystem.Comptabilite.Depense.domaine.AvanceFournisseur;
import com.DevPointSystem.Comptabilite.Depense.dto.AvanceFournisseurDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.BanqueFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CaisseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CostProfitCentreFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.DeviseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.EtatApprouverFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.FournisseurFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.ModeReglementFactory;
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
public class AvanceFournisseurFactory {

    
       public static AvanceFournisseur createAvanceFournisseurByCode(int code) {
        AvanceFournisseur domaine = new AvanceFournisseur();
        domaine.setCode(code);
        return domaine;
    }
       
    public static AvanceFournisseur avanceForunisseurDTOTOAvanceFornisseur(AvanceFournisseur domaine, AvanceFournisseurDTO dto) {
        if (dto != null) {
            domaine.setCode(dto.getCode());    
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setApurer(dto.getApurer());
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate());
            domaine.setMontant(dto.getMontant());
            domaine.setObservation(dto.getObservation());
            domaine.setCodeUserApprouver(dto.getCodeUserApprouver());
            domaine.setDateApprouve(dto.getDateApprouve());
            domaine.setNumPiece(dto.getNumPiece());  
            domaine.setPaid(dto.getPaid());    
            domaine.setTauxDevise(dto.getTauxDevise());    
            domaine.setMontantEnDevise(dto.getMontantEnDevise());




            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));
            }

            domaine.setCodeCostProfitCentre(dto.getCodeCostProfitCentre());
            if (domaine.getCodeCostProfitCentre() != null) {
                domaine.setCostProfitCentre(CostProfitCentreFactory.createCostCentreByCode(dto.getCodeCostProfitCentre()));
            }

            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }

            domaine.setCodeModeReglement(dto.getCodeModeReglement());
            if (domaine.getCodeModeReglement() != null) {
                domaine.setModeReglement(ModeReglementFactory.createModeReglementByCode(dto.getCodeModeReglement()));
            }
             domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
            if (domaine.getCodeEtatApprouver() != null) {
                domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
            }
            

            domaine.setCodeCaisse(dto.getCodeCaisse());
            if (domaine.getCodeCaisse() != null) {
                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));
            }

            domaine.setCodeBanque(dto.getCodeBanque());
            if (domaine.getCodeBanque() != null) {
                domaine.setBanque(BanqueFactory.createBanqueByCode(dto.getCodeBanque()));
            }
            return domaine;
        } else {
            return null;
        }
    }
    
    
    public static AvanceFournisseurDTO avanceFournisseurToAvanceFournisseurDTOUpdate(AvanceFournisseur domaine) {

        if (domaine != null) {
            AvanceFournisseurDTO dto = new AvanceFournisseurDTO();
            dto.setCode(domaine.getCode());
            dto.setObservation(domaine.getObservation());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate()); 

            dto.setDateApprouve(domaine.getDateApprouve());

            dto.setUserCreate(domaine.getUserCreate());
            dto.setMontant(domaine.getMontant());
            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());
            dto.setNumPiece(domaine.getNumPiece());
            dto.setMontantEnDevise(domaine.getMontantEnDevise());
            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());
            dto.setTauxDevise(domaine.getTauxDevise());

            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());

            dto.setBanqueDTO(BanqueFactory.banqueToBanqueDTO(domaine.getBanque()));
            dto.setCodeBanque(domaine.getCodeBanque());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            dto.setCostProfitCentreDTO(CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTO(domaine.getCostProfitCentre()));
            dto.setCodeCostProfitCentre(domaine.getCodeCostProfitCentre());

            dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
            dto.setCodeModeReglement(domaine.getCodeModeReglement());
 

            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver());
 

            return dto;
        } else {
            return null;
        }
    }

    public static AvanceFournisseurDTO avanceFournisseurToAvanceFournisseurDTOLazy(AvanceFournisseur domaine) {

        if (domaine != null) {
            AvanceFournisseurDTO dto = new AvanceFournisseurDTO();
            dto.setCode(domaine.getCode()); 
            return dto;
        } else {
            return null;
        }
    }

    public static AvanceFournisseurDTO avanceFournisseurToAvanceFournisseurDTO(AvanceFournisseur domaine) {

        if (domaine != null) {
            AvanceFournisseurDTO dto = new AvanceFournisseurDTO();
            dto.setCode(domaine.getCode());
            dto.setObservation(domaine.getObservation());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setMontant(domaine.getMontant()); 
            dto.setNumPiece(domaine.getNumPiece());
            dto.setMontantEnDevise(domaine.getMontantEnDevise());
            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());
            dto.setCostProfitCentreDTO(CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTOLazy(domaine.getCostProfitCentre()));
            dto.setCodeCostProfitCentre(domaine.getCodeCostProfitCentre());
            dto.setTauxDevise(domaine.getTauxDevise());
            dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
            dto.setCodeModeReglement(domaine.getCodeModeReglement()); 
            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur()); 
            if (domaine.getCodeModeReglement() == 1) {
                dto.setCodeCaisse(domaine.getCodeCaisse());
                if (dto.getCodeCaisse() != null) {
                    dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
                }
                dto.setCodeBanque(null);
                dto.setBanqueDTO(null);
            } else {

                dto.setCodeBanque(dto.getCodeBanque());
                if (dto.getCodeBanque() != null) {
                    dto.setBanqueDTO(BanqueFactory.banqueToBanqueDTO(domaine.getBanque()));
                }
                domaine.setCodeCaisse(null);
                domaine.setCaisse(null);

            } 
            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise()); 
            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver()); 
             
            return dto;
        } else {
            return null;
        }
    }

    public static List<AvanceFournisseurDTO> listAvanceFournisseurToAvanceFournisseurDTOs(List<AvanceFournisseur> factureFournisseurs) {
        List<AvanceFournisseurDTO> list = new ArrayList<>();
        for (AvanceFournisseur factureFournisseur : factureFournisseurs) {
            list.add(avanceFournisseurToAvanceFournisseurDTOUpdate(factureFournisseur));
        }
        return list;
    }

    public static List<AvanceFournisseurDTO> listAvanceFournisseurToAvanceFournisseurDTOsLazy(List<AvanceFournisseur> factureFournisseurs) {
        List<AvanceFournisseurDTO> list = new ArrayList<>();
        for (AvanceFournisseur factureFournisseur : factureFournisseurs) {
            list.add(avanceFournisseurToAvanceFournisseurDTOLazy(factureFournisseur));
        }
        return list;
    }

    public static Collection<AvanceFournisseurDTO> CollectionfAvanceToAvanceFournisseursDTOsCollection(Collection<AvanceFournisseur> factureFournisseurs) {
        List<AvanceFournisseurDTO> dtos = new ArrayList<>();
        factureFournisseurs.forEach(x -> {
            dtos.add(avanceFournisseurToAvanceFournisseurDTO(x));
        });
        return dtos;

    }

    public static AvanceFournisseur ApprouveAvanceFournisseurDTOToAvanceFournisseur(AvanceFournisseur domaine, AvanceFournisseurDTO dto) {
        domaine.setCode(dto.getCode());
        domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
        if (domaine.getCodeEtatApprouver() != null) {
            domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
        }
        domaine.setCodeUserApprouver(dto.getCodeUserApprouver());
        domaine.setDateApprouve(new Date()); 
        return domaine;
    }

    public static AvanceFournisseur CancelAvanceFournisseurDTOToAvanceFournisseur(AvanceFournisseur domaine, AvanceFournisseurDTO dto) {
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
