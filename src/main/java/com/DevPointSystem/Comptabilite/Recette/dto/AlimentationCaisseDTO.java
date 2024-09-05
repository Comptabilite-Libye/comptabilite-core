/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.dto;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.ModeReglement;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CaisseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.DeviseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.ModeReglementDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.TypeRecetteDTO;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class AlimentationCaisseDTO {

    private Integer code;

    private String codeSaisie;

    private String observation;

    private CaisseDTO caisseDTO;

    private Integer codeCaisse;

    private DeviseDTO deviseDTO;

    private Integer codeDevise;

    private String userCreate;

    private Date dateCreate;

    private TypeRecetteDTO typeRecetteDTO;

    private Integer codeTypeRecette;
    private BigDecimal montant;

    private ModeReglementDTO modeReglementDTO;

    private Integer codeModeReglement;

    public AlimentationCaisseDTO() {
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
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

    public TypeRecetteDTO getTypeRecetteDTO() {
        return typeRecetteDTO;
    }

    public void setTypeRecetteDTO(TypeRecetteDTO typeRecetteDTO) {
        this.typeRecetteDTO = typeRecetteDTO;
    }

    public Integer getCodeTypeRecette() {
        return codeTypeRecette;
    }

    public void setCodeTypeRecette(Integer codeTypeRecette) {
        this.codeTypeRecette = codeTypeRecette;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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

    
}
