/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.service;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.TypeDepense;
import com.DevPointSystem.Comptabilite.Parametrage.dto.TypeDepenseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.TypeDepenseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.repository.TypeDepenseRepo;
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
public class TypeDepenseService {
     private final TypeDepenseRepo typeDepenseRepo;

    public TypeDepenseService(TypeDepenseRepo typeDepenseRepo) {
        this.typeDepenseRepo = typeDepenseRepo;
    }

    @Transactional(readOnly = true)
    public List<TypeDepenseDTO> findAllTypeDepense() {
        return TypeDepenseFactory.listTypeDepenseToTypeDepenseDTOs(typeDepenseRepo.findAll());

    }

    @Transactional(readOnly = true)
    public TypeDepenseDTO findOne(Integer code) {
        TypeDepense domaine = typeDepenseRepo.findByCode(code);
        Preconditions.checkArgument(domaine  != null, "error.TypeDepenseNotFound");
        return TypeDepenseFactory.typeDepenseToTypeDepenseDTO(domaine);
    }

//
    public TypeDepenseDTO save(TypeDepenseDTO dto) {
        TypeDepense domaine = TypeDepenseFactory.typeDepenseDTOToTypeDepense(dto, new TypeDepense());
        domaine = typeDepenseRepo.save(domaine);
        return TypeDepenseFactory.typeDepenseToTypeDepenseDTO(domaine);
    }

    public TypeDepense update(TypeDepenseDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.TypeDepenseNotFound");
        TypeDepense domaine = typeDepenseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(true, "error.TypeDepenseNotFound");
        dto.setCode(domaine.getCode());
        TypeDepenseFactory.typeDepenseDTOToTypeDepense(dto, domaine);
        return typeDepenseRepo.save(domaine);
    }

    public void deleteTypeDepense(Integer code) {
        Preconditions.checkArgument(typeDepenseRepo.existsById(code), "error.TypeDepenseNotFound");
        typeDepenseRepo.deleteById(code);
    }
}
