package br.com.cfc.gestor.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.repository.VeiculoRepository;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	@Resource
	private VeiculoRepository veiculoRepositiry;
	
	@Override
	public Iterable<Veiculo> findAll() {
		return veiculoRepositiry.findAll();
	}

}
