package br.com.cfc.gestor.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Despesa;
import br.com.cfc.gestor.repository.DespesaRepository;

@Service
public class DespesaServiceImpl implements DespesaService {

	@Resource
	private DespesaRepository despesaRepository;
	
	@Override
	public Iterable<Despesa> findAll() {
		return despesaRepository.findAll();
	}

	@Override
	public void save(Despesa despesa) {
		despesaRepository.save(despesa);
	}

	@Override
	public Despesa findById(Long id) {
		return despesaRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Despesa despesa) {
		despesaRepository.delete(despesa);
	}

}
