/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Depense.service;

import com.DevPointSystem.Comptabilite.Depense.domaine.AvanceFournisseur;
import com.DevPointSystem.Comptabilite.Depense.domaine.DetailsReglementFactureFrs;
import com.DevPointSystem.Comptabilite.Depense.domaine.FactureFournisseur;
import com.DevPointSystem.Comptabilite.Depense.domaine.ReglementFactureFrs;
import com.DevPointSystem.Comptabilite.Depense.dto.AvanceFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.dto.DetailsReglementFactureFrsDTO;
import com.DevPointSystem.Comptabilite.Depense.dto.FactureFournisseurDTO;
import com.DevPointSystem.Comptabilite.Depense.dto.ReglementFactureFrsDTO;
import com.DevPointSystem.Comptabilite.Depense.factory.AvanceFournisseurFactory;
import com.DevPointSystem.Comptabilite.Depense.factory.DetailsReglementFactureFournisseurFactory;
import com.DevPointSystem.Comptabilite.Depense.factory.ReglementFactureFournisseurFactory;
import com.DevPointSystem.Comptabilite.Depense.repository.AvanceFournisseurRepo;
import com.DevPointSystem.Comptabilite.Depense.repository.DetailsReglementFactureFrsRepo;
import com.DevPointSystem.Comptabilite.Depense.repository.ReglementFactureFrsRepo;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Caisse;
import com.DevPointSystem.Comptabilite.Parametrage.domaine.Compteur;
import com.DevPointSystem.Comptabilite.Parametrage.factory.BanqueFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CaisseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CostProfitCentreFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.DeviseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.EtatApprouverFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.FournisseurFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.ModeReglementFactory;
import com.DevPointSystem.Comptabilite.Parametrage.repository.CaisseRepo;
import com.DevPointSystem.Comptabilite.Parametrage.service.CompteurService;
import com.DevPointSystem.Comptabilite.Recette.domaine.MouvementCaisse;
import com.DevPointSystem.Comptabilite.Recette.domaine.SoldeCaisse;
import com.DevPointSystem.Comptabilite.Recette.dto.SoldeCaisseDTO;
import com.DevPointSystem.Comptabilite.Recette.repository.MouvementCaisseRepo;
import com.DevPointSystem.Comptabilite.Recette.repository.SoldeCaisseRepo;
import com.DevPointSystem.Comptabilite.Recette.service.SoldeCaisseService;
import com.DevPointSystem.Comptabilite.web.Util.Helper;
import com.DevPointSystem.Comptabilite.web.errors.IllegalBusinessLogiqueException;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.temporal.TemporalQueries.zone;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class ReglementFactureFournisseurService {

    private final ReglementFactureFrsRepo reglementFactureFrsRepo;

    private final CompteurService compteurService;

    private final MouvementCaisseRepo mouvementCaisseRepo;

    private final DetailsReglementFactureFrsRepo detailsReglementFactureFrsRepo;

    private final SoldeCaisseRepo soldeCaisseRepo;
    private final SoldeCaisseService soldeCaisseService;

    private final FactureFournisseurService factureFournisseurService;

    private final AvanceFournisseurRepo avanceFournisseurRepo;
    private final AvanceFournisseurService avanceFournisseurService;

    public ReglementFactureFournisseurService(ReglementFactureFrsRepo reglementFactureFrsRepo, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo, DetailsReglementFactureFrsRepo detailsReglementFactureFrsRepo, SoldeCaisseRepo soldeCaisseRepo, SoldeCaisseService soldeCaisseService, FactureFournisseurService factureFournisseurService, AvanceFournisseurRepo avanceFournisseurRepo, AvanceFournisseurService avanceFournisseurService) {
        this.reglementFactureFrsRepo = reglementFactureFrsRepo;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.detailsReglementFactureFrsRepo = detailsReglementFactureFrsRepo;
        this.soldeCaisseRepo = soldeCaisseRepo;
        this.soldeCaisseService = soldeCaisseService;
        this.factureFournisseurService = factureFournisseurService;
        this.avanceFournisseurRepo = avanceFournisseurRepo;
        this.avanceFournisseurService = avanceFournisseurService;
    }

    @Transactional(readOnly = true)
    public List<ReglementFactureFrsDTO> findAllReglementFactureFournisseur() {

        return ReglementFactureFournisseurFactory.listReglementFactureFournisseurToReglementFactureFournisseurDTOs(reglementFactureFrsRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public ReglementFactureFrsDTO findOne(Integer code) {
        ReglementFactureFrs domaine = reglementFactureFrsRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.FactureFournisseurNotFound");
        return ReglementFactureFournisseurFactory.reglementfactureFournisseurToReglementFactureFournisseurDTOUpdate(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<ReglementFactureFrsDTO> findByCodeFournisseur(Collection<Integer> codeFournisseur) {
        Collection<ReglementFactureFrs> result = reglementFactureFrsRepo.findByCodeFournisseurIn(Helper.removeNullValueFromCollection(codeFournisseur));
        return ReglementFactureFournisseurFactory.CollectionfactureFournisseursTofactureFournisseursDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public List<ReglementFactureFrsDTO> findByCodeFournisseurAndCodeDevise(Integer codeFournisseur, Integer codeDevise) {
        List<ReglementFactureFrs> result = reglementFactureFrsRepo.findByCodeFournisseurAndCodeDevise(codeFournisseur, codeDevise);
        return ReglementFactureFournisseurFactory.listReglementFactureFournisseurToReglementFactureFournisseurDTOs(result);
    }

    @Transactional(readOnly = true)
    public Collection<ReglementFactureFrsDTO> findByCodeDevise(Collection<Integer> codeDevise) {
        Collection<ReglementFactureFrs> result = reglementFactureFrsRepo.findByCodeDeviseIn(Helper.removeNullValueFromCollection(codeDevise));
        return ReglementFactureFournisseurFactory.CollectionfactureFournisseursTofactureFournisseursDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public List<ReglementFactureFrsDTO> findByEtatApprouver(Integer CodeEtatApprouver) {
        return ReglementFactureFournisseurFactory.listReglementFactureFournisseurToReglementFactureFournisseurDTOs(reglementFactureFrsRepo.findReglementFactureFrsByCodeEtatApprouver(CodeEtatApprouver));
    }

    @Transactional(readOnly = true)
    public List<ReglementFactureFrsDTO> findByEtatApprouverLazy(Integer CodeEtatApprouver) {
        return ReglementFactureFournisseurFactory.listReglementFactureFournisseurToReglementFactureFournisseurDTOsLazy(reglementFactureFrsRepo.findReglementFactureFrsByCodeEtatApprouver(CodeEtatApprouver));
    }

    public ReglementFactureFrsDTO save(ReglementFactureFrsDTO dto) {
        ReglementFactureFrs domaine = ReglementFactureFournisseurFactory.reglementfactureFournisseurDTOToReglementFactureFournisseur(new ReglementFactureFrs(), dto);

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieRF");
        Compteur CompteurCodeSaisieAV = compteurService.findOne("CodeSaisieAF");

        if ("OP".equals(dto.getTypeOP())) {
            FactureFournisseurDTO factureFournisseur = factureFournisseurService.findOne(dto.getCodeFactureFournisseur());
            factureFournisseurService.CreatedOrdrePaiement(factureFournisseur);
            String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();

            compteurService.incrementeSuffixe(CompteurCodeSaisie);
            domaine.setCodeSaisie(codeSaisieAC);
            domaine = reglementFactureFrsRepo.save(domaine);

        } else {
            String codeSaisieAC = CompteurCodeSaisieAV.getPrefixe() + CompteurCodeSaisieAV.getSuffixe();
            compteurService.incrementeSuffixe(CompteurCodeSaisieAV);
            domaine.setCodeSaisie(codeSaisieAC);
            AvanceFournisseur avanceFournisseur = new AvanceFournisseur();
            avanceFournisseur.setCodeSaisie(codeSaisieAC);
            avanceFournisseur.setApurer(Boolean.FALSE);
            avanceFournisseur.setDateCreate(dto.getDateCreate());
            avanceFournisseur.setUserCreate(dto.getUserCreate());
            avanceFournisseur.setMontant(dto.getMontant());
            avanceFournisseur.setObservation(dto.getObservation());
            avanceFournisseur.setCodeUserApprouver(dto.getCodeUserApprouver());
            avanceFournisseur.setDateApprouve(dto.getDateApprouve());
            avanceFournisseur.setNumPiece(dto.getNumPiece());
            avanceFournisseur.setPaid(Boolean.FALSE);
            avanceFournisseur.setTauxDevise(dto.getTauxDevise());
            avanceFournisseur.setMontantEnDevise(dto.getMontantEnDevise());
            avanceFournisseur.setCodeDevise(dto.getCodeDevise());
            if (avanceFournisseur.getCodeDevise() != null) {
                avanceFournisseur.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));
            }
            avanceFournisseur.setCodeCostProfitCentre(dto.getCodeCostProfitCentre());
            if (avanceFournisseur.getCodeCostProfitCentre() != null) {
                avanceFournisseur.setCostProfitCentre(CostProfitCentreFactory.createCostCentreByCode(dto.getCodeCostProfitCentre()));
            }
            avanceFournisseur.setCodeFournisseur(dto.getCodeFournisseur());
            if (avanceFournisseur.getCodeFournisseur() != null) {
                avanceFournisseur.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }
            avanceFournisseur.setCodeModeReglement(dto.getCodeModeReglement());
            if (avanceFournisseur.getCodeModeReglement() != null) {
                avanceFournisseur.setModeReglement(ModeReglementFactory.createModeReglementByCode(dto.getCodeModeReglement()));
            }
            avanceFournisseur.setCodeEtatApprouver(dto.getCodeEtatApprouver());
            if (avanceFournisseur.getCodeEtatApprouver() != null) {
                avanceFournisseur.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
            }
            avanceFournisseur.setCodeCaisse(dto.getCodeCaisse());
            if (avanceFournisseur.getCodeCaisse() != null) {
                avanceFournisseur.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));
            }
            avanceFournisseur.setCodeBanque(dto.getCodeBanque());
            if (avanceFournisseur.getCodeBanque() != null) {
                avanceFournisseur.setBanque(BanqueFactory.createBanqueByCode(dto.getCodeBanque()));
            }

            avanceFournisseurRepo.save(avanceFournisseur);
            domaine = reglementFactureFrsRepo.save(domaine);
//            FactureFournisseurDTO factureFournisseur = factureFournisseurService.findOne(dto.getCodeFactureFournisseur());
//            factureFournisseurService.CreatedOrdrePaiement(factureFournisseur);

        }

        return ReglementFactureFournisseurFactory.reglementfactureFournisseurToReglementFactureFournisseurDTO(domaine);
    }

    public ReglementFactureFrsDTO updateNewWithFlush(ReglementFactureFrsDTO dto) {
        ReglementFactureFrs inBase = reglementFactureFrsRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        detailsReglementFactureFrsRepo.deleteByCodeReglementFactureFournisseur(dto.getCode());
        System.out.println("flush deleted OK Code " + dto.getCode());

        inBase = ReglementFactureFournisseurFactory.reglementfactureFournisseurDTOToReglementFactureFournisseur(inBase, dto);
        inBase = reglementFactureFrsRepo.save(inBase);
        ReglementFactureFrsDTO resultDTO = ReglementFactureFournisseurFactory.reglementfactureFournisseurToReglementFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    public void deleteReglementFactureFournisseur(Integer code) {
        Preconditions.checkArgument(reglementFactureFrsRepo.existsById(code), "error.FactureFournisseurNotFound");

        ReglementFactureFrs inBase = reglementFactureFrsRepo.findByCode(code);
        Preconditions.checkArgument(inBase.getCodeUserApprouver() == null, "error.ReglementFactureApprouved");

        if (inBase.getTypeOP().equals("OP")) {
            FactureFournisseurDTO factureFournisseur = factureFournisseurService.findOne(inBase.getCodeFactureFournisseur());
            factureFournisseurService.DeleteOrdrePaiement(factureFournisseur);

        } else {
            AvanceFournisseurDTO avanceFournisseurDTO = avanceFournisseurService.findOneByCodeSaisie(inBase.getCodeSaisie());
            System.out.println("avanceFournisseurDTO  get code " + avanceFournisseurDTO.getCodeSaisie());
            avanceFournisseurService.deleteAvanceFournisseur(avanceFournisseurDTO.getCode());

        }
        reglementFactureFrsRepo.deleteById(code);
    }

    public ReglementFactureFrsDTO approuveRegFactFrs(ReglementFactureFrsDTO dto) {
        ReglementFactureFrs inBase = reglementFactureFrsRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");

        SoldeCaisse soldeCaisse = soldeCaisseRepo.findByCodeCaisse(dto.getCodeCaisse());

        BigDecimal soldDebit = soldeCaisse.getDebit();
        BigDecimal soldCredit = soldeCaisse.getCredit();

        BigDecimal soldeCaisseFinal = soldDebit.subtract(soldCredit);
        Boolean x = soldeCaisseFinal.compareTo(dto.getMontant()) > 0;

        inBase = ReglementFactureFournisseurFactory.ApprouveReglementFactureFournisseurDTOToReglementFactureFournisseur(inBase, dto);

        if (dto.getOldEtatApprouve() == 1 && dto.getCodeEtatApprouver() == 2 || dto.getOldEtatApprouve() == 3 && dto.getCodeEtatApprouver() == 2) {
            Preconditions.checkArgument(soldeCaisseFinal.compareTo(BigDecimal.ZERO) >= 0, "error.SoldeNegativeCaisse");
            Preconditions.checkArgument(soldeCaisseFinal.compareTo(dto.getMontant()) > 0, "error.MontantNotExisteInCaisse");
            MouvementCaisse mvtCaisse = new MouvementCaisse();
            if (dto.getCodeEtatApprouver() == 2) {
                mvtCaisse.setCodeSaisie(inBase.getCodeSaisie());
                mvtCaisse.setDebit(BigDecimal.ZERO);
                mvtCaisse.setCredit(inBase.getMontant());
                mvtCaisse.setMntDevise(inBase.getMontantEnDevise());
                mvtCaisse.setDateCreate(new Date());
                mvtCaisse.setUserCreate(inBase.getUserCreate());
                mvtCaisse.setCodeTier(inBase.getFournisseur().getCodeSaisie());
                mvtCaisse.setCodeCaisse(inBase.getCodeCaisse());
                if (mvtCaisse.getCodeCaisse() != null) {
                    mvtCaisse.setCaisse(CaisseFactory.createCaisseByCode(inBase.getCodeCaisse()));
                }

//            mvtCaisse.setCodeCaisseTr(0);
                mvtCaisse.setCodeDevise(inBase.getCodeDevise());
                if (mvtCaisse.getCodeDevise() != null) {
                    mvtCaisse.setDevise(DeviseFactory.createDeviseByCode(inBase.getCodeDevise()));

                }

                mvtCaisse.setCodeModeReglement(inBase.getCodeModeReglement());
                if (mvtCaisse.getCodeModeReglement() != null) {
                    mvtCaisse.setModeReglement(ModeReglementFactory.createModeReglementByCode(inBase.getCodeModeReglement()));

                }
                mvtCaisse = mouvementCaisseRepo.save(mvtCaisse);
            }

            SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisse());
            BigDecimal montCreditSoldeCaisse = soldeCaisseDTOs.getCredit();
            BigDecimal mntReglement = inBase.getMontant();
            BigDecimal soldFiniCaisse = montCreditSoldeCaisse.add(mntReglement);
            soldeCaisseDTOs.setCredit(soldFiniCaisse);
            soldeCaisseService.updateMontant(soldeCaisseDTOs);
            inBase = reglementFactureFrsRepo.save(inBase);

            if (inBase.getTypeOP().equals("OP")) {
                FactureFournisseurDTO factureFournisseur = factureFournisseurService.findOne(inBase.getCodeFactureFournisseur());
                factureFournisseurService.CreatedPaieOrdrePaiement(factureFournisseur);
            } else {
                AvanceFournisseurDTO avanceFournisseurDTO = avanceFournisseurService.findOneByCodeSaisie(inBase.getCodeSaisie());
                avanceFournisseurDTO.setCodeUserApprouver(dto.getCodeUserApprouver());
                avanceFournisseurDTO.setCodeEtatApprouver(dto.getCodeEtatApprouver());

                avanceFournisseurService.approuveAvanceFournisseur(avanceFournisseurDTO);
                System.out.println("changement  ");

            }

        } else if (dto.getOldEtatApprouve() == 2 && dto.getCodeEtatApprouver() == 3) {
            SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisse());
            BigDecimal mntOld = soldeCaisseDTOs.getCredit();
            BigDecimal mntNew = inBase.getMontant();
            BigDecimal sumMnt = mntOld.subtract(mntNew);
            soldeCaisseDTOs.setCredit(sumMnt);
            soldeCaisseService.updateMontant(soldeCaisseDTOs);

            mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());
            inBase = reglementFactureFrsRepo.save(inBase);

            if (inBase.getTypeOP().equals("OP")) {
                FactureFournisseurDTO factureFournisseur = factureFournisseurService.findOne(inBase.getCodeFactureFournisseur());
                factureFournisseurService.DeletePaieOrdrePaiement(factureFournisseur);
            } else {
                AvanceFournisseurDTO avanceFournisseurDTO = avanceFournisseurService.findOneByCodeSaisie(inBase.getCodeSaisie());
                avanceFournisseurDTO.setCodeUserApprouver(dto.getCodeUserApprouver());
                avanceFournisseurDTO.setCodeEtatApprouver(dto.getCodeEtatApprouver());
                avanceFournisseurService.CancelapprouveAvanceFournisseur(avanceFournisseurDTO);

            }

        } else {
            AvanceFournisseurDTO avanceFournisseurDTO = avanceFournisseurService.findOneByCodeSaisie(inBase.getCodeSaisie());
            avanceFournisseurDTO.setCodeUserApprouver(dto.getCodeUserApprouver());
            avanceFournisseurDTO.setCodeEtatApprouver(dto.getCodeEtatApprouver());
            avanceFournisseurService.approuveAvanceFournisseur(avanceFournisseurDTO);
            inBase = reglementFactureFrsRepo.save(inBase);
        }

//        AvanceFournisseurDTO avanceFournisseurDTO = avanceFournisseurService.findOneByCodeSaisie(inBase.getCodeSaisie());
//        avanceFournisseurService.approuveAvanceFournisseur(avanceFournisseurDTO);
        ReglementFactureFrsDTO resultDTO = ReglementFactureFournisseurFactory.reglementfactureFournisseurToReglementFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    public ReglementFactureFrsDTO CancelapprouveRegFactFrs(ReglementFactureFrsDTO dto) {
        ReglementFactureFrs inBase = reglementFactureFrsRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.FactureFournisseurNotFound");
        inBase = ReglementFactureFournisseurFactory.CancelReglementFactureFournisseurDTOToReglementFactureFournisseur(inBase, dto);

        if (dto.getOldEtatApprouve() == 1 && dto.getCodeEtatApprouver() == 1) {
            inBase = reglementFactureFrsRepo.save(inBase);
        } else {
            SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisse());
            BigDecimal mntOld = soldeCaisseDTOs.getCredit();
            BigDecimal mntNew = inBase.getMontant();
            BigDecimal sumMnt = mntOld.subtract(mntNew);
            soldeCaisseDTOs.setCredit(sumMnt);
            soldeCaisseService.updateMontant(soldeCaisseDTOs);

            mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());
            inBase = reglementFactureFrsRepo.save(inBase);
            if (inBase.getTypeOP().equals("OP")) {
                FactureFournisseurDTO factureFournisseur = factureFournisseurService.findOne(inBase.getCodeFactureFournisseur());
                factureFournisseurService.DeletePaieOrdrePaiement(factureFournisseur);
            } else {
                AvanceFournisseurDTO avanceFournisseurDTO = avanceFournisseurService.findOneByCodeSaisie(inBase.getCodeSaisie());
                avanceFournisseurDTO.setCodeUserApprouver(dto.getCodeUserApprouver());
                avanceFournisseurDTO.setCodeEtatApprouver(dto.getCodeEtatApprouver());
                avanceFournisseurService.CancelapprouveAvanceFournisseur(avanceFournisseurDTO);

            }
        }

        ReglementFactureFrsDTO resultDTO = ReglementFactureFournisseurFactory.reglementfactureFournisseurToReglementFactureFournisseurDTO(inBase);
        return resultDTO;
    }

    @Transactional(readOnly = true)
    public Collection<DetailsReglementFactureFrsDTO> findOneWithDetails(Integer code) {
        Collection<DetailsReglementFactureFrs> domaine = detailsReglementFactureFrsRepo.findByDetailsReglementFactureFrsPK_codeReglementFactureFournisseur(code);
        return DetailsReglementFactureFournisseurFactory.detailsFactureFournisseurTodetailsFactureFournisseurDTOCollections(domaine);
    }

}
