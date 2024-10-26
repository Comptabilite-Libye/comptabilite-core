/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.dto;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.CategorieDepense;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CategorieDepenseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.TypeDepenseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsFactureFournisseurDTO {

    private int codeFactureFournisseur;
    private String codeSaisieFactureFournisseur;

    @NotNull
    private TypeDepenseDTO typeDepenseDTO;
    private String codeSaisieTypeDepense;
    private Integer codeTypeDepense;
    private String designationArTypeDepense;
    private String designationLtTypeDepense;

    private String usercreate;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    private BigDecimal montant;
    @NotNull
    private CategorieDepenseDTO categorieDepenseDTO; 
    private Integer codeCategorieDepense;

    public DetailsFactureFournisseurDTO() {
    }

    public int getCodeFactureFournisseur() {
        return codeFactureFournisseur;
    }

    public void setCodeFactureFournisseur(int codeFactureFournisseur) {
        this.codeFactureFournisseur = codeFactureFournisseur;
    }

    public String getCodeSaisieFactureFournisseur() {
        return codeSaisieFactureFournisseur;
    }

    public void setCodeSaisieFactureFournisseur(String codeSaisieFactureFournisseur) {
        this.codeSaisieFactureFournisseur = codeSaisieFactureFournisseur;
    }

    public TypeDepenseDTO getTypeDepenseDTO() {
        return typeDepenseDTO;
    }

    public void setTypeDepenseDTO(TypeDepenseDTO typeDepenseDTO) {
        this.typeDepenseDTO = typeDepenseDTO;
    }

    public String getCodeSaisieTypeDepense() {
        return codeSaisieTypeDepense;
    }

    public void setCodeSaisieTypeDepense(String codeSaisieTypeDepense) {
        this.codeSaisieTypeDepense = codeSaisieTypeDepense;
    }

    public Integer getCodeTypeDepense() {
        return codeTypeDepense;
    }

    public void setCodeTypeDepense(Integer codeTypeDepense) {
        this.codeTypeDepense = codeTypeDepense;
    }

    public String getDesignationArTypeDepense() {
        return designationArTypeDepense;
    }

    public void setDesignationArTypeDepense(String designationArTypeDepense) {
        this.designationArTypeDepense = designationArTypeDepense;
    }

    public String getDesignationLtTypeDepense() {
        return designationLtTypeDepense;
    }

    public void setDesignationLtTypeDepense(String designationLtTypeDepense) {
        this.designationLtTypeDepense = designationLtTypeDepense;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public CategorieDepenseDTO getCategorieDepenseDTO() {
        return categorieDepenseDTO;
    }

    public void setCategorieDepenseDTO(CategorieDepenseDTO categorieDepenseDTO) {
        this.categorieDepenseDTO = categorieDepenseDTO;
    }

    public Integer getCodeCategorieDepense() {
        return codeCategorieDepense;
    }

    public void setCodeCategorieDepense(Integer codeCategorieDepense) {
        this.codeCategorieDepense = codeCategorieDepense;
    }
    
    

}
