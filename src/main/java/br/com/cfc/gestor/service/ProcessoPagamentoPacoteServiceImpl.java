package br.com.cfc.gestor.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Pagamento;
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.ProcessoPagamentoPacote;
import br.com.cfc.gestor.repository.ProcessoPagamentoPacoteRepository;

@Service
public class ProcessoPagamentoPacoteServiceImpl implements ProcessoPagamentoPacoteService{

	@Resource
	private ProcessoPagamentoPacoteRepository processoPagamentoRepository;

	@Override
	public void save(ProcessoPagamentoPacote processoPagamentoPacote) {
		processoPagamentoRepository.save(processoPagamentoPacote);
	}

	@Override
	public Iterable<ProcessoPagamentoPacote> findByProcesso(Processo processo) {
		return processoPagamentoRepository.findByProcesso(processo);
	}
}
