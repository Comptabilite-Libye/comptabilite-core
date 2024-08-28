/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.service;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.TypeRecette;
import com.DevPointSystem.Comptabilite.Parametrage.dto.TypeRecetteDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.TypeRecetteFactory;
import com.DevPointSystem.Comptabilite.Parametrage.repository.TypeRecetteRepo;
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
public class TypeRecetteService {
     private final TypeRecetteRepo typeRecetteRepo;

    public TypeRecetteService(TypeRecetteRepo typeRecetteRepo) {
        this.typeRecetteRepo = typeRecetteRepo;
    }

    @Transactional(readOnly = true)
    public List<TypeRecetteDTO> findAllTypeRecette() {
        return TypeRecetteFactory.listTypeRecetteToTypeRecetteDTOs(typeRecetteRepo.findAll());

    }

    @Transactional(readOnly = true)
    public TypeRecetteDTO findOne(Integer code) {
        TypeRecette domaine = typeRecetteRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.TypeRecetteNotFound");
        return TypeRecetteFactory.typeRecetteToTypeRecetteDTO(domaine);
    }

//
    public TypeRecetteDTO save(TypeRecetteDTO dto) {
        TypeRecette domaine = TypeRecetteFactory.typeRecetteDTOToTypeRecette(dto, new TypeRecette());
        domaine = typeRecetteRepo.save(domaine);
        return TypeRecetteFactory.typeRecetteToTypeRecetteDTO(domaine);
    }

    public TypeRecette update(TypeRecetteDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.TypeRecetteNotFound");
        TypeRecette domaine = typeRecetteRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(true, "error.TypeRecetteNotFound");
        dto.setCode(domaine.getCode());
        TypeRecetteFactory.typeRecetteDTOToTypeRecette(dto, domaine);
        return typeRecetteRepo.save(domaine);
    }

    public void deleteTypeRecette(Integer code) {
        Preconditions.checkArgument(typeRecetteRepo.existsById(code), "error.TypeRecetteNotFound");
        typeRecetteRepo.deleteById(code);
    }
}
