/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Authentification.service;

/**
 *
 * @author Administrator
 */
import com.DevPointSystem.Comptabilite.Authentification.Config.JwtTokenUtil;
import com.DevPointSystem.Comptabilite.Authentification.domaine.User;
import com.DevPointSystem.Comptabilite.Authentification.dto.LoginResponse;
import com.DevPointSystem.Comptabilite.Authentification.dto.LoginUserDto;
import com.DevPointSystem.Comptabilite.Authentification.dto.RegisterUserDto;
import com.DevPointSystem.Comptabilite.Authentification.repository.UserRepository;
import com.DevPointSystem.Comptabilite.Authentification.web.Request.AuthenticationRequest;
import com.DevPointSystem.Comptabilite.Authentification.web.Response.AuthenticationResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtUtil;
    

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    public User signup(RegisterUserDto input) {
//        User user = new User()
//                .setFullName(input.getFullName())
//                .setUserName(input.getUserName())
//                .setEmail(input.getEmail())
//                .setPassword(passwordEncoder.encode(input.getPassword()));
//        user.setPasswordDecry(input.getPassword());
//
//        return userRepository.save(user);
//    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUserName(),
                        input.getPassword()
                )
        );

        return userRepository.findByUserName(input.getUserName())
                .orElseThrow();
    }

    
  
    
    
    public List<User> findone(String user, String passwd) {
        List<User> accesscont = userRepository.findByUserNameAndPassword(user, passwd);
        return accesscont;
    }

 

}
