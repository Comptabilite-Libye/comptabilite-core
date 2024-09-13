/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.domaine;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.TypeRecette;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Id;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "details_alimentation_caisse", schema = "recette")
@Audited
@AuditTable(value = "details_alimentation_caisse_AUD")
public class DetailsAlimentationCaisse {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DetailsAlimentationCaissePK detailsAlimentationCaissePK;

    @MapsId("codeAlimentationCaisse")
    @JoinColumn(name = "code_alimentation_caisse", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false)
    private AlimentationCaisse alimentationCaisse;

    @JoinColumn(name = "code_type_recette", referencedColumnName = "Code", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private TypeRecette typeRecette;

    @Column(name = "code_type_recette", insertable = false, updatable = false)
    private Integer codeTypeRecette;

    @Column(name = "user_create", nullable = false, columnDefinition = "nvarchar(200)")
    private String usercreate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_Create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Column(name = "montant", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montant;

    @Column(name = "montant_devise_principal", columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantDevise;

    public DetailsAlimentationCaisse() {
    }

    public AlimentationCaisse getAlimentationCaisse() {
        return alimentationCaisse;
    }

    public void setAlimentationCaisse(AlimentationCaisse alimentationCaisse) {
        this.alimentationCaisse = alimentationCaisse;
    }

    public TypeRecette getTypeRecette() {
        return typeRecette;
    }

    public void setTypeRecette(TypeRecette typeRecette) {
        this.typeRecette = typeRecette;
    }

    public Integer getCodeTypeRecette() {
        return codeTypeRecette;
    }

    public void setCodeTypeRecette(Integer codeTypeRecette) {
        this.codeTypeRecette = codeTypeRecette;
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

    public DetailsAlimentationCaissePK getDetailsAlimentationCaissePK() {
        return detailsAlimentationCaissePK;
    }

    public void setDetailsAlimentationCaissePK(DetailsAlimentationCaissePK detailsAlimentationCaissePK) {
        this.detailsAlimentationCaissePK = detailsAlimentationCaissePK;
    }

    public BigDecimal getMontantDevise() {
        return montantDevise;
    }

    public void setMontantDevise(BigDecimal montantDevise) {
        this.montantDevise = montantDevise;
    }
    

}
