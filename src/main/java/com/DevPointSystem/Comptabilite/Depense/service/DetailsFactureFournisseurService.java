/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.service;
 
import com.DevPointSystem.Comptabilite.Depense.repository.DetailsFactureFournisseurRepo;


/**
 *
 * @author Administrator
 */
public class DetailsFactureFournisseurService {
    private final DetailsFactureFournisseurRepo detailsFactureFournisseurRepo;

    public DetailsFactureFournisseurService(DetailsFactureFournisseurRepo detailsFactureFournisseurRepo) {
        this.detailsFactureFournisseurRepo = detailsFactureFournisseurRepo;
    }
    
    
    
      public Boolean deleteByCodeFactureFournisseur(Integer codeFactureFournisseur) {
        detailsFactureFournisseurRepo.deleteByCodeFactureFournisseur(codeFactureFournisseur);
        return true;
    }
}
