package com.db.gestionale.mdm.be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ===========================
// RUOLI
// ===========================
@Entity
@Table(name = "MDMRUOL_TAB", schema = "MDMDBAE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MdmruolTab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RUOLO")
    private Long idRuolo;

    @Column(name = "CODICE_RUOLO", nullable = false, unique = true)
    private String codiceRuolo;

    @Column(name = "DESCRIZIONE", nullable = false)
    private String descrizione;
}













