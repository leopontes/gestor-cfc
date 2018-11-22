package br.com.cfc.gestor.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.Documento;
import br.com.cfc.gestor.repository.DocumentoRepository;

@Service
public class DocumentoServiceImpl implements DocumentoService{

	@Resource
	private DocumentoRepository documentoRepository;

	@Override
	public void save(Documento doc) {
		documentoRepository.save(doc);
	}

	@Override
	public Iterable<Documento> findByAluno(Aluno aluno) {
		return documentoRepository.findByAluno(aluno);
	}
}
