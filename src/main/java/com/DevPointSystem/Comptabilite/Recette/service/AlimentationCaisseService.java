/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Recette.service;

import com.DevPointSystem.Comptabilite.Parametrage.domaine.Compteur;
import com.DevPointSystem.Comptabilite.Parametrage.factory.CaisseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.DeviseFactory;
import com.DevPointSystem.Comptabilite.Parametrage.factory.ModeReglementFactory;
import com.DevPointSystem.Comptabilite.Parametrage.service.CompteurService;
import com.DevPointSystem.Comptabilite.Recette.domaine.AlimentationCaisse;
import com.DevPointSystem.Comptabilite.Recette.domaine.MouvementCaisse;
import com.DevPointSystem.Comptabilite.Recette.dto.AlimentationCaisseDTO;
import com.DevPointSystem.Comptabilite.Recette.factory.AlimentationCaisseFactory;
import com.DevPointSystem.Comptabilite.Recette.factory.MouvementCaisseFactory;
import com.DevPointSystem.Comptabilite.Recette.repository.AlimentationCaisseRepo;
import com.DevPointSystem.Comptabilite.Recette.repository.MouvementCaisseRepo;
import com.DevPointSystem.Comptabilite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class AlimentationCaisseService {

    private final AlimentationCaisseRepo alimentationCaisseRepo;

    private final CompteurService compteurService;

    private final MouvementCaisseRepo mouvementCaisseRepo;

    public AlimentationCaisseService(AlimentationCaisseRepo alimentationCaisseRepo, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo) {
        this.alimentationCaisseRepo = alimentationCaisseRepo;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
    }

    @Transactional(readOnly = true)
    public List<AlimentationCaisseDTO> findAllAlimentationCaisse() {
        return AlimentationCaisseFactory.listAlimentationCaisseToAlimentationCaisseDTOs(alimentationCaisseRepo.findAll());

    }

    @Transactional(readOnly = true)
    public AlimentationCaisseDTO findOne(Integer code) {
        AlimentationCaisse domaine = alimentationCaisseRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.AlimentationCaisseNotFound");
        return AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<AlimentationCaisseDTO> findByCodeCaisse(Collection<Integer> codeCaisse) {
        Collection<AlimentationCaisse> result = alimentationCaisseRepo.findByCodeCaisseIn(Helper.removeNullValueFromCollection(codeCaisse));
        return AlimentationCaisseFactory.CollectionalimentationCaissesToalimentationCaissesDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public Collection<AlimentationCaisseDTO> findByCodeDevise(Collection<Integer> codeDevise) {
        Collection<AlimentationCaisse> result = alimentationCaisseRepo.findByCodeDeviseIn(Helper.removeNullValueFromCollection(codeDevise));
        return AlimentationCaisseFactory.CollectionalimentationCaissesToalimentationCaissesDTOsCollection(result);
    }

    public AlimentationCaisseDTO save(AlimentationCaisseDTO dto) {
        AlimentationCaisse domaine = AlimentationCaisseFactory.alimentationCaisseDTOToAlimentationCaisse(dto, new AlimentationCaisse());

        MouvementCaisse mvtCaisse = new MouvementCaisse();

        mvtCaisse.setCode(dto.getCode());

        mvtCaisse.setCodeSaisie(dto.getCodeSaisie());
        mvtCaisse.setDebit(dto.getMontant());   
        mvtCaisse.setCredit(BigDecimal.ZERO);


        mvtCaisse.setDateCreate(dto.getDateCreate());
        mvtCaisse.setUserCreate(dto.getUserCreate());
        mvtCaisse.setMntDevise(dto.getMontant());
        mvtCaisse.setCodeTier("");

        mvtCaisse.setCodeCaisse(dto.getCodeCaisse());
        if (mvtCaisse.getCodeCaisse() != null) {
            mvtCaisse.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));

        }

        mvtCaisse.setCodeCaisseTr(0);

        mvtCaisse.setCodeDevise(dto.getCodeDevise());
        if (mvtCaisse.getCodeDevise() != null) {
            mvtCaisse.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));

        }

        mvtCaisse.setCodeModeReglement(dto.getCodeModeReglement());
        if (mvtCaisse.getCodeModeReglement() != null) {
            mvtCaisse.setModeReglement(ModeReglementFactory.createModeReglementByCode(dto.getCodeModeReglement()));

        }

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieAC");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = alimentationCaisseRepo.save(domaine);
        mvtCaisse = mouvementCaisseRepo.save(mvtCaisse);
        return AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(domaine);
    }

    public AlimentationCaisse update(AlimentationCaisseDTO dTO) {
        Preconditions.checkArgument((dTO.getCode() != null), "error.AlimentationCaisseNotFound");
        AlimentationCaisse domaine = alimentationCaisseRepo.getReferenceById(dTO.getCode());
        Preconditions.checkArgument(true, "error.AlimentationCaisseNotFound");
        dTO.setCode(domaine.getCode());
        AlimentationCaisseFactory.alimentationCaisseDTOToAlimentationCaisse(dTO, domaine);
        return alimentationCaisseRepo.save(domaine);
    }

    public void deleteAlimentationCaisse(Integer code) {
        Preconditions.checkArgument(alimentationCaisseRepo.existsById(code), "error.AlimentationCaisseNotFound");
        alimentationCaisseRepo.deleteById(code);
    }
}
