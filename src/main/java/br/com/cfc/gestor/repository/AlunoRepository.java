package br.com.cfc.gestor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long>{

	@Query("select a from Aluno a where concat(trim(lower(nome)), '|', trim(cpf), '|', aluno_id, '/', year(cadastrado_em)) like ?1")
	List<Aluno> fullSearch(String search);

}
