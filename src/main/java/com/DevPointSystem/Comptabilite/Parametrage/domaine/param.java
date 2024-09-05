/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.domaine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "param", schema = "param")
@Audited
@AuditTable("param_AUD")
public class param  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;

    @Column(name = "code_param", columnDefinition = ("varchar(200)"))
    private String codeParam;

    @Column(name = "description", columnDefinition = ("nvarchar(200)"))
    private String description;

    @Column(name = "valeur", columnDefinition = ("nvarchar(200)"))
    private String valeur;

    @Column(name = "visible")
    private Boolean Visible;

    public param() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeParam() {
        return codeParam;
    }

    public void setCodeParam(String codeParam) {
        this.codeParam = codeParam;
    }

    public Boolean getVisible() {
        return Visible;
    }

    public void setVisible(Boolean Visible) {
        this.Visible = Visible;
    }

}
