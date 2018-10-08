package br.com.cfc.gestor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Clausula;
import br.com.cfc.gestor.model.Paragrafo;

@Repository
public interface ParagrafoRepository extends CrudRepository<Paragrafo, Long>{

	@Query("select prg from Paragrafo prg where prg.clausula = ?1")
	Iterable<Paragrafo> findClausula(Clausula clausula);

}
