/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.domaine;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Banque;
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
@Table(name = "reglement_facture_fournisseur", schema = "depense")
@Audited
@AuditTable("reglement_facture_fournisseur_AUD")
public class ReglementFactureFrs {

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reglementFactureFrs")
    private Collection<DetailsReglementFactureFrs> detailsReglementFactureFrses;

    @Column(name = "montant", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montant;

    @JoinColumn(name = "code_cost_centre", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private CostProfitCentre costProfitCentre;

    @Column(name = "code_cost_centre", updatable = false, insertable = false)
    private Integer codeCostProfitCentre;

    @JoinColumn(name = "code_mode_reglement", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ModeReglement modeReglement;

    @Column(name = "code_mode_reglement", updatable = false, insertable = false)
    private Integer codeModeReglement;

    @JoinColumn(name = "code_banque", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Banque banque;

    @Column(name = "code_banque", updatable = false, insertable = false)
    private Integer codeBanque;

    @JoinColumn(name = "code_caisse", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Caisse caisse;

    @Column(name = "code_caisse", updatable = false, insertable = false)
    private Integer codeCaisse;

    @Size(max = 200)
    @Column(name = "num_piece", length = 200, columnDefinition = "nvarchar(20)")
    private String numPiece;

    public ReglementFactureFrs() {
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

    public Date getDateApprouve() {
        return dateApprouve;
    }

    public void setDateApprouve(Date dateApprouve) {
        this.dateApprouve = dateApprouve;
    }

    public Collection<DetailsReglementFactureFrs> getDetailsReglementFactureFrses() {
        return detailsReglementFactureFrses;
    }

    public void setDetailsReglementFactureFrses(Collection<DetailsReglementFactureFrs> detailsReglementFactureFrses) {
        this.detailsReglementFactureFrses = detailsReglementFactureFrses;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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

    public ModeReglement getModeReglement() {
        return modeReglement;
    }

    public void setModeReglement(ModeReglement modeReglement) {
        this.modeReglement = modeReglement;
    }

    public Integer getCodeModeReglement() {
        return codeModeReglement;
    }

    public void setCodeModeReglement(Integer codeModeReglement) {
        this.codeModeReglement = codeModeReglement;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public Integer getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(Integer codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }

    public Integer getCodeCaisse() {
        return codeCaisse;
    }

    public void setCodeCaisse(Integer codeCaisse) {
        this.codeCaisse = codeCaisse;
    }

    
    
}
