package br.com.cfc.gestor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Documento;

@Repository
public interface DocumentoRepository extends CrudRepository<Documento, Long>{

}
