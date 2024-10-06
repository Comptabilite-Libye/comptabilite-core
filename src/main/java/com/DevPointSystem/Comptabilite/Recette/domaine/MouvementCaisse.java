/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.domaine;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Caisse;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Devise;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.ModeReglement;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.TypeRecette;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "mouvement_caisse", schema = "recette")
@Audited
@AuditTable("mouvement_caisse_AUD")
public class MouvementCaisse {

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

//    @JoinColumn(name = "code_caisse_tr", referencedColumnName = "Code",insertable = false,updatable = false)
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JsonBackReference
//    private Caisse caisseTr;

    @Column(name = "code_caisse_tr")
    private Integer codeCaisseTr;

    @JoinColumn(name = "code_devise", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Devise devise;

    @Column(name = "code_devise", updatable = false, insertable = false)
    private Integer codeDevise;

    @JoinColumn(name = "mode_reglement", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ModeReglement modeReglement;

    @Column(name = "mode_reglement", updatable = false, insertable = false)
    private Integer codeModeReglement;

    @NotNull
    @Column(name = "user_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_Create", nullable = false)
    private Date dateCreate;

    @NotNull
    @Column(name = "debit", columnDefinition = ("decimal(18,3) "), nullable = false)
    private BigDecimal debit;
    @NotNull
    @Column(name = "credit", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal credit;

    @Column(name = "montant_devise", columnDefinition = ("decimal(18,3) "), nullable = false)
    private BigDecimal mntDevise;

    @Column(name = "code_tier", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String codeTier;

    public MouvementCaisse() {
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

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getMntDevise() {
        return mntDevise;
    }

    public void setMntDevise(BigDecimal mntDevise) {
        this.mntDevise = mntDevise;
    }

    public String getCodeTier() {
        return codeTier;
    }

    public void setCodeTier(String codeTier) {
        this.codeTier = codeTier;
    }

//    public Caisse getCaisseTr() {
//        return caisseTr;
//    }
//
//    public void setCaisseTr(Caisse caisseTr) {
//        this.caisseTr = caisseTr;
//    }

    
    
}
