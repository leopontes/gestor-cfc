package br.com.cfc.gestor.service;

import java.time.LocalDate;

import br.com.cfc.gestor.model.Conta;

public interface ContaService {

	Iterable<Conta> findBy(LocalDate inicio, LocalDate fim);

}
