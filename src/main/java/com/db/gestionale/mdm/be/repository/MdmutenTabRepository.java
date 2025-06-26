package com.db.gestionale.mdm.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.gestionale.mdm.be.entity.MdmutenTab;

public interface MdmutenTabRepository extends JpaRepository<MdmutenTab, Integer> {
	Optional<MdmutenTab> findByUtenUsername(String username);

}
