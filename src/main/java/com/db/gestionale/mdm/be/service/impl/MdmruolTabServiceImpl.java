package com.db.gestionale.mdm.be.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.gestionale.mdm.be.entity.MdmruolTab;
import com.db.gestionale.mdm.be.repository.MdmruolTabRepository;
import com.db.gestionale.mdm.be.service.MdmruolTabService;

@Service
public class MdmruolTabServiceImpl implements MdmruolTabService {
	
	@Autowired
	MdmruolTabRepository repository;

	@Override
	public List<MdmruolTab> findAll() {
		return repository.findAll();
	}

    public List<MdmruolTab> findAllExcludeSys() {
        return repository.findByCodiceRuoloNot("SYS");
    }
}
