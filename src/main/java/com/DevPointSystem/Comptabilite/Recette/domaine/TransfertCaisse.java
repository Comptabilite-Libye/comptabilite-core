/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.domaine;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Caisse;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Devise;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.EtatApprouver;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.ModeReglement;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.util.Collection;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "transfert_caisse", schema = "recette")
@Audited
@AuditTable("transfert_caisse_AUD")
public class TransfertCaisse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "code_saisie", length = 200)
    private String codeSaisie;

    @JoinColumn(name = "code_caisse", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Caisse caisse;

    @Column(name = "code_caisse", updatable = false, insertable = false)
    private Integer codeCaisse;

    @JoinColumn(name = "code_caisse_tr", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Caisse caisseTr;

    @Column(name = "code_caisse_tr", updatable = false, insertable = false)
    private Integer codeCaisseTr;

    @JoinColumn(name = "code_devise", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Devise devise;

    @Column(name = "code_devise", updatable = false, insertable = false)
    private Integer codeDevise;

    @Column(name = "user_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_Create", nullable = false)
    private Date dateCreate;

    @Size(max = 200)
    @Column(name = "observation", length = 200, nullable = false, columnDefinition = "nvarchar(max)")
    private String observation;

    @JoinColumn(name = "mode_reglement", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ModeReglement modeReglement;

    @Column(name = "mode_reglement", updatable = false, insertable = false)
    private Integer codeModeReglement;

    @JoinColumn(name = "etat_approuver", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private EtatApprouver etatApprouver;

    @Column(name = "etat_approuver", updatable = false, insertable = false)
    private Integer codeEtatApprouver;

    @Column(name = "code_user_approuver", columnDefinition = "Nvarchar(200) default ''")
    private Integer codeUserApprouver;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_approuve", columnDefinition = "datetime default(getdate())")
    private Date dateApprouve;

    @Column(name = "montant", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montant;

    @Column(name = "montant_en_devise_principal", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montantEnDevise;

    @Column(name = "taux_de_change", columnDefinition = ("decimal(18,3)"))
    private BigDecimal tauxChange;

    public TransfertCaisse() {
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

    public Caisse getCaisseTr() {
        return caisseTr;
    }

    public void setCaisseTr(Caisse caisseTr) {
        this.caisseTr = caisseTr;
    }

    public Integer getCodeCaisseTr() {
        return codeCaisseTr;
    }

    public void setCodeCaisseTr(Integer codeCaisseTr) {
        this.codeCaisseTr = codeCaisseTr;
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getMontantEnDevise() {
        return montantEnDevise;
    }

    public void setMontantEnDevise(BigDecimal montantEnDevise) {
        this.montantEnDevise = montantEnDevise;
    }

    public BigDecimal getTauxChange() {
        return tauxChange;
    }

    public void setTauxChange(BigDecimal tauxChange) {
        this.tauxChange = tauxChange;
    }
    
    

}
