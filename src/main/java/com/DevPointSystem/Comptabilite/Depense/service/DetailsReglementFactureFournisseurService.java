/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.service;
 
import com.DevPointSystem.Comptabilite.Depense.repository.DetailsFactureFournisseurRepo;
import com.DevPointSystem.Comptabilite.Depense.repository.DetailsReglementFactureFrsRepo;


/**
 *
 * @author Administrator
 */
public class DetailsReglementFactureFournisseurService {
    private final DetailsReglementFactureFrsRepo detailsReglementFactureFrsRepo;

    public DetailsReglementFactureFournisseurService(DetailsReglementFactureFrsRepo detailsReglementFactureFrsRepo) {
        this.detailsReglementFactureFrsRepo = detailsReglementFactureFrsRepo;
    }

 
    
    
    
      public Boolean deleteByCodeReglementFactureFournisseur(Integer codeFactureFournisseur) {
        detailsReglementFactureFrsRepo.deleteByCodeReglementFactureFournisseur(codeFactureFournisseur);
        return true;
    }
}
