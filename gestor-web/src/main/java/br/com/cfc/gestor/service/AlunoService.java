package br.com.cfc.gestor.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cfc.gestor.model.Aluno;

public interface AlunoService {

	Iterable<Aluno> findAll();
	
	Page<Aluno> findPaginated(Optional<String> findBy, Pageable pageable);
	
	void save(Aluno aluno);
	
	Aluno get(Long id);

	void delete(Aluno aluno);

}
