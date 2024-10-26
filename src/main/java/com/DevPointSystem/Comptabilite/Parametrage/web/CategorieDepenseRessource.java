/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.web;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.CategorieDepense;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CategorieDepenseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.service.CategorieDepenseService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class CategorieDepenseRessource {
    private final CategorieDepenseService categorieDepenseService;

    public CategorieDepenseRessource(CategorieDepenseService categorieDepenseService) {
        this.categorieDepenseService = categorieDepenseService;
    }

    @GetMapping("categorie_depense/{code}")
    public ResponseEntity<CategorieDepenseDTO> getCategorieDepenseByCode(@PathVariable Integer code) {
        CategorieDepenseDTO dto = categorieDepenseService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("categorie_depense/all")
    public ResponseEntity<List<CategorieDepenseDTO>> getAllCategorieDepense() { 
        return ResponseEntity.ok().body(categorieDepenseService.findAllCategorieDepense());
    }

    
    
    
    @PostMapping("categorie_depense")
    public ResponseEntity<CategorieDepenseDTO> postCategorieDepense(@Valid @RequestBody CategorieDepenseDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        CategorieDepenseDTO result = categorieDepenseService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("categorieDepense/update")
    public ResponseEntity<CategorieDepense> updateCategorieDepense(@RequestBody @Valid CategorieDepenseDTO dto) throws URISyntaxException {
        CategorieDepense result = categorieDepenseService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("categorie_depense/delete/{code}")
    public ResponseEntity<CategorieDepense> deleteCategorieDepense(@PathVariable("code") Integer code) {
        categorieDepenseService.deleteCategorieDepense(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
