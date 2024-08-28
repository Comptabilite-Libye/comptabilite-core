/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.service;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Fournisseur;
import com.DevPointSystem.Comptabilite.Parametrage.dto.FournisseurDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.FournisseurFactory;
import com.DevPointSystem.Comptabilite.Parametrage.repository.FournisseurRepo;
import com.google.common.base.Preconditions;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class FournisseurService {
    private final FournisseurRepo fournisseurRepo;

    public FournisseurService(FournisseurRepo fournisseurRepo) {
        this.fournisseurRepo = fournisseurRepo;
    }

    @Transactional(readOnly = true)
    public List<FournisseurDTO> findAllFournisseur() {
        return FournisseurFactory.listFournisseurToFournisseurDTOs(fournisseurRepo.findAll());

    }

    @Transactional(readOnly = true)
    public FournisseurDTO findOne(Integer code) {
        Fournisseur domaine = fournisseurRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.FournisseurNotFound");
        return FournisseurFactory.fournisseurToFournisseurDTO(domaine);
    }

//
    public FournisseurDTO save(FournisseurDTO dto) {
        Fournisseur domaine = FournisseurFactory.fournisseurDTOToFournisseur(dto, new Fournisseur());
        domaine = fournisseurRepo.save(domaine);
        return FournisseurFactory.fournisseurToFournisseurDTO(domaine);
    }

    public Fournisseur update(FournisseurDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.FournisseurNotFound");
        Fournisseur domaine = fournisseurRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(true, "error.FournisseurNotFound");
        dto.setCode(domaine.getCode());
        FournisseurFactory.fournisseurDTOToFournisseur(dto, domaine);
        return fournisseurRepo.save(domaine);
    }

    public void deleteFournisseur(Integer code) {
        Preconditions.checkArgument(fournisseurRepo.existsById(code), "error.FournisseurNotFound");
        fournisseurRepo.deleteById(code);
    }
}
