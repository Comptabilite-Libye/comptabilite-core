/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.domaine;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
public class DetailsReglementFactureFrsPK {
    @Basic(optional = false)
    @NotNull
    @Column(name = "code_reglement_facture_fournisseur", nullable = false)
    private int codeReglementFactureFournisseur;

    @Basic(optional = false)
    @NotNull
    @Column(name = "code_type_depense", nullable = false)
    private int codeTypeDepense;

    @Basic(optional = false)
    @NotNull
    @Column(name = "code_categorie_depense", nullable = false)
    private int codeCategorieDepense;

    public DetailsReglementFactureFrsPK() {
    }

    public int getCodeReglementFactureFournisseur() {
        return codeReglementFactureFournisseur;
    }

    public void setCodeReglementFactureFournisseur(int codeReglementFactureFournisseur) {
        this.codeReglementFactureFournisseur = codeReglementFactureFournisseur;
    }

 

    public int getCodeTypeDepense() {
        return codeTypeDepense;
    }

    public void setCodeTypeDepense(int codeTypeDepense) {
        this.codeTypeDepense = codeTypeDepense;
    }

    public int getCodeCategorieDepense() {
        return codeCategorieDepense;
    }

    public void setCodeCategorieDepense(int codeCategorieDepense) {
        this.codeCategorieDepense = codeCategorieDepense;
    }

    
    
}
