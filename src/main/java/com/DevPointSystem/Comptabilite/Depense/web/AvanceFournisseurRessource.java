/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.web;

import com.DevPointSystem.Comptabilite.Authentification.service.AccessUserService;
import com.DevPointSystem.Comptabilite.Depense.domaine.AvanceFournisseur;
import com.DevPointSystem.Comptabilite.Depense.dto.AvanceFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.service.AvanceFournisseurService;
import com.DevPointSystem.Comptabilite.Parametrage.service.ParamService;
import com.DevPointSystem.Comptabilite.Parametrage.service.SocieteService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/depense/")
public class AvanceFournisseurRessource {

    private final AvanceFournisseurService reglementFactureFournisseurService;
    private final ParamService paramService;
    private final SocieteService societeService;

    private final AccessUserService accessUserService;

    public AvanceFournisseurRessource(AvanceFournisseurService reglementFactureFournisseurService, ParamService paramService, SocieteService societeService, AccessUserService accessUserService) {
        this.reglementFactureFournisseurService = reglementFactureFournisseurService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.accessUserService = accessUserService;
    }

    @GetMapping("avance_fournisseur/{code}")
    public ResponseEntity<AvanceFournisseurDTO> getAvanceFournisseurByCode(@PathVariable Integer code) {
        AvanceFournisseurDTO dTO = reglementFactureFournisseurService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("avance_fournisseur/all")
    public ResponseEntity<List<AvanceFournisseurDTO>> getAllAvanceFournisseur() {
        return ResponseEntity.ok().body(reglementFactureFournisseurService.findAllAvanceFournisseur());
    }

    @GetMapping("avance_fournisseur/codeFournisseur")
    public ResponseEntity<Collection<AvanceFournisseurDTO>> getAvanceFournisseurByCodeCaisse(@RequestParam Collection<Integer> codeFournisseur) {
        Collection<AvanceFournisseurDTO> dTOs = reglementFactureFournisseurService.findByCodeFournisseur(codeFournisseur);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("avance_fournisseur/By")
    public ResponseEntity<Collection<AvanceFournisseurDTO>> getAvanceFournisseurByِCodeFournisseurAndNonApurer(@RequestParam Integer codeFournisseur, Boolean apurer) {
        Collection<AvanceFournisseurDTO> dTOs = reglementFactureFournisseurService.findByCodeFournisseurAndNonApurer(codeFournisseur, apurer);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("avance_fournisseur/codeDevise")
    public ResponseEntity<Collection<AvanceFournisseurDTO>> getAvanceFournisseurByCodeDevise(@RequestParam Collection<Integer> codeDevise) {
        Collection<AvanceFournisseurDTO> dTOs = reglementFactureFournisseurService.findByCodeDevise(codeDevise);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("avance_fournisseur/EtatApprouver/{codeEtatApprouver}")
    public ResponseEntity<List<AvanceFournisseurDTO>> getReglementFactFrsByCodeEtatApprouve(@PathVariable Integer codeEtatApprouver) {
        List<AvanceFournisseurDTO> dto = reglementFactureFournisseurService.findByEtatApprouver(codeEtatApprouver);
        return ResponseEntity.ok().body(dto);

    }

    @GetMapping("avance_fournisseur/LazyEtatApprouver/{codeEtatApprouver}")
    public ResponseEntity<List<AvanceFournisseurDTO>> getReglementFactFrsByCodeEtatApprouveLazy(@PathVariable Integer codeEtatApprouver) {
        List<AvanceFournisseurDTO> dto = reglementFactureFournisseurService.findByEtatApprouverLazy(codeEtatApprouver);
        return ResponseEntity.ok().body(dto);

    }

//    @PostMapping("avance_fournisseur")
//    public ResponseEntity<AvanceFournisseurDTO> postAvanceFournisseur(@Valid @RequestBody AvanceFournisseurDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
//        AvanceFournisseurDTO result = reglementFactureFournisseurService.save(dTO);
//        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
//    }
    @PutMapping("avance_fournisseur/update")
    public ResponseEntity<AvanceFournisseurDTO> updateAvanceFournisseur(@Valid @RequestBody AvanceFournisseurDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        AvanceFournisseurDTO result = reglementFactureFournisseurService.updateNewWithFlush(dTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("avance_fournisseur/approuver")
    public ResponseEntity<AvanceFournisseurDTO> approuveReglementFactFrs(@Valid @RequestBody AvanceFournisseurDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        AvanceFournisseurDTO result = reglementFactureFournisseurService.approuveAvanceFournisseur(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("avance_fournisseur/cancel_approuver")
    public ResponseEntity<AvanceFournisseurDTO> Cancel_approuveReglementFactFrs(@Valid @RequestBody AvanceFournisseurDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        AvanceFournisseurDTO result = reglementFactureFournisseurService.CancelapprouveAvanceFournisseur(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("avance_fournisseur/delete/{code}")
    public ResponseEntity<AvanceFournisseur> deleteAvanceFournisseur(@PathVariable("code") Integer code) {
        reglementFactureFournisseurService.deleteAvanceFournisseur(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
