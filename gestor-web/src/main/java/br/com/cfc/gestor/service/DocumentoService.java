package br.com.cfc.gestor.service;

import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.Documento;

public interface DocumentoService {

	void save(Documento doc);

	Iterable<Documento> findByAluno(Aluno aluno);

}
