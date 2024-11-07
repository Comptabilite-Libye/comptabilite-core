/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.domaine;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
@Embeddable
public class DetailsFactureFournisseurPK {

    @Basic(optional = false)
    @NotNull
    @Column(name = "code_facture_fournisseur", nullable = false)
    private int codeFactureFournisseur;

    @Basic(optional = false)
    @NotNull
    @Column(name = "code_type_depense", nullable = false)
    private int codeTypeDepense;

    @Basic(optional = false)
    @NotNull
    @Column(name = "code_categorie_depense", nullable = false)
    private int codeCategorieDepense;

    public DetailsFactureFournisseurPK() {
    }

    public int getCodeFactureFournisseur() {
        return codeFactureFournisseur;
    }

    public void setCodeFactureFournisseur(int codeFactureFournisseur) {
        this.codeFactureFournisseur = codeFactureFournisseur;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.codeFactureFournisseur;
        hash = 61 * hash + this.codeTypeDepense;
        hash = 61 * hash + this.codeCategorieDepense;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetailsFactureFournisseurPK other = (DetailsFactureFournisseurPK) obj;
        if (this.codeFactureFournisseur != other.codeFactureFournisseur) {
            return false;
        }
        if (this.codeTypeDepense != other.codeTypeDepense) {
            return false;
        }
        return this.codeCategorieDepense == other.codeCategorieDepense;
    }
    
    

}
