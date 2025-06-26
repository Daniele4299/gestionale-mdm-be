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
// TIPOLOGIE RICHIESTE
// ===========================
@Entity
@Table(name = "MDMTIPO_TAB", schema = "MDMDBAE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MdmtipoTab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO")
    private Long idTipo;

    @Column(name = "CODICE_TIPO", nullable = false, unique = true)
    private String codiceTipo;

    @Column(name = "DESCRIZIONE", nullable = false)
    private String descrizione;
}