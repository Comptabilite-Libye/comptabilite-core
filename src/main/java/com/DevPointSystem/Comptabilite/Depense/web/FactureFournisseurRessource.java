/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.web;

import com.DevPointSystem.Comptabilite.Authentification.dto.AccessUserDTO;
import com.DevPointSystem.Comptabilite.Authentification.service.AccessUserService;
import com.DevPointSystem.Comptabilite.Depense.domaine.FactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.dto.DetailsFactureFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.dto.FactureFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.service.FactureFournisseurService;
import com.DevPointSystem.Comptabilite.Parametrage.dto.SocieteDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.paramDTO;
import com.DevPointSystem.Comptabilite.Parametrage.service.ParamService;
import com.DevPointSystem.Comptabilite.Parametrage.service.SocieteService;
import com.google.common.base.Preconditions;
import jakarta.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/depense/")
public class FactureFournisseurRessource {

    private final FactureFournisseurService factureFournisseurService;
    private final ParamService paramService;
    private final SocieteService societeService;

    private final AccessUserService accessUserService;

    public FactureFournisseurRessource(FactureFournisseurService factureFournisseurService, ParamService paramService, SocieteService societeService, AccessUserService accessUserService) {
        this.factureFournisseurService = factureFournisseurService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.accessUserService = accessUserService;
    }

    @GetMapping("facture_fournisseur/{code}")
    public ResponseEntity<FactureFournisseurDTO> getFactureFournisseurByCode(@PathVariable Integer code) {
        FactureFournisseurDTO dTO = factureFournisseurService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("facture_fournisseur/all")
    public ResponseEntity<List<FactureFournisseurDTO>> getAllFactureFournisseur() {
        return ResponseEntity.ok().body(factureFournisseurService.findAllFactureFournisseur());
    }

    @GetMapping("facture_fournisseur/codeFournisseur")
    public ResponseEntity<Collection<FactureFournisseurDTO>> getFactureFournisseurByCodeCaisse(@RequestParam Collection<Integer> codeFournisseur) {
        Collection<FactureFournisseurDTO> dTOs = factureFournisseurService.findByCodeFournisseur(codeFournisseur);
        return ResponseEntity.ok().body(dTOs);
    }
    
        @GetMapping("facture_fournisseur/factureBy")
    public ResponseEntity<Collection<FactureFournisseurDTO>> getFactureFournisseurByCodeCaisse(@RequestParam Integer codeFournisseur ,@RequestParam Integer codeDevise,@RequestParam Boolean Paid ) {
        Collection<FactureFournisseurDTO> dTOs = factureFournisseurService.findByCodeFournisseurAndCodeDeviseAndNotPaid(codeFournisseur,codeDevise,Paid);
        return ResponseEntity.ok().body(dTOs);
    }
    
    
    

    @GetMapping("facture_fournisseur/codeDevise")
    public ResponseEntity<Collection<FactureFournisseurDTO>> getFactureFournisseurByCodeDevise(@RequestParam Collection<Integer> codeDevise) {
        Collection<FactureFournisseurDTO> dTOs = factureFournisseurService.findByCodeDevise(codeDevise);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("facture_fournisseur/EtatApprouver/{codeEtatApprouver}")
    public ResponseEntity<List<FactureFournisseurDTO>> getAppelOffreByCodeEtatApprouve(@PathVariable Integer codeEtatApprouver) {
        List<FactureFournisseurDTO> dto = factureFournisseurService.findByEtatApprouver(codeEtatApprouver);
        return ResponseEntity.ok().body(dto);

    }

    @PostMapping("facture_fournisseur")
    public ResponseEntity<FactureFournisseurDTO> postFactureFournisseur(@Valid @RequestBody FactureFournisseurDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FactureFournisseurDTO result = factureFournisseurService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

//    @PutMapping("facture_fournisseur/update")
//    public ResponseEntity<FactureFournisseurDTO> updateFactureFournisseur(@Valid @RequestBody FactureFournisseurDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
//        FactureFournisseurDTO result = factureFournisseurService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }
    @PutMapping("facture_fournisseur/update")
    public ResponseEntity<FactureFournisseurDTO> updateFactureFournisseur(@Valid @RequestBody FactureFournisseurDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        FactureFournisseurDTO result = factureFournisseurService.updateNewWithFlush(dTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("facture_fournisseur/approuver")
    public ResponseEntity<FactureFournisseurDTO> approuveDemandeAchat(@Valid @RequestBody FactureFournisseurDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        FactureFournisseurDTO result = factureFournisseurService.approuveAC(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("facture_fournisseur/cancel_approuver")
    public ResponseEntity<FactureFournisseurDTO> Cancel_approuveDemandeAchat(@Valid @RequestBody FactureFournisseurDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        FactureFournisseurDTO result = factureFournisseurService.CancelapprouveAC(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("facture_fournisseur/delete/{code}")
    public ResponseEntity<FactureFournisseur> deleteFactureFournisseur(@PathVariable("code") Integer code) {
        factureFournisseurService.deleteFactureFournisseur(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("facture_fournisseur/edition/{code}")
//    public ResponseEntity<byte[]> getReport(@PathVariable Integer code) throws Exception {
//
//        Collection<DetailsFactureFournisseurDTO> dto = factureFournisseurService.findOneWithDetails(code);
//
//        String fileNameJrxml = "src/main/resources/Reports/AlimentCaisse.jrxml";
//        paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
//        FactureFournisseurDTO rslt = factureFournisseurService.findOne(code);
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
//        params.put("typeRecette", rslt.getDetailsFactureFournisseurDTOs().iterator().next().getDesignationArTypeRecette());
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
