package br.com.cfc.gestor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Veiculo;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Long>{

}
