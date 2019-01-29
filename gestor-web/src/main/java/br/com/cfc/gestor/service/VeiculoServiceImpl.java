package br.com.cfc.gestor.service;

import java.util.Optional;

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

	@Override
	public Veiculo get(Long veiculoId) {
		
		Optional<Veiculo> veiculo = veiculoRepositiry.findById(veiculoId);
		
		if(veiculo.isPresent()) {
			return veiculo.get();
		}
		
		return null;
	}

	@Override
	public void save(Veiculo veiculo) {
		veiculoRepositiry.save(veiculo);
	}

	@Override
	public void delete(Veiculo veiculo) {
		veiculoRepositiry.delete(veiculo);
	}

}
