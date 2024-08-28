/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.web;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Caisse;
import com.DevPointSystem.Comptabilite.Parametrage.dto.CaisseDTO;
import com.DevPointSystem.Comptabilite.Parametrage.service.CaisseService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class CaisseRessource {

    private final CaisseService caisseService;

    public CaisseRessource(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

    @GetMapping("caisse/{code}")
    public ResponseEntity<CaisseDTO> getCaisseByCode(@PathVariable Integer code) {
        CaisseDTO dto = caisseService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping( "caisse/all")
    public ResponseEntity<List<CaisseDTO>> getAllCaisse() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
//        return ResponseEntity.ok().body(caisseService.findAllCaisse());
        return new ResponseEntity<>(caisseService.findAllCaisse(), headers, HttpStatus.OK);
    }

    @PostMapping("caisse")
    public ResponseEntity<CaisseDTO> postCaisse(@Valid @RequestBody CaisseDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        CaisseDTO result = caisseService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("caisse/update")
    public ResponseEntity<Caisse> updateCaisse(@RequestBody @Valid CaisseDTO dto) throws URISyntaxException {
        Caisse result = caisseService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("caisse/delete/{code}")
    public ResponseEntity<Caisse> deleteCaisse(@PathVariable("code") Integer code) {
        caisseService.deleteCaisse(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
