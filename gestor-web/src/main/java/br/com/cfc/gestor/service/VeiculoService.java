package br.com.cfc.gestor.service;

import br.com.cfc.gestor.model.Veiculo;

public interface VeiculoService {

	Iterable<Veiculo> findAll();

	Veiculo get(Long veiculo);

	void save(Veiculo bean);

	void delete(Veiculo veiculo);

}
