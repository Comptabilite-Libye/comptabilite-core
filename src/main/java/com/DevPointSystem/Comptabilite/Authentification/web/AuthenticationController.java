/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Authentification.web;

import com.DevPointSystem.Comptabilite.Authentification.domaine.User;
import com.DevPointSystem.Comptabilite.Authentification.dto.LoginResponse;
import com.DevPointSystem.Comptabilite.Authentification.dto.LoginUserDto;
import com.DevPointSystem.Comptabilite.Authentification.dto.RegisterUserDto;
import com.DevPointSystem.Comptabilite.Authentification.service.AuthenticationService;
import com.DevPointSystem.Comptabilite.Authentification.service.JwtService;
import com.DevPointSystem.Comptabilite.Authentification.service.UserDetailsImpl;
import com.DevPointSystem.Comptabilite.Authentification.web.Response.MessageResponse;
import java.util.List;
/**
 *
 * @author Administrator
 */

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
//    refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }
    
    
       @GetMapping("/authen")
    public ResponseEntity<List<User>> authentification(@RequestParam("user") String login, @RequestParam("pass") String password) {
        List<User> dd = authenticationService.findone(login, password);
        return ResponseEntity.ok().body(dd);

    }

}
