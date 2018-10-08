package br.com.cfc.gestor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Secao;

@Repository
public interface SecaoRepository extends CrudRepository<Secao, Long>{

}
