package br.com.cfc.gestor.repository;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.AulaProcessoVeiculo;

@Repository
public interface AulaProcessoVeiculoRepository extends CrudRepository<AulaProcessoVeiculo, Long>{

	@Query(value="select apv from AulaProcessoVeiculo apv where apv.veiculo.id = :codigoVeiculo and apv.data = :dataHora")
	Collection<AulaProcessoVeiculo> findByVeiculoData(Long codigoVeiculo, LocalDateTime dataHora);

}
