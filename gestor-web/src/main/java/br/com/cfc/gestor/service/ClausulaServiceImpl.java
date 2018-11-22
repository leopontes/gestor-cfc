package br.com.cfc.gestor.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Clausula;
import br.com.cfc.gestor.model.Contrato;
import br.com.cfc.gestor.repository.ClausulaRepository;

@Service
public class ClausulaServiceImpl implements ClausulaService {

	@Resource
	private ClausulaRepository clausulaRepository;

	@Override
	public void save(Clausula clausula) {
		clausulaRepository.save(clausula);
	}

	@Override
	public Iterable<Clausula> findBy(Contrato contrato) {
		return clausulaRepository.findByContrato(contrato);
	}

	@Override
	public Optional<Clausula> get(Long idClausula) {
		return clausulaRepository.findById(idClausula);
	}
}
