/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.web;

import com.DevPointSystem.Comptabilite.Authentification.service.AccessUserService;
import com.DevPointSystem.Comptabilite.Depense.domaine.FactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.dto.FactureFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.service.FactureFournisseurService;
import com.DevPointSystem.Comptabilite.Parametrage.dto.SocieteDTO;
import com.DevPointSystem.Comptabilite.Parametrage.dto.paramDTO;
import com.DevPointSystem.Comptabilite.Parametrage.service.ParamService;
import com.DevPointSystem.Comptabilite.Parametrage.service.SocieteService;
import jakarta.validation.Valid;
import java.io.ByteArrayOutputStream;
import static java.lang.StrictMath.log;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import static org.codehaus.groovy.ast.tools.GeneralUtils.params;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.format.annotation.DateTimeFormat;
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
import static org.springframework.web.servlet.function.RequestPredicates.headers;

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
    public ResponseEntity<Collection<FactureFournisseurDTO>> getFactureFournisseurByCodeCaisse(@RequestParam Integer codeFournisseur, @RequestParam Integer codeDevise, @RequestParam Boolean hasOrdrePaiement) {
        Collection<FactureFournisseurDTO> dTOs = factureFournisseurService.findByCodeFournisseurAndCodeDeviseAndNotPaid(codeFournisseur, codeDevise, hasOrdrePaiement);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("facture_fournisseur/factureForPaied")
    public ResponseEntity<Collection<FactureFournisseurDTO>> getFactureFournisseurForPaid(@RequestParam Integer codeFournisseur, @RequestParam Integer codeDevise, @RequestParam Boolean hasOrdrePaiement, @RequestParam Integer codeEtatApprouver) {
        Collection<FactureFournisseurDTO> dTOs = factureFournisseurService.findByCodeFournisseurAndCodeDeviseAndNotPaidAndApprouved(codeFournisseur, codeDevise, hasOrdrePaiement, codeEtatApprouver);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("facture_fournisseur/neww/{HasOrdrePaiement}")
    public ResponseEntity<Collection<FactureFournisseurDTO>> getFactureFournisseurNew(
            @PathVariable Boolean HasOrdrePaiement,
            @RequestParam Date DateDebut,
            @RequestParam Date DateFin
    ) {

        Collection<FactureFournisseurDTO> rslt = factureFournisseurService.findOneCollection(HasOrdrePaiement, DateDebut, DateFin);
        return ResponseEntity.ok().body(rslt);
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

    @GetMapping("facture_fournisseur/LazyEtatApprouver/{codeEtatApprouver}")
    public ResponseEntity<List<FactureFournisseurDTO>> getAppelOffreByCodeEtatApprouveLazy(@PathVariable Integer codeEtatApprouver) {
        List<FactureFournisseurDTO> dto = factureFournisseurService.findByEtatApprouverLazy(codeEtatApprouver);
        return ResponseEntity.ok().body(dto);

    }

    @PostMapping("facture_fournisseur")
    public ResponseEntity<FactureFournisseurDTO> postFactureFournisseur(@Valid @RequestBody FactureFournisseurDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FactureFournisseurDTO result = factureFournisseurService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

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

    @GetMapping("facture_fournisseur/edition")
    public ResponseEntity<byte[]> getReport(
            @RequestParam Boolean Paid,
            @RequestParam Date dateDebut,
            @RequestParam Date dateFin
    ) throws Exception {
        String fileNameJrxml = "src/main/resources/Reports/ListFactureFournisseur.jrxml";
        paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
        Collection<FactureFournisseurDTO> rslt = factureFournisseurService.findOneCollection(Paid, dateDebut, dateFin);

        SocieteDTO societeDTO = societeService.findOne(1);
        JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userName = "Anonymous";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() != null) {
            userName = auth.getName();
        }

        Map<String, Object> params = new HashMap<>();
        params.put("ItemDataSource", new JRBeanCollectionDataSource(rslt));
        params.put("societe", dTOs.getValeur());
        params.put("logo", societeDTO.getLogo());

        if (rslt.iterator().next().getHasOrdrePaiement() == true) {
            params.put("hasOP", "المسددة");
        } else {
            params.put("hasOP", "غير المسددة");
        }

        params.put("UserCreate", userName);
        params.put("DateDebut", dateDebut);
        params.put("DateFin", dateFin);

        params.put("codeSaisie", rslt.iterator().next().getCodeSaisie());
        params.put("designationArDevise", rslt.iterator().next().getDeviseDTO().getDesignationAr());
        params.put("dateFactureFournisseur", rslt.iterator().next().getDateFactureFournisseur());
        params.put("numFactureFournisseur", rslt.iterator().next().getNumFactureFournisseur());
        params.put("userCreate", rslt.iterator().next().getUserCreate());
        params.put("montant", rslt.iterator().next().getMontant());
        params.put("dateCreate", rslt.iterator().next().getDateCreate());

        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);
        exporter.exportReport();
        var res = pdfOutputStream.toByteArray();
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename= filename.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(res);
    }
//
//    @GetMapping("facture_fournisseur/editions")
//    public ResponseEntity<?> getReports( // Return ResponseEntity<?> for more flexibility
//            @RequestParam Date dateDebut,
//            @RequestParam Date dateFin,
//            @RequestParam Boolean hasOrdrePaiement // Consistent naming
//    ) {
//        try {
//            // ... (your existing code to get userName, dTOs, societeDTO)
//            String fileNameJrxml = "src/main/resources/Reports/ListFactureFournisseur.jrxml";
//            paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
//            Collection<FactureFournisseurDTO> rslt = factureFournisseurService.findOneCollection(dateDebut, dateFin, hasOrdrePaiement);
//
//            SocieteDTO societeDTO = societeService.findOne(1);
//            JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
////        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
////            Collection<FactureFournisseurDTO> rslt = factureFournisseurService.findOneCollection(dateDebut, dateFin, hasOrdrePaiement);
//            if (rslt.isEmpty()) {
//                return ResponseEntity.noContent().build(); // Handle empty result set
//            }
//
//            String userName = "Anonymous";
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            if (auth != null && auth.getPrincipal() != null) {
//                userName = auth.getName();
//            }
//
//            Map<String, Object> params = new HashMap<>();
//            params.put("ItemDataSource", new JRBeanCollectionDataSource(rslt));
//            params.put("societe", dTOs.getValeur());
//            params.put("logo", societeDTO.getLogo());
//
//            params.put("UserCreate", userName);
//            params.put("dateDebut", "01/01/2024");
//            params.put("dateFin", "01/05/2024");
//
//            params.put("codeSaisie", "lllll");
//
//            params.put("designationArDevise", "lllll");
//            params.put("dateFactureFrs", "lllll");
//            params.put("numFactureFrs", "lllll");
//            params.put("userCreate", "lllll");
//            params.put("montant", "lllll");
//
//            JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
//            JRPdfExporter exporter = new JRPdfExporter();
//            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//            exporter.setExporterInput(new SimpleExporterInput(print));
//            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
//            SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
//            reportConfig.setSizePageToContent(true);
//            reportConfig.setForceLineBreakPolicy(false);
//            exporter.exportReport();
//            var res = pdfOutputStream.toByteArray();
//            var headers = new HttpHeaders();
//            headers.add("Content-Disposition", "inline; filename= filename.pdf");
//
//            return ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .body(res);
//
//        } catch (Exception e) {
////            log.error("Error generating report", e); // Proper logging
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error generating report: " + e.getMessage()); // Informative error response
//        }
//    }
}
