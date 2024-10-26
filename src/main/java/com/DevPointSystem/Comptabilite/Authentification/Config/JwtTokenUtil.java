///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.DevPointSystem.Comptabilite.Authentification.Config;
//
//import com.DevPointSystem.Comptabilite.ComptabiliteCoreApplication;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import io.jsonwebtoken.security.SignatureException;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.function.Function;
//import org.springframework.http.ProblemDetail;
//import org.springframework.stereotype.Component;
//
//import org.springframework.http.HttpStatusCode;
//
//@Component
//public class JwtTokenUtil implements Serializable {
//
//    private static final long serialVersionUID = -2550185165626007488L;
//
////    //retrieve username from jwt token
////    public String getUsernameFromToken(String token) {
////        Claims claims = getAllClaims(token);
////        return (String) claims.get("unique_name");
////    }
////
////    //retrieve expiration date from jwt token
////    public Date getExpirationDateFromToken(String token) {
////        return getClaimFromToken(token, Claims::getExpiration);
////    }
////
////    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
////        final Claims claims = getAllClaimsFromToken(token);
////        return claimsResolver.apply(claims);
////    }
////
////    private Claims getAllClaims(String token) {
////        Claims claims;
////        try {
////            claims = Jwts.parser()
////                    .setSigningKey(ComptabiliteCoreApplication.jwtSecret.getBytes())
////                    .parseClaimsJws(token)
////                    .getBody();
////        } catch (Exception e) {
////            claims = null;
////        }
////        return claims;
////    }
////
////    //for retrieveing any information from token we will need the secret key
////    private Claims getAllClaimsFromToken(String token) {
////        return Jwts.parser().setSigningKey(ComptabiliteCoreApplication.jwtSecret.getBytes()).parseClaimsJws(token).getBody();
////    }
////
////    //check if the token has expired
////    private Boolean isTokenExpired(String token) {
////        final Date expiration = getExpirationDateFromToken(token);
////        return expiration.before(new Date());
////    }
////
////    //validate token
////    public Boolean validateToken(String token) {
////        return !isTokenExpired(token);
////    }
//    public Claims getClaimsFromToken(String token) {
//        ProblemDetail errorDetail = null;
//        try {
//             System.out.println("Token" + token);
//            return Jwts.parser()
//                    .setSigningKey(ComptabiliteCoreApplication.jwtSecret.getBytes())
//                    .parseClaimsJws(token)
//                    .getBody();
//             
//        } catch (SignatureException e) {
//
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
//            errorDetail.setProperty("description", "The JWT signature is invalid");
//        } catch (MalformedJwtException e) {
//            System.out.println("Token is invalid");
//        } catch (ExpiredJwtException e) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
//            errorDetail.setProperty("description", "Session Token has expired V1");
//        } catch (UnsupportedJwtException e) {
//            System.out.println("Token is not supported"); 
//        } catch (IllegalArgumentException e) {
//            // 
//            System.out.println("Token is null or empty");
//        }
//        return null;
//    }
//}
