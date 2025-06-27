package com.db.gestionale.mdm.be.service;

import java.util.List;
import java.util.Optional;

import com.db.gestionale.mdm.be.entity.MdmutenTab;

public interface MdmutenTabService {
	List<MdmutenTab> findAll();

	Optional<MdmutenTab> findById(Integer id);

	MdmutenTab save(MdmutenTab user);

	Optional<MdmutenTab> update(Integer id, MdmutenTab user);

	boolean deleteById(Integer id);
}
