package com.db.gestionale.mdm.be.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.gestionale.mdm.be.dto.LoginRequest;
import com.db.gestionale.mdm.be.repository.MdmutenTabRepository;
import com.db.gestionale.mdm.be.security.jwt.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

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
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());
        var user = repository.findByUtenUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        if (!Boolean.TRUE.equals(user.getUtenAttivo())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Utenza disabilitata"));
        }

        authManager.authenticate(auth);
        String token = jwtService.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUtenUsername())
                        .password(user.getUtenPassword())
                        .authorities(user.getAuthorities())
                        .build()
        );

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1 ora
        response.addCookie(cookie);

        return ResponseEntity.ok().build(); // NON restituiamo più il token
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Cancella subito
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            var user = repository.findByUtenUsername(userDetails.getUsername()).orElseThrow();

            var userInfo = Map.of(
                "username", user.getUtenUsername(),
                "nome", user.getUtenNome(),
                "cognome", user.getUtenCognome(),
                "email", user.getUtenEmail(),
                "ruolo", user.getUtenRuolo().getCodiceRuolo()
            );

            return ResponseEntity.ok(userInfo);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
