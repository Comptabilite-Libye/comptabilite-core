/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.web;

import com.DevPointSystem.Comptabilite.Parametrage.service.ParamService;
import com.DevPointSystem.Comptabilite.Parametrage.service.SocieteService;
import com.DevPointSystem.Comptabilite.Recette.dto.MouvementCaisseDTO;
import com.DevPointSystem.Comptabilite.Recette.service.MouvementCaisseService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/recette/")
public class MouvementCaisseRessource {

    private final MouvementCaisseService mouvementCaisseService;
    private final ParamService paramService;
    private final SocieteService societeService;

    public MouvementCaisseRessource(MouvementCaisseService mouvementCaisseService, ParamService paramService, SocieteService societeService) {
        this.mouvementCaisseService = mouvementCaisseService;
        this.paramService = paramService;
        this.societeService = societeService;
    }

    @GetMapping("mouvement_caisse/{code}")
    public ResponseEntity<MouvementCaisseDTO> getMouvementCaisseByCode(@PathVariable Integer code) {
        MouvementCaisseDTO dTO = mouvementCaisseService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("mouvement_caisse/all")
    public ResponseEntity<List<MouvementCaisseDTO>> getAllMouvementCaisse() {
        return ResponseEntity.ok().body(mouvementCaisseService.findAllMouvementCaisse());
    }

//    @GetMapping("mouvement_caisse/codeCaisse")
//    public ResponseEntity<Collection<MouvementCaisseDTO>> getMouvementCaisseByCodeCaisse(@RequestParam Collection<Integer> codeCaisse) {
//        Collection<MouvementCaisseDTO> dTOs = mouvementCaisseService.findByCodeCaisse(codeCaisse);
//        return ResponseEntity.ok().body(dTOs);
//    }
//
//    @GetMapping("mouvement_caisse/codeCaisseTr")
//    public ResponseEntity<Collection<MouvementCaisseDTO>> getMouvementCaisseByByCodeCaisseTr(@RequestParam Collection<Integer> codeCaisseTr) {
//        Collection<MouvementCaisseDTO> dTOs = mouvementCaisseService.findByCodeCaisseTr(codeCaisseTr);
//        return ResponseEntity.ok().body(dTOs);
//    }
//    
//    
//    @GetMapping("mouvement_caisse/codeModeReglement")
//    public ResponseEntity<Collection<MouvementCaisseDTO>> getMouvementCaisseByByCodeModeReglement(@RequestParam Collection<Integer> codeModeReglement) {
//        Collection<MouvementCaisseDTO> dTOs = mouvementCaisseService.findByCodeModeReglement(codeModeReglement);
//        return ResponseEntity.ok().body(dTOs);
//    }
//    
//    
//        @GetMapping("mouvement_caisse/codeTier")
//    public ResponseEntity<Collection<MouvementCaisseDTO>> getMouvementCaisseByByCodeTier(@RequestParam Collection<String> codeTier) {
//        Collection<MouvementCaisseDTO> dTOs = mouvementCaisseService.findByCodeTier(codeTier);
//        return ResponseEntity.ok().body(dTOs);
//    }
//
//    @GetMapping("mouvement_caisse/codeDevise")
//    public ResponseEntity<Collection<MouvementCaisseDTO>> getMouvementCaisseByCodeDevise(@RequestParam Collection<Integer> codeDevise) {
//        Collection<MouvementCaisseDTO> dTOs = mouvementCaisseService.findByCodeDevise(codeDevise);
//        return ResponseEntity.ok().body(dTOs);
//    }

    @PostMapping("mouvement_caisse")
    public ResponseEntity<MouvementCaisseDTO> postMouvementCaisse(@Valid @RequestBody MouvementCaisseDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        MouvementCaisseDTO result = mouvementCaisseService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

//    
//
//    @GetMapping("mouvement_caisse/edition/{code}")
//    public ResponseEntity<byte[]> getReport(@PathVariable Integer code) throws Exception {
//
//        String fileNameJrxml = "src/main/resources/Reports/AlimentCaisse.jrxml";
//        paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
//        MouvementCaisseDTO rslt = mouvementCaisseService.findOne(code);
//        SocieteDTO societeDTO = societeService.findOne(1);
//        JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Map<String, Object> params = new HashMap<>();
//        params.put("UserCreate", auth.getName());
//        params.put("CodeSaisie", rslt.getCodeSaisie());
//        params.put("societe", dTOs.getValeur());
//        params.put("devise", rslt.getDeviseDTO().getDesignationAr());
//        params.put("typeRecette", rslt.getTypeRecetteDTO().getDesignationAr());
//        params.put("caisse", rslt.getCaisseDTO().getDesignationAr());
//
//        params.put("montant", rslt.getMontant());
//        params.put("observation", rslt.getObservation());
//        params.put("dateCreate", rslt.getDateCreate());
//
//        params.put("logo", societeDTO.getLogo());
//        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
//        //initiate exporter
//        JRPdfExporter exporter = new JRPdfExporter();
//        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//        exporter.setExporterInput(new SimpleExporterInput(print));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
//
//        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
//        reportConfig.setSizePageToContent(true);
//        reportConfig.setForceLineBreakPolicy(false);
//
//        exporter.exportReport();
//        var res = pdfOutputStream.toByteArray();
//
//        var headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename= filename.pdf");
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(res);
//
//    }
}
