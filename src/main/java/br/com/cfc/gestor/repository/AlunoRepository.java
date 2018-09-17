package br.com.cfc.gestor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long>{

}
