package com.db.gestionale.mdm.be.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MDMUTEN_TAB", schema = "MDMDBAE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MdmutenTab implements UserDetails {
    
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UTENTE")
    private Integer idUtente;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String utenUsername;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String utenPasswordHash;

    @Column(name = "NOME", nullable = false)
    private String utenNome;

    @Column(name = "COGNOME", nullable = false)
    private String utenCognome;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String utenEmail;

    @ManyToOne
    @JoinColumn(name = "ID_RUOLO", nullable = false)
    private MdmruolTab utenRuolo;

    @Column(name = "ATTIVO")
    private Boolean utenAttivo;

    @Column(name = "DATA_CREAZIONE")
    private LocalDateTime utenDataCreazione;

    // Implementazione metodi UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(utenRuolo.getCodiceRuolo()));
    }

    @Override
    public String getPassword() {
        return utenPasswordHash;
    }

    @Override
    public String getUsername() {
        return utenUsername;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(utenAttivo);
    }
}
