package br.com.cfc.gestor.service;

import java.time.LocalDateTime;

import br.com.cfc.gestor.model.AulaProcessoVeiculo;

public interface AulaProcessoVeiculoService {

	AulaProcessoVeiculo find(Long codigoVeiculo, LocalDateTime dataHora);

	void save(AulaProcessoVeiculo agendamento);

}
