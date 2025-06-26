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
// CLIENTI
// ===========================
@Entity
@Table(name = "MDMCLIE_TAB", schema = "MDMDBAE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MdmclieTab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "COGNOME")
    private String cognome;

    @Column(name = "P_IVA")
    private String partitaIva;

    @Column(name = "RAGIONE_SOCIALE", nullable = false)
    private String ragioneSociale;

    @Column(name = "TARIFFA_ORARIA")
    private BigDecimal tariffaOraria;

    @Column(name = "DATA_INSERIMENTO")
    private LocalDateTime dataInserimento;
}