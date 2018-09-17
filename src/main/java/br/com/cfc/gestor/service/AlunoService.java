package br.com.cfc.gestor.service;

import br.com.cfc.gestor.model.Aluno;

public interface AlunoService {

	Iterable<Aluno> findAll();
	
	void save(Aluno aluno);
	
	Aluno get(Long id);

	void delete(Aluno aluno);

}
