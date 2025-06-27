package com.db.gestionale.mdm.be.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.gestionale.mdm.be.entity.MdmutenTab;
import com.db.gestionale.mdm.be.service.MdmutenTabService;

@RestController
@RequestMapping("/api/utenti")
public class MdmutenTabController {

	@Autowired
	MdmutenTabService service;

	@GetMapping
	public List<MdmutenTab> listAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<MdmutenTab> getById(@PathVariable Integer id) {
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody MdmutenTab user) {
		try {
			MdmutenTab created = service.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MdmutenTab user) {
	    try {
	        Optional<MdmutenTab> existingUser = service.findById(id);
	        if (existingUser.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato");
	        }

	        MdmutenTab existing = existingUser.get();

	        // Se SYS, impedisci la disattivazione
	        if ("SYS".equals(existing.getUtenRuolo().getCodiceRuolo()) && !user.getUtenAttivo()) {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Impossibile disattivare utenze SYS");
	        }

	        // Se utente SYS, impedisci la modifica del ruolo
	        if ("SYS".equals(existing.getUtenRuolo().getCodiceRuolo())) {
	            if (user.getUtenRuolo() != null 
	                && !user.getUtenRuolo().getCodiceRuolo().equals(existing.getUtenRuolo().getCodiceRuolo())) {
	                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Impossibile modificare il ruolo di utenze SYS");
	            }
	        }

	        Optional<MdmutenTab> updated = service.update(id, user);
	        if (updated.isPresent()) {
	            return ResponseEntity.ok(updated.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'aggiornamento");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {

			Optional<MdmutenTab> utente = service.findById(id);

			if (utente.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato");
			}

			if ("SYS".equals(utente.get().getUtenRuolo().getCodiceRuolo())) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Impossibile eliminare utenti SYS");
			}

			service.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
