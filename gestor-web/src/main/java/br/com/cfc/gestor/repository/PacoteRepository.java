package br.com.cfc.gestor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Pacote;

@Repository
public interface PacoteRepository extends CrudRepository<Pacote, Long>{

}
