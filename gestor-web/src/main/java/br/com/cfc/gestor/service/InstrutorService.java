package br.com.cfc.gestor.service;

import br.com.cfc.gestor.model.Instrutor;

public interface InstrutorService {

	Iterable<Instrutor> findAll();

	Instrutor get(Long instrutorId);

	void save(Instrutor instrutor);

	void delete(Instrutor instrutor);

}
