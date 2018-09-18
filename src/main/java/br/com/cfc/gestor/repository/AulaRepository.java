package br.com.cfc.gestor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Aula;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Long>{

}
