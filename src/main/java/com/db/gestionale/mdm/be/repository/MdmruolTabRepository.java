package com.db.gestionale.mdm.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.gestionale.mdm.be.entity.MdmruolTab;

public interface MdmruolTabRepository extends JpaRepository<MdmruolTab, Long> {
	List<MdmruolTab> findByCodiceRuoloNot(String codiceRuolo);
}
