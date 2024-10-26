/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.dto;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.CostProfitCentre;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CostProfitCentreDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.DeviseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.EtatApprouverDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.FournisseurDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class FactureFournisseurDTO {

//    private Integer code;
//
//    private String codeSaisie;
//
//    private String observation;
//
//    private DeviseDTO deviseDTO;
//
//    private Integer codeDevise;
//
//    private String userCreate;
//
//    private Date dateCreate;
//
//    private EtatApprouverDTO etatApprouverDTO;
//    private Integer codeEtatApprouver;
//    private Integer codeUserApprouver;
//    private Date dateApprouve;
//    private String designationEtatApprouve;
//
//    private BigDecimal montantFactureFrounisseur;
//
//    private String numFactureFournisseur;
//    private BigDecimal montant;
//
//    private FournisseurDTO fournisseurDTO;
//
//    private Integer codeFournisseur;
//
//    @Basic(optional = false)
//    @NotNull
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
//    private LocalDate dateFactureFournisseur;
//
//    private Collection<DetailsFactureFournisseurDTO> detailsFactureFournisseursDTOs;
//
//    
//    
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

    private Boolean paid;

    @Basic(optional = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateFactureFournisseur;

    private Collection<DetailsFactureFournisseurDTO> detailsFactureFournisseursDTOs;

    private BigDecimal montantFactureFrounisseur;

    private BigDecimal montant;

    private String numFactureFournisseur;

    private CostProfitCentreDTO costProfitCentreDTO;

    private Integer codeCostProfitCentre;

    public FactureFournisseurDTO() {
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

    public LocalDate getDateFactureFournisseur() {
        return dateFactureFournisseur;
    }

    public void setDateFactureFournisseur(LocalDate dateFactureFournisseur) {
        this.dateFactureFournisseur = dateFactureFournisseur;
    }

    public Collection<DetailsFactureFournisseurDTO> getDetailsFactureFournisseursDTOs() {
        return detailsFactureFournisseursDTOs;
    }

    public void setDetailsFactureFournisseursDTOs(Collection<DetailsFactureFournisseurDTO> detailsFactureFournisseursDTOs) {
        this.detailsFactureFournisseursDTOs = detailsFactureFournisseursDTOs;
    }

    public BigDecimal getMontantFactureFrounisseur() {
        return montantFactureFrounisseur;
    }

    public void setMontantFactureFrounisseur(BigDecimal montantFactureFrounisseur) {
        this.montantFactureFrounisseur = montantFactureFrounisseur;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getNumFactureFournisseur() {
        return numFactureFournisseur;
    }

    public void setNumFactureFournisseur(String numFactureFournisseur) {
        this.numFactureFournisseur = numFactureFournisseur;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
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
    
    

}
