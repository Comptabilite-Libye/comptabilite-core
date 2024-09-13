/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Authentification.service;

import com.DevPointSystem.Comptabilite.Authentification.domaine.User;
import com.DevPointSystem.Comptabilite.Authentification.dto.AccessUserDTO;
import com.DevPointSystem.Comptabilite.Authentification.factory.AccessUserFactory;
import com.DevPointSystem.Comptabilite.Authentification.repository.AccessUserRepo;
import com.DevPointSystem.Comptabilite.web.Util.RestPreconditions;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class AccessUserService {

    private final AccessUserRepo accessUserRepo;
    private final PasswordEncoder passwordEncoder;


    public AccessUserService(AccessUserRepo accessUserRepo, PasswordEncoder passwordEncoder) {
        this.accessUserRepo = accessUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

   
    public User findByUsername(String username) {
        return accessUserRepo.findUserByUserName(username); // Assuming you have a 'findByUsername' method in your UserRepository
    }
    @Transactional(readOnly = true)
    public List<AccessUserDTO> findAllAcessUser() {
        return AccessUserFactory.societeToSocieteDTOs(accessUserRepo.findAll());

    }

    @Transactional(readOnly = true)
    public List<AccessUserDTO> findAllAcessUserWithOutPassword() {
        return AccessUserFactory.societeToSocieteDTOsWithOutPassword(accessUserRepo.findAll());

    }
//

    @Transactional(readOnly = true)
    public AccessUserDTO findOne(String username) {
        User domaine = accessUserRepo.findUserByUserName(username);
        RestPreconditions.checkFound(domaine, "UserName.NotFound");
        AccessUserDTO dto = AccessUserFactory.accessUserToAccessUserDTO(domaine, false);
        return dto;
    }

    @Transactional(readOnly = true)
    public AccessUserDTO findOneByCode(Integer id) {
        User domaine = accessUserRepo.getReferenceById(id);
        RestPreconditions.checkFound(domaine, "UserName.NotFound");
        AccessUserDTO dto = AccessUserFactory.accessUserToAccessUserDTO(domaine, false);
        return dto;
    }

    @Transactional(readOnly = true)
    public AccessUserDTO findOneWithOutLogo(Integer id, Boolean withoutLogo) {
        User societe = accessUserRepo.getReferenceById(id);
        RestPreconditions.checkFound(societe, "UserName.NotFound");
        AccessUserDTO dto = AccessUserFactory.accessUserToAccessUserDTO(societe, withoutLogo);
        return dto;
    }

    public void deleteUSerName(Integer code) {
        accessUserRepo.deleteById(code);
    }

    public AccessUserDTO saves(AccessUserDTO dTO) {
        User domaine = AccessUserFactory.accessUserDTOToAccessUserx(dTO, new User());
        domaine.setPassword(passwordEncoder.encode(dTO.getPassword()));
        domaine = accessUserRepo.save(domaine);
        return AccessUserFactory.accessUserToAccessUserDTOx(domaine);
    }

    public AccessUserDTO update(AccessUserDTO dTO) throws IOException {
        User inBase = accessUserRepo.getReferenceById(dTO.getId());
        Preconditions.checkArgument(inBase != null, "UserName does not exist");
        AccessUserDTO result = saves(dTO);
        return result;
    }

}
