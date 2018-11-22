package br.com.cfc.gestor.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Contrato;
import br.com.cfc.gestor.repository.ContratoRepository;

@Service
public class ContratoServiceImpl implements ContratoService{

	@Resource
	private ContratoRepository contratoRepository;
	
	@Override
	public Iterable<Contrato> findAll() {
		return contratoRepository.findAll();
	}

	@Override
	public Optional<Contrato> get(Long id) {
		return contratoRepository.findById(id);
	}

	@Override
	public void save(Contrato contrato) {
		contratoRepository.save(contrato);
	}

}
