package com.db.gestionale.mdm.be.entity;

import java.time.LocalDateTime;

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
// BUSTE PAGA
// ===========================
@Entity
@Table(name = "MDMBUST_TAB", schema = "MDMDBAE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MdmbustTab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BUSTA")
    private Long idBusta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UTENTE", nullable = false)
    private MdmutenTab utente;

    @Column(name = "ANNO", nullable = false)
    private Integer anno;

    @Column(name = "MESE", nullable = false)
    private Integer mese;

    @Column(name = "FILE_BUSTA", nullable = false)
    private byte[] fileBusta;

    @Column(name = "FILE_BUSTA_FILENAME")
    private String fileBustaFilename;

    @Column(name = "DATA_CARICAMENTO")
    private LocalDateTime dataCaricamento;
}