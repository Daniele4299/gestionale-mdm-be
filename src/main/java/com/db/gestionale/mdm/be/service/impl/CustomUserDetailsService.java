package com.db.gestionale.mdm.be.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.db.gestionale.mdm.be.entity.MdmutenTab;
import com.db.gestionale.mdm.be.repository.MdmutenTabRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MdmutenTabRepository repository;

    public CustomUserDetailsService(MdmutenTabRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MdmutenTab user = repository.findByUtenUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
        return new User(user.getUtenUsername(), user.getUtenPasswordHash(), user.getAuthorities());
    }
}
