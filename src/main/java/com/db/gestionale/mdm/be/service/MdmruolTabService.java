package com.db.gestionale.mdm.be.service;

import java.util.List;

import com.db.gestionale.mdm.be.entity.MdmruolTab;

public interface MdmruolTabService {
	List<MdmruolTab> findAll();
	List<MdmruolTab> findAllExcludeSys();
}
