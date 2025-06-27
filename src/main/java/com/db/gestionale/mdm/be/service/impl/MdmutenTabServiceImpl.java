package com.db.gestionale.mdm.be.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.db.gestionale.mdm.be.entity.MdmutenTab;
import com.db.gestionale.mdm.be.repository.MdmutenTabRepository;
import com.db.gestionale.mdm.be.service.MdmutenTabService;

@Service
public class MdmutenTabServiceImpl implements MdmutenTabService {
	
	@Autowired
	MdmutenTabRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<MdmutenTab> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<MdmutenTab> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public MdmutenTab save(MdmutenTab user) {
		// Esempio: encode password
		user.setUtenPassword(passwordEncoder.encode(user.getUtenPassword()));
		user.setIdUtente(null);
		return repository.save(user);
	}

	@Override
	public Optional<MdmutenTab> update(Integer id, MdmutenTab user) {
		return repository.findById(id).map(existing -> {
			existing.setUtenUsername(user.getUsername());
			existing.setUtenNome(user.getUtenNome());
			existing.setUtenCognome(user.getUtenCognome());
			existing.setUtenEmail(user.getUtenEmail());
			existing.setUtenRuolo(user.getUtenRuolo());
			existing.setUtenAttivo(user.getUtenAttivo());
			// Se password non vuota la aggiorna codificata
			if (user.getUtenPassword() != null && !user.getUtenPassword().isEmpty()) {
				existing.setUtenPassword(passwordEncoder.encode(user.getUtenPassword()));
			}
			return repository.save(existing);
		});
	}

	@Override
	public boolean deleteById(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
