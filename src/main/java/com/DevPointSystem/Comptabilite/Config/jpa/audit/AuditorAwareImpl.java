///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.DevPointSystem.Comptabilite.Config.jpa.audit;
//
//import java.util.Optional;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
///**
// *
// * @author Admin
// */
//public class AuditorAwareImpl implements AuditorAware<String> {
//
////    @Override
////    public Optional<String> getCurrentAuditor() {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
////    @Override
////    public Optional<String> getCurrentAuditor() {
////        return SecurityContextHolder.getContext().getAuthentication().getName();
////    }
////    @Override
////    public Optional<String> getCurrentAuditor() {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        if (authentication != null && authentication.isAuthenticated()) {
////            return Optional.of(authentication.getName());
////        }
////        return Optional.empty();
////    }
//    
//    @Override
//  public Optional<String> getCurrentAuditor() {
//
//    return Optional.ofNullable(SecurityContextHolder.getContext())
//            .map(SecurityContext::getAuthentication)
//            .filter(Authentication::isAuthenticated)
//            .map(Authentication::getPrincipal)
//            .map(String.class::cast);
//  }
//}
