/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.web;

import com.DevPointSystem.Comptabilite.Authentification.service.AccessUserService;
import com.DevPointSystem.Comptabilite.Depense.domaine.ReglementFactureFrs;
import com.DevPointSystem.Comptabilite.Depense.dto.ReglementFactureFrsDTO;
import com.DevPointSystem.Comptabilite.Depense.service.ReglementFactureFournisseurService;
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
public class ReglementFactureFournisseurRessource {

    private final ReglementFactureFournisseurService reglementFactureFournisseurService;
    private final ParamService paramService;
    private final SocieteService societeService;

    private final AccessUserService accessUserService;

    public ReglementFactureFournisseurRessource(ReglementFactureFournisseurService reglementFactureFournisseurService, ParamService paramService, SocieteService societeService, AccessUserService accessUserService) {
        this.reglementFactureFournisseurService = reglementFactureFournisseurService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.accessUserService = accessUserService;
    }

    @GetMapping("reglement_facture_fournisseur/{code}")
    public ResponseEntity<ReglementFactureFrsDTO> getReglementFactureFournisseurByCode(@PathVariable Integer code) {
        ReglementFactureFrsDTO dTO = reglementFactureFournisseurService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("reglement_facture_fournisseur/all")
    public ResponseEntity<List<ReglementFactureFrsDTO>> getAllReglementFactureFournisseur() {
        return ResponseEntity.ok().body(reglementFactureFournisseurService.findAllReglementFactureFournisseur());
    }

    @GetMapping("reglement_facture_fournisseur/codeFournisseur")
    public ResponseEntity<Collection<ReglementFactureFrsDTO>> getReglementFactureFournisseurByCodeCaisse(@RequestParam Collection<Integer> codeFournisseur) {
        Collection<ReglementFactureFrsDTO> dTOs = reglementFactureFournisseurService.findByCodeFournisseur(codeFournisseur);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("reglement_facture_fournisseur/RegfactureBy")
    public ResponseEntity<Collection<ReglementFactureFrsDTO>> getReglementFactureFournisseurByCodeFournisseurAndDevise(@RequestParam Integer codeFournisseur, @RequestParam Integer codeDevise) {
        Collection<ReglementFactureFrsDTO> dTOs = reglementFactureFournisseurService.findByCodeFournisseurAndCodeDevise(codeFournisseur, codeDevise);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("reglement_facture_fournisseur/codeDevise")
    public ResponseEntity<Collection<ReglementFactureFrsDTO>> getReglementFactureFournisseurByCodeDevise(@RequestParam Collection<Integer> codeDevise) {
        Collection<ReglementFactureFrsDTO> dTOs = reglementFactureFournisseurService.findByCodeDevise(codeDevise);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("reglement_facture_fournisseur/EtatApprouver/{codeEtatApprouver}")
    public ResponseEntity<List<ReglementFactureFrsDTO>> getReglementFactFrsByCodeEtatApprouve(@PathVariable Integer codeEtatApprouver) {
        List<ReglementFactureFrsDTO> dto = reglementFactureFournisseurService.findByEtatApprouver(codeEtatApprouver);
        return ResponseEntity.ok().body(dto);

    }

    @GetMapping("reglement_facture_fournisseur/LazyEtatApprouver/{codeEtatApprouver}")
    public ResponseEntity<List<ReglementFactureFrsDTO>> getReglementFactFrsByCodeEtatApprouveLazy(@PathVariable Integer codeEtatApprouver) {
        List<ReglementFactureFrsDTO> dto = reglementFactureFournisseurService.findByEtatApprouverLazy(codeEtatApprouver);
        return ResponseEntity.ok().body(dto);

    }

    @PostMapping("reglement_facture_fournisseur")
    public ResponseEntity<ReglementFactureFrsDTO> postReglementFactureFournisseur(@Valid @RequestBody ReglementFactureFrsDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ReglementFactureFrsDTO result = reglementFactureFournisseurService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

//    @PutMapping("facture_fournisseur/update")
//    public ResponseEntity<ReglementFactureFrsDTO> updateFactureFournisseur(@Valid @RequestBody ReglementFactureFrsDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
//        ReglementFactureFrsDTO result = factureFournisseurService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }
    @PutMapping("reglement_facture_fournisseur/update")
    public ResponseEntity<ReglementFactureFrsDTO> updateReglementFactureFournisseur(@Valid @RequestBody ReglementFactureFrsDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        ReglementFactureFrsDTO result = reglementFactureFournisseurService.updateNewWithFlush(dTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("reglement_facture_fournisseur/approuver")
    public ResponseEntity<ReglementFactureFrsDTO> approuveReglementFactFrs(@Valid @RequestBody ReglementFactureFrsDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        ReglementFactureFrsDTO result = reglementFactureFournisseurService.approuveRegFactFrs(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("reglement_facture_fournisseur/cancel_approuver")
    public ResponseEntity<ReglementFactureFrsDTO> Cancel_approuveReglementFactFrs(@Valid @RequestBody ReglementFactureFrsDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        ReglementFactureFrsDTO result = reglementFactureFournisseurService.CancelapprouveRegFactFrs(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("reglement_facture_fournisseur/delete/{code}")
    public ResponseEntity<ReglementFactureFrs> deleteReglementFactureFournisseur(@PathVariable("code") Integer code) {
        reglementFactureFournisseurService.deleteReglementFactureFournisseur(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("facture_fournisseur/edition/{code}")
//    public ResponseEntity<byte[]> getReport(@PathVariable Integer code) throws Exception {
//
//        Collection<DetailsReglementFactureFrsDTO> dto = factureFournisseurService.findOneWithDetails(code);
//
//        String fileNameJrxml = "src/main/resources/Reports/AlimentCaisse.jrxml";
//        paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
//        ReglementFactureFrsDTO rslt = factureFournisseurService.findOne(code);
//
//        Preconditions.checkArgument(rslt.getCodeUserApprouver() !=null, "error.User.Approuve.Found");
//        AccessUserDTO getsignature = accessUserService.findOneByCode(rslt.getCodeUserApprouver());
//
//        SocieteDTO societeDTO = societeService.findOne(1);
//        JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("ItemDataSource", new JRBeanCollectionDataSource(dto));
//        params.put("UserCreate", auth.getName());
//        params.put("CodeSaisie", rslt.getCodeSaisie());
//        params.put("societe", dTOs.getValeur());
//        params.put("devise", rslt.getDeviseDTO().getDesignationAr());
//        params.put("typeRecette", rslt.getDetailsReglementFactureFrsDTOs().iterator().next().getDesignationArTypeRecette());
//        params.put("caisse", rslt.getCaisseDTO().getDesignationAr());
//        params.put("modeReglement", rslt.getModeReglementDTO().getDesignationAr());
//        params.put("montant", rslt.getMontant());
//        params.put("montantEnDevise", rslt.getMontantEnDevise());
//        params.put("tauxChange", rslt.getTauxChange());
//        params.put("observation", rslt.getObservation());
//        params.put("dateCreate", rslt.getDateCreate());
//        params.put("designationEtatApprouve", rslt.getEtatApprouverDTO().getDesignation());
//
//        params.put("logo", societeDTO.getLogo());
//        params.put("signature", getsignature.getSignature());
//
//        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
//        JRPdfExporter exporter = new JRPdfExporter();
//        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//        exporter.setExporterInput(new SimpleExporterInput(print));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
//        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
//        reportConfig.setSizePageToContent(true);
//        reportConfig.setForceLineBreakPolicy(false);
//        exporter.exportReport();
//        var res = pdfOutputStream.toByteArray();
//        var headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename= filename.pdf");
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(res);
//    }
}
