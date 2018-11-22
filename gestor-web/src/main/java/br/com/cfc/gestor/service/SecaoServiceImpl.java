package br.com.cfc.gestor.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Secao;
import br.com.cfc.gestor.repository.SecaoRepository;

@Service
public class SecaoServiceImpl implements SecaoService{

	@Resource
	private SecaoRepository secaoRepository;

	@Override
	public Iterable<Secao> findAll() {
		return secaoRepository.findAll();
	}

	@Override
	public void save(Secao secao) {
		secaoRepository.save(secao);
	}
}
