/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.web;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Beneficiaire;
import com.DevPointSystem.Comptabilite.Parametrage.dto.BeneficiaireDTO;
import com.DevPointSystem.Comptabilite.Parametrage.service.BeneficiaireService;
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
public class BeneficiaireRessource {

    private final BeneficiaireService beneficiaireService;

    public BeneficiaireRessource(BeneficiaireService beneficiaireService) {
        this.beneficiaireService = beneficiaireService;
    }

    @GetMapping("beneficiaire/{code}")
    public ResponseEntity<BeneficiaireDTO> getBeneficiaireByCode(@PathVariable Integer code) {
        BeneficiaireDTO dto = beneficiaireService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("beneficiaire/all")
    public ResponseEntity<List<BeneficiaireDTO>> getAllBeneficiaire() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(beneficiaireService.findAllBeneficiaire());
    }

    @PostMapping("beneficiaire")
    public ResponseEntity<BeneficiaireDTO> postBeneficiaire(@Valid @RequestBody BeneficiaireDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        BeneficiaireDTO result = beneficiaireService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("beneficiaire/update")
    public ResponseEntity<Beneficiaire> updateBeneficiaire(@RequestBody @Valid BeneficiaireDTO dto) throws URISyntaxException {
        Beneficiaire result = beneficiaireService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("beneficiaire/delete/{code}")
    public ResponseEntity<Beneficiaire> deleteBeneficiaire(@PathVariable("code") Integer code) {
        beneficiaireService.deleteBeneficiaire(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
