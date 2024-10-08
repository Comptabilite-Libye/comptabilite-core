///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.DevPointSystem.Comptabilite.Authentification.Config;
//
//import com.DevPointSystem.Comptabilite.Authentification.service.UserDetailsImpl;
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
// 
//
//import io.jsonwebtoken.*;
//
//@Component
//
//
///**
// *
// * @author Administrator
// */
//public class JwtUtils {
//     private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//
//   
//
//  public String generateJwtToken(UserDetailsImpl userPrincipal) {
//    return generateTokenFromUsername(userPrincipal.getUsername());
//  }
//
//  public String generateTokenFromUsername(String username) {
//    return Jwts.builder().setSubject(username).setIssuedAt(new Date())
//        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
//        .compact();
//  }
//
//  public String getUserNameFromJwtToken(String token) {
//    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//  }
//
//  public boolean validateJwtToken(String authToken) {
//    try {
//      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//      return true;
//    } catch (SignatureException e) {
//      logger.error("Invalid JWT signature: {}", e.getMessage());
//    } catch (MalformedJwtException e) {
//      logger.error("Invalid JWT token: {}", e.getMessage());
//    } catch (ExpiredJwtException e) {
//      logger.error("JWT token is expired: {}", e.getMessage());
//    } catch (UnsupportedJwtException e) {
//      logger.error("JWT token is unsupported: {}", e.getMessage());
//    } catch (IllegalArgumentException e) {
//      logger.error("JWT claims string is empty: {}", e.getMessage());
//    }
//
//    return false;
//  }
//}
