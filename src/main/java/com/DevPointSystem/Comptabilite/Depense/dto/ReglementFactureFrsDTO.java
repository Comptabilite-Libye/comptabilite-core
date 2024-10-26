/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.dto;

import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsReglementFactureFrs;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Banque;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Caisse;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.CostProfitCentre;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Devise;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.EtatApprouver;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Fournisseur;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.ModeReglement;
import com.DevPointSystem.Comptabilite.Parametrage.dto.BanqueDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CaisseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CostProfitCentreDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.DeviseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.EtatApprouverDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.FournisseurDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.ModeReglementDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ReglementFactureFrsDTO {
    
     private Integer code;

    private String codeSaisie;

    private FournisseurDTO fournisseurDTO;

    private Integer codeFournisseur;

    private String userCreate;

    private Date dateCreate;

    private String observation;

    private DeviseDTO deviseDTO;

    private Integer codeDevise;

    private EtatApprouverDTO etatApprouverDTO;

    private Integer codeEtatApprouver;

    private Integer codeUserApprouver;

    private Date dateApprouve;

    private Collection<DetailsReglementFactureFrsDTO> detailsReglementFactureFrsDTOs;

    private BigDecimal montant;

    private CostProfitCentreDTO costProfitCentreDTO;

    private Integer codeCostProfitCentre;

    private ModeReglementDTO modeReglementDTO;

    private Integer codeModeReglement;

    private BanqueDTO banqueDTO;

    private Integer codeBanque;

    private String numPiece;
     
    private CaisseDTO caisseDTO;
 
    private Integer codeCaisse;

    public ReglementFactureFrsDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeSaisie() {
        return codeSaisie;
    }

    public void setCodeSaisie(String codeSaisie) {
        this.codeSaisie = codeSaisie;
    }

    public FournisseurDTO getFournisseurDTO() {
        return fournisseurDTO;
    }

    public void setFournisseurDTO(FournisseurDTO fournisseurDTO) {
        this.fournisseurDTO = fournisseurDTO;
    }

    public Integer getCodeFournisseur() {
        return codeFournisseur;
    }

    public void setCodeFournisseur(Integer codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public DeviseDTO getDeviseDTO() {
        return deviseDTO;
    }

    public void setDeviseDTO(DeviseDTO deviseDTO) {
        this.deviseDTO = deviseDTO;
    }

    public Integer getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(Integer codeDevise) {
        this.codeDevise = codeDevise;
    }

    public EtatApprouverDTO getEtatApprouverDTO() {
        return etatApprouverDTO;
    }

    public void setEtatApprouverDTO(EtatApprouverDTO etatApprouverDTO) {
        this.etatApprouverDTO = etatApprouverDTO;
    }

    public Integer getCodeEtatApprouver() {
        return codeEtatApprouver;
    }

    public void setCodeEtatApprouver(Integer codeEtatApprouver) {
        this.codeEtatApprouver = codeEtatApprouver;
    }

    public Integer getCodeUserApprouver() {
        return codeUserApprouver;
    }

    public void setCodeUserApprouver(Integer codeUserApprouver) {
        this.codeUserApprouver = codeUserApprouver;
    }

    public Date getDateApprouve() {
        return dateApprouve;
    }

    public void setDateApprouve(Date dateApprouve) {
        this.dateApprouve = dateApprouve;
    }

    public Collection<DetailsReglementFactureFrsDTO> getDetailsReglementFactureFrsDTOs() {
        return detailsReglementFactureFrsDTOs;
    }

    public void setDetailsReglementFactureFrsDTOs(Collection<DetailsReglementFactureFrsDTO> detailsReglementFactureFrsDTOs) {
        this.detailsReglementFactureFrsDTOs = detailsReglementFactureFrsDTOs;
    }

   
    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public CostProfitCentreDTO getCostProfitCentreDTO() {
        return costProfitCentreDTO;
    }

    public void setCostProfitCentreDTO(CostProfitCentreDTO costProfitCentreDTO) {
        this.costProfitCentreDTO = costProfitCentreDTO;
    }

    public Integer getCodeCostProfitCentre() {
        return codeCostProfitCentre;
    }

    public void setCodeCostProfitCentre(Integer codeCostProfitCentre) {
        this.codeCostProfitCentre = codeCostProfitCentre;
    }

    public ModeReglementDTO getModeReglementDTO() {
        return modeReglementDTO;
    }

    public void setModeReglementDTO(ModeReglementDTO modeReglementDTO) {
        this.modeReglementDTO = modeReglementDTO;
    }

    public Integer getCodeModeReglement() {
        return codeModeReglement;
    }

    public void setCodeModeReglement(Integer codeModeReglement) {
        this.codeModeReglement = codeModeReglement;
    }

    public BanqueDTO getBanqueDTO() {
        return banqueDTO;
    }

    public void setBanqueDTO(BanqueDTO banqueDTO) {
        this.banqueDTO = banqueDTO;
    }

    public Integer getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(Integer codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    public CaisseDTO getCaisseDTO() {
        return caisseDTO;
    }

    public void setCaisseDTO(CaisseDTO caisseDTO) {
        this.caisseDTO = caisseDTO;
    }

    public Integer getCodeCaisse() {
        return codeCaisse;
    }

    public void setCodeCaisse(Integer codeCaisse) {
        this.codeCaisse = codeCaisse;
    }
    
    
    
}
