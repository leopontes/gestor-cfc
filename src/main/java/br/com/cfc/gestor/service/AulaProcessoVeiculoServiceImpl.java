package br.com.cfc.gestor.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.AulaProcessoVeiculo;
import br.com.cfc.gestor.repository.AulaProcessoVeiculoRepository;

@Service
public class AulaProcessoVeiculoServiceImpl implements AulaProcessoVeiculoService {

	@Resource
	private AulaProcessoVeiculoRepository aulaProcessoVeiculoRepository;
	
	@Override
	public AulaProcessoVeiculo find(Long codigoVeiculo, LocalDateTime dataHora) {
		return aulaProcessoVeiculoRepository.findByVeiculoData(codigoVeiculo, dataHora);
	}

	@Override
	public void save(AulaProcessoVeiculo agendamento) {
		aulaProcessoVeiculoRepository.save(agendamento);
	}

	@Override
	public AulaProcessoVeiculo get(Long agendamentoId) {
		
		Optional<AulaProcessoVeiculo> aula = aulaProcessoVeiculoRepository.findById(agendamentoId);
		
		if(aula.isPresent()) {
			return aula.get();
		}
		
		return null;
				
	}

	@Override
	public void delete(AulaProcessoVeiculo aula) {
		aulaProcessoVeiculoRepository.delete(aula);
	}

}
