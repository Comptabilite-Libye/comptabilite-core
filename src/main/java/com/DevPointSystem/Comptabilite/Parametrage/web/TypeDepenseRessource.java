/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.web;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.TypeDepense;
import com.DevPointSystem.Comptabilite.Parametrage.dto.TypeDepenseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.service.TypeDepenseService;
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
public class TypeDepenseRessource {
     private final TypeDepenseService typeDepenseService;

    public TypeDepenseRessource(TypeDepenseService typeDepenseService) {
        this.typeDepenseService = typeDepenseService;
    }

    @GetMapping("type_depense/{code}")
    public ResponseEntity<TypeDepenseDTO> getTypeDepenseByCode(@PathVariable Integer code) {
        TypeDepenseDTO dto = typeDepenseService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("type_depense/all")
    public ResponseEntity<List<TypeDepenseDTO>> getAllTypeDepense() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(typeDepenseService.findAllTypeDepense());
    }

    @PostMapping("type_depense")
    public ResponseEntity<TypeDepenseDTO> postTypeDepense(@Valid @RequestBody TypeDepenseDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TypeDepenseDTO result = typeDepenseService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("type_depense/update")
    public ResponseEntity<TypeDepense> updateTypeDepense(@RequestBody @Valid TypeDepenseDTO dto) throws URISyntaxException {
        TypeDepense result = typeDepenseService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("type_depense/delete/{code}")
    public ResponseEntity<TypeDepense> deleteTypeDepense(@PathVariable("code") Integer code) {
        typeDepenseService.deleteTypeDepense(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
