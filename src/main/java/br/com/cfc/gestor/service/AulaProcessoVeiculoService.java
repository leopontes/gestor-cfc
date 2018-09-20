package br.com.cfc.gestor.service;

import java.time.LocalDateTime;
import java.util.Collection;

import br.com.cfc.gestor.model.AulaProcessoVeiculo;

public interface AulaProcessoVeiculoService {

	Collection<AulaProcessoVeiculo> find(Long codigoVeiculo, LocalDateTime dataHora);

}
