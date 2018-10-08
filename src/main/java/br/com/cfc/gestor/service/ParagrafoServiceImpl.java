package br.com.cfc.gestor.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Clausula;
import br.com.cfc.gestor.model.Paragrafo;
import br.com.cfc.gestor.repository.ParagrafoRepository;

@Service
public class ParagrafoServiceImpl implements ParagrafoService{

	@Resource
	private ParagrafoRepository paragrafoRepository;

	@Override
	public Iterable<Paragrafo> findAll(Clausula clausula) {
		return paragrafoRepository.findClausula(clausula);
	}

	@Override
	public void save(Paragrafo paragrafo) {
		paragrafoRepository.save(paragrafo);
	}
}
