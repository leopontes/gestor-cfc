package br.com.cfc.gestor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Instrutor;

@Repository
public interface InstrutorRepository extends CrudRepository<Instrutor, Long>{

}
