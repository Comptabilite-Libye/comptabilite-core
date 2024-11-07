/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.domaine;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.CategorieDepense;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.TypeDepense;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "details_reglement_facture_fournisseur", schema = "depense")
@Audited
@AuditTable(value = "details_reglement_facture_fournisseur_AUD")
public class DetailsReglementFactureFrs {
    
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DetailsReglementFactureFrsPK detailsReglementFactureFrsPK;

    @MapsId("codeReglementFactureFournisseur")
    @JoinColumn(name = "code_reglement_facture_fournisseur", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false)
    private ReglementFactureFrs reglementFactureFrs;

    @JoinColumn(name = "code_type_depense", referencedColumnName = "Code", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private TypeDepense typeDepense;

    @Column(name = "code_type_depense", insertable = false, updatable = false)
    private Integer codeTypeDepense;

    @JoinColumn(name = "code_categorie_depense", referencedColumnName = "Code", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private CategorieDepense categorieDepense;

    @Column(name = "code_categorie_depense", insertable = false, updatable = false)
    private Integer codeCategorieDepense;

    @Column(name = "user_create", nullable = false, columnDefinition = "nvarchar(200)")
    private String usercreate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_Create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Column(name = "montant", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montant;

    public DetailsReglementFactureFrs() {
    }

    public DetailsReglementFactureFrsPK getDetailsReglementFactureFrsPK() {
        return detailsReglementFactureFrsPK;
    }

    public void setDetailsReglementFactureFrsPK(DetailsReglementFactureFrsPK detailsReglementFactureFrsPK) {
        this.detailsReglementFactureFrsPK = detailsReglementFactureFrsPK;
    }

    public ReglementFactureFrs getReglementFactureFrs() {
        return reglementFactureFrs;
    }

    public void setReglementFactureFrs(ReglementFactureFrs reglementFactureFrs) {
        this.reglementFactureFrs = reglementFactureFrs;
    }

    
    
 

 

    public TypeDepense getTypeDepense() {
        return typeDepense;
    }

    public void setTypeDepense(TypeDepense typeDepense) {
        this.typeDepense = typeDepense;
    }

    public Integer getCodeTypeDepense() {
        return codeTypeDepense;
    }

    public void setCodeTypeDepense(Integer codeTypeDepense) {
        this.codeTypeDepense = codeTypeDepense;
    }

    public CategorieDepense getCategorieDepense() {
        return categorieDepense;
    }

    public void setCategorieDepense(CategorieDepense categorieDepense) {
        this.categorieDepense = categorieDepense;
    }

    public Integer getCodeCategorieDepense() {
        return codeCategorieDepense;
    }

    public void setCodeCategorieDepense(Integer codeCategorieDepense) {
        this.codeCategorieDepense = codeCategorieDepense;
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
    
    
    
}
