package br.com.cfc.gestor.service;

import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.ProcessoPagamentoPacote;

public interface ProcessoPagamentoPacoteService {

	void save(ProcessoPagamentoPacote processoPagamentoPacote);

	Iterable<ProcessoPagamentoPacote> findByProcesso(Processo processo);

}
