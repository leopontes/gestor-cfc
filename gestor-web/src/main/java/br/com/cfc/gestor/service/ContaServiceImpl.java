package br.com.cfc.gestor.service;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Conta;
import br.com.cfc.gestor.repository.ContaRepository;

@Service
public class ContaServiceImpl implements ContaService{

	@Resource
	private ContaRepository contaRepository;

	@Override
	public Iterable<Conta> findBy(LocalDate inicio, LocalDate fim) {
		return contaRepository.findByPeriodo(inicio, fim);
	}
}
