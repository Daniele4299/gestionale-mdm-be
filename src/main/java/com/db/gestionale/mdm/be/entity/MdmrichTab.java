package com.db.gestionale.mdm.be.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
// RICHIESTE FERIE / PERMESSI
// ===========================
@Entity
@Table(name = "MDMRICH_TAB", schema = "MDMDBAE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MdmrichTab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RICHIESTA")
    private Long idRichiesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UTENTE", nullable = false)
    private MdmutenTab utente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO", nullable = false)
    private MdmtipoTab tipo;

    @Column(name = "DATA_DA", nullable = false)
    private LocalDate dataDa;

    @Column(name = "DATA_A", nullable = false)
    private LocalDate dataA;

    @Column(name = "ORA_DA")
    private LocalTime oraDa;

    @Column(name = "ORA_A")
    private LocalTime oraA;

    @Column(name = "CERTIFICATO")
    private byte[] certificato;

    @Column(name = "CERTIFICATO_FILENAME")
    private String certificatoFilename;

    @Column(name = "ACCETTATA")
    private Boolean accettata;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "DATA_INSERIMENTO")
    private LocalDateTime dataInserimento;
}