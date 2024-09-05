/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.service;

 
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Societe;
import com.DevPointSystem.Comptabilite.Parametrage.dto.SocieteDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.SocieteFactory;
import com.DevPointSystem.Comptabilite.Parametrage.repository.SocieteRepo;
import com.DevPointSystem.Comptabilite.web.Util.Helper;
import com.DevPointSystem.Comptabilite.web.Util.RestPreconditions;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class SocieteService {

    private final SocieteRepo societeRepo;

    public SocieteService(SocieteRepo societeRepo) {
        this.societeRepo = societeRepo;
    }

  
    @Transactional(readOnly = true)
    public SocieteDTO findOne(Integer id) {
        Societe societe = societeRepo.getReferenceById(id);
        RestPreconditions.checkFound(societe, "societe.NotFound");
        SocieteDTO dto = SocieteFactory.societeToSocieteDTO(societe, false);
        return dto;
    }

    @Transactional(readOnly = true)
    public Societe findSociete(Integer id) {
        Societe societe = societeRepo.getReferenceById(id);
        RestPreconditions.checkFound(societe, "societe.NotFound");
        return societe;
    }

//    @ApiOperation("always returns a list of single object, we cannot modify return type since api is used")
    @Transactional(readOnly = true)
    public Collection<SocieteDTO> findAll(Boolean withoutLogo) {
        Societe societe = societeRepo.findFirstBy();
        SocieteDTO societeDTO = SocieteFactory.societeToSocieteDTO(societe, withoutLogo); 
        return (Collection<SocieteDTO>) Collections.singleton(societeDTO);
    }

    @Transactional(
            readOnly = true
    )
    public SocieteDTO findFirst() {
        Societe result = societeRepo.findFirstBy();
        return SocieteFactory.societeToSocieteDTO(result, false);
    }

    @Transactional(
            readOnly = true
    )
    public SocieteDTO findOne(Integer id, Boolean withoutLogo) {
        Societe societe = societeRepo.getReferenceById(id);
        RestPreconditions.checkFound(societe, "societe.NotFound");
        SocieteDTO dto = SocieteFactory.societeToSocieteDTO(societe, withoutLogo);
        return dto;
    }

    public SocieteDTO save(SocieteDTO dTO) throws IOException {
        Societe domaine = SocieteFactory.societeDTOToSociete(dTO);
        if (!dTO.getImagePath().isEmpty()) {
            domaine.setLogo(Helper.extractBytes(dTO.getImagePath(), "png"));
        }
        domaine = societeRepo.save(domaine);
        SocieteDTO resultDTO = SocieteFactory.societeToSocieteDTO(domaine, false);
        return resultDTO;
    }

    public SocieteDTO update(SocieteDTO dTO) throws IOException {
        Societe inBase = societeRepo.getReferenceById(dTO.getCode());
        Preconditions.checkArgument(inBase != null, "Societe does not exist");
        SocieteDTO result = save(dTO);
        return result;
    }

    public void delete(Integer id) { 
        societeRepo.deleteById(id); }
     
        

}
