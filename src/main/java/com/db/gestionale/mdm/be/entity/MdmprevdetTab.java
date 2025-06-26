package com.db.gestionale.mdm.be.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

// ===========================
// DETTAGLI PREVENTIVO
// ===========================
@Entity
@Table(name = "MDMPREVDET_TAB", schema = "MDMDBAE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MdmprevdetTab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETTAGLIO")
    private Long idDettaglio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PREVENTIVO", nullable = false)
    private MdmprevTab preventivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TARIFFA", nullable = false)
    private MdmtariTab tariffa;

    @Column(name = "QUANTITA")
    private Integer quantita;

    @Column(name = "PREZZO_UNITARIO", nullable = false)
    private BigDecimal prezzoUnitario;

    @Column(name = "DESCRIZIONE_AGGIUNTIVA")
    private String descrizioneAggiuntiva;
}