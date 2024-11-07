///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.DevPointSystem.Comptabilite.Authentification.Config.Exp;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// *
// * @author Administrator
// */
//@Component
//public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
//
//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        if (ex instanceof MissingServletRequestParameterException) {
//            try {
//                String parameterName = ((MissingServletRequestParameterException) ex).getParameterName();
//                String errorMessage = String.format("Required parameter '%s' is missing.", parameterName);
//
//                response.setStatus(HttpStatus.BAD_REQUEST.value());
//                response.setContentType("application/json"); // Or other content type
//                response.getWriter().write(errorMessage);
//                return new ModelAndView(); // Prevent further processing
//            } catch (IOException e) {
//                // Handle IO exception
//            }
//        }
//        return null; // Let other resolvers handle the exception
//    }
//    
//    
//}
