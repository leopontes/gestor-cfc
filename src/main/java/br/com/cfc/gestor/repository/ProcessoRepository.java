package br.com.cfc.gestor.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.Processo;

@Repository
public interface ProcessoRepository extends CrudRepository<Processo, Long>{
	
	public Collection<Processo> findByAluno(Aluno aluno);

	@Query("select p from Processo p where p.aluno=:aluno and p.dataTermino is null")
	public Processo getVigente(@Param("aluno") Aluno aluno);

}
