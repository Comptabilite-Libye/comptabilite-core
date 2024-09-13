/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.domaine;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
@Embeddable
public class DetailsAlimentationCaissePK {

    @Basic(optional = false)
    @NotNull
    @Column(name = "code_alimentation_caisse", nullable = false)
    private int codeAlimentationCaisse;

    @Basic(optional = false)
    @NotNull
    @Column(name = "code_type_recette", nullable = false)
    private int codeTypeRecette;

    public DetailsAlimentationCaissePK() {
    }

    public int getCodeAlimentationCaisse() {
        return codeAlimentationCaisse;
    }

    public void setCodeAlimentationCaisse(int codeAlimentationCaisse) {
        this.codeAlimentationCaisse = codeAlimentationCaisse;
    }

    public int getCodeTypeRecette() {
        return codeTypeRecette;
    }

    public void setCodeTypeRecette(int codeTypeRecette) {
        this.codeTypeRecette = codeTypeRecette;
    }

}
