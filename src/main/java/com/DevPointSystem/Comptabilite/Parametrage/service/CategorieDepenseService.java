/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.service;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.CategorieDepense;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.TypeDepense;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CategorieDepenseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CategorieDepenseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.repository.CategorieDepenseRepo;
import com.DevPointSystem.Comptabilite.Parametrage.repository.TypeDepenseRepo;
import com.google.common.base.Preconditions;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class CategorieDepenseService {
    
    private final CategorieDepenseRepo categorieDepenseRepo;
    
    private final TypeDepenseRepo typeDepenseRepo;

    public CategorieDepenseService(CategorieDepenseRepo categorieDepenseRepo, TypeDepenseRepo typeDepenseRepo) {
        this.categorieDepenseRepo = categorieDepenseRepo;
        this.typeDepenseRepo = typeDepenseRepo;
    }
    

   
    @Transactional(readOnly = true)
    public List<CategorieDepenseDTO> findAllCategorieDepense() {
        return CategorieDepenseFactory.listCategorieDepenseToCategorieDepenseDTOs(categorieDepenseRepo.findAll());

    }

    @Transactional(readOnly = true)
    public CategorieDepenseDTO findOne(Integer code) {
        CategorieDepense domaine = categorieDepenseRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine != null, "error.CategorieDepenseNotFound");
        return CategorieDepenseFactory.categorieDepenseToCategorieDepenseDTO(domaine);
    }

//
    public CategorieDepenseDTO save(CategorieDepenseDTO dto) {
        CategorieDepense domaine = CategorieDepenseFactory.categorieDepenseDTOToCategorieDepense(dto, new CategorieDepense());
        domaine = categorieDepenseRepo.save(domaine);
        return CategorieDepenseFactory.categorieDepenseToCategorieDepenseDTO(domaine);
    }

    public CategorieDepense update(CategorieDepenseDTO dto) { 
        CategorieDepense domaine = categorieDepenseRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.CategorieDepenseNotFound");
        dto.setCode(domaine.getCode());
        CategorieDepenseFactory.categorieDepenseDTOToCategorieDepense(dto, domaine);
        return categorieDepenseRepo.save(domaine);
    }

    public void deleteCategorieDepense(Integer code) {
        Preconditions.checkArgument(categorieDepenseRepo.existsById(code), "error.CategorieDepenseNotFound");
        
        List<TypeDepense> typeDepense = typeDepenseRepo.findByCodeCategorieDepense(code);
        
        Preconditions.checkArgument( !typeDepense.isEmpty(), "error.CategorieDepenseNotFound");
        categorieDepenseRepo.deleteById(code);
    }
    
    
}
