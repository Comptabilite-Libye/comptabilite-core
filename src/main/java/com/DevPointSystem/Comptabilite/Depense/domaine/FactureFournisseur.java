/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.domaine;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Caisse;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.CostProfitCentre;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Devise;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.EtatApprouver;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Fournisseur;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.ModeReglement;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "facture_fournisseur", schema = "depense")
@Audited
@AuditTable("facture_fournisseur_AUD")
public class FactureFournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "code_saisie", length = 200)
    private String codeSaisie;

    @JoinColumn(name = "code_fournisseur", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Fournisseur fournisseur;

    @Column(name = "code_fournisseur", updatable = false, insertable = false)
    private Integer codeFournisseur;

   
    @Column(name = "user_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_Create", nullable = false)
    private Date dateCreate;

    @Size(max = 200)
    @Column(name = "observation", length = 200, nullable = false, columnDefinition = "nvarchar(max)")
    private String observation;

    @JoinColumn(name = "code_devise", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Devise devise;

    @Column(name = "code_devise", updatable = false, insertable = false)
    private Integer codeDevise;

    @JoinColumn(name = "etat_approuver", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private EtatApprouver etatApprouver;

    @Column(name = "etat_approuver", updatable = false, insertable = false)
    private Integer codeEtatApprouver;

    @Column(name = "code_user_approuver", columnDefinition = "Nvarchar(200) default ''")
    private Integer codeUserApprouver;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_approuve", columnDefinition = "datetime ")
    private Date dateApprouve;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_facture_fournisseur", nullable = false, columnDefinition = ("date"))
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateFactureFournisseur;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factureFournisseur")
    private Collection<DetailsFactureFournisseur> detailsFactureFournisseurs;

    @Column(name = "montant_facture_fournisseur", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montantFactureFrounisseur;

    @Column(name = "montant", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montant;

    @Column(name = "numero_facture_forunisseur", columnDefinition = ("nvarchar(max)"), nullable = false, unique = true)
    private String numFactureFournisseur;

    @Column(name = "paid", columnDefinition = ("bit default 0"), nullable = false)
    private Boolean paid;

    @JoinColumn(name = "code_cost_centre", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private CostProfitCentre costProfitCentre;

    @Column(name = "code_cost_centre", updatable = false, insertable = false)
    private Integer codeCostProfitCentre;

    public FactureFournisseur() {
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

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
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

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public Integer getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(Integer codeDevise) {
        this.codeDevise = codeDevise;
    }

    public EtatApprouver getEtatApprouver() {
        return etatApprouver;
    }

    public void setEtatApprouver(EtatApprouver etatApprouver) {
        this.etatApprouver = etatApprouver;
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

    public LocalDate getDateFactureFournisseur() {
        return dateFactureFournisseur;
    }

    public void setDateFactureFournisseur(LocalDate dateFactureFournisseur) {
        this.dateFactureFournisseur = dateFactureFournisseur;
    }

    public Collection<DetailsFactureFournisseur> getDetailsFactureFournisseurs() {
        return detailsFactureFournisseurs;
    }

    public void setDetailsFactureFournisseurs(Collection<DetailsFactureFournisseur> detailsFactureFournisseurs) {
        this.detailsFactureFournisseurs = detailsFactureFournisseurs;
    }

    public BigDecimal getMontantFactureFrounisseur() {
        return montantFactureFrounisseur;
    }

    public void setMontantFactureFrounisseur(BigDecimal montantFactureFrounisseur) {
        this.montantFactureFrounisseur = montantFactureFrounisseur;
    }

    public String getNumFactureFournisseur() {
        return numFactureFournisseur;
    }

    public void setNumFactureFournisseur(String numFactureFournisseur) {
        this.numFactureFournisseur = numFactureFournisseur;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDateApprouve() {
        return dateApprouve;
    }

    public void setDateApprouve(Date dateApprouve) {
        this.dateApprouve = dateApprouve;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public CostProfitCentre getCostProfitCentre() {
        return costProfitCentre;
    }

    public void setCostProfitCentre(CostProfitCentre costProfitCentre) {
        this.costProfitCentre = costProfitCentre;
    }

    public Integer getCodeCostProfitCentre() {
        return codeCostProfitCentre;
    }

    public void setCodeCostProfitCentre(Integer codeCostProfitCentre) {
        this.codeCostProfitCentre = codeCostProfitCentre;
    }

}
