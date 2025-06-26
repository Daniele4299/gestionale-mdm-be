package com.db.gestionale.mdm.be.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
// TARIFFE
// ===========================
@Entity
@Table(name = "MDMTARI_TAB", schema = "MDMDBAE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MdmtariTab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TARIFFA")
    private Long idTariffa;

    @Column(name = "CODICE", nullable = false, unique = true)
    private String codice;

    @Column(name = "DESCRIZIONE", nullable = false)
    private String descrizione;

    @Column(name = "IMPORTO", nullable = false)
    private BigDecimal importo;

    @Column(name = "ATTIVA")
    private Boolean attiva;

    @Column(name = "DATA_CREAZIONE")
    private LocalDateTime dataCreazione;
}