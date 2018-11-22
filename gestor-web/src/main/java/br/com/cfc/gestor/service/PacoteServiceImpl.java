package br.com.cfc.gestor.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Pacote;
import br.com.cfc.gestor.repository.PacoteRepository;

@Service
public class PacoteServiceImpl implements PacoteService{

	@Resource
	private PacoteRepository pacoteRepository;

	@Override
	public Iterable<Pacote> findAll() {
		return pacoteRepository.findAll();
	}

	@Override
	public void save(Pacote pacote) {
		pacoteRepository.save(pacote);
	}

	@Override
	public Pacote get(Long id) {
		return pacoteRepository.findById(id).orElse(null);
	}
}
