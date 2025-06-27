package com.db.gestionale.mdm.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.gestionale.mdm.be.entity.MdmruolTab;
import com.db.gestionale.mdm.be.service.MdmruolTabService;

@RestController
@RequestMapping("/api/ruoli")
public class MdmruolTabController {

	@Autowired
	private MdmruolTabService service;

	@GetMapping
	public List<MdmruolTab> listAllExcludeSys() {
	    return service.findAllExcludeSys();
	}
}
