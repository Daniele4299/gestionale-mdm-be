package com.db.gestionale.mdm.be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.gestionale.mdm.be.dto.LoginRequest;
import com.db.gestionale.mdm.be.repository.MdmutenTabRepository;
import com.db.gestionale.mdm.be.security.jwt.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final MdmutenTabRepository repository;

    public AuthenticationController(AuthenticationManager authManager, JwtService jwtService,
                                    MdmutenTabRepository repository, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.repository = repository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());
        authManager.authenticate(auth);

        var user = repository.findByUtenUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUtenUsername())
                        .password(user.getUtenPasswordHash())
                        .authorities(user.getAuthorities())
                        .build()
        );

        return ResponseEntity.ok().body(token);
    }
}
