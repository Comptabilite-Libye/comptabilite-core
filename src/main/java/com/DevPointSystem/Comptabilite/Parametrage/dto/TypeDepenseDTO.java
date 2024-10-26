/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.dto;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TypeDepenseDTO {

    private Integer code;

    private String codeSaisie;   
    
    private String codeSaisieTypeDepense;
    private String designationArTypeDepense;
    private String designationLtTypeDepense;

    private String designationAr;

    private String designationLt;
    private boolean actif;

    private String userCreate;

    private Date dateCreate;
    private CategorieDepenseDTO categorieDepenseDTO;

    private Integer codeCategorieDepense;

    public TypeDepenseDTO() {
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

    public String getDesignationAr() {
        return designationAr;
    }

    public void setDesignationAr(String designationAr) {
        this.designationAr = designationAr;
    }

    public String getDesignationLt() {
        return designationLt;
    }

    public void setDesignationLt(String designationLt) {
        this.designationLt = designationLt;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
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

    public String getCodeSaisieTypeDepense() {
        return codeSaisieTypeDepense;
    }

    public void setCodeSaisieTypeDepense(String codeSaisieTypeDepense) {
        this.codeSaisieTypeDepense = codeSaisieTypeDepense;
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

    

}
