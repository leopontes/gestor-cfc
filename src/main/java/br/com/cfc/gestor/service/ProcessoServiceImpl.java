package br.com.cfc.gestor.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.repository.ProcessoRepository;

@Service
public class ProcessoServiceImpl implements ProcessoService {

	@Resource
	private ProcessoRepository processoRepository;
	
	@Override
	public void save(Processo processo) {
		processoRepository.save(processo);
	}

	@Override
	public Collection<Processo> find(Aluno aluno) {
		return processoRepository.findByAluno(aluno);
	}

	@Override
	public Processo getVigente(Aluno aluno) {
		return processoRepository.getVigente(aluno);
	}

	@Override
	public Processo getId(Long id) {
		return processoRepository.findById(id).orElse(null);
	}

}
