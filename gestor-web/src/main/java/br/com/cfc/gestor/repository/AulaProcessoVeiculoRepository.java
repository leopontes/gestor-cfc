package br.com.cfc.gestor.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.AulaProcessoVeiculo;
import br.com.cfc.gestor.model.Instrutor;

@Repository
public interface AulaProcessoVeiculoRepository extends CrudRepository<AulaProcessoVeiculo, Long>{

	@Query(value="select apv from AulaProcessoVeiculo apv where apv.veiculo.id = ?1 and apv.data = ?2")
	AulaProcessoVeiculo findByVeiculoData(Long codigoVeiculo, LocalDateTime dataHora);

	@Query(value="select apv from AulaProcessoVeiculo apv where apv.instrutor = ?1 and apv.data = ?2")
	AulaProcessoVeiculo findByInstrutor(Instrutor instrutor, LocalDateTime dataAgendamento);

	@Query(value="select apv from AulaProcessoVeiculo apv where apv.processo.aluno.id = ?1 and apv.processo.dataTermino is null")
	Iterable<AulaProcessoVeiculo> findByAluno(Long alunoId);

}
