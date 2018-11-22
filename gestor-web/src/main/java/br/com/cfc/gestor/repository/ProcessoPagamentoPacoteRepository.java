package br.com.cfc.gestor.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.ProcessoPagamentoPacote;

@Repository
public interface ProcessoPagamentoPacoteRepository extends CrudRepository<ProcessoPagamentoPacote, Long>{

	@Query("select ppp from ProcessoPagamentoPacote ppp where ppp.processo = ?1")
	Iterable<ProcessoPagamentoPacote> findByProcesso(Processo processoId);

}
