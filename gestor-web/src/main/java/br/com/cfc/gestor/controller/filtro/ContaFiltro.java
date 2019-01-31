package br.com.cfc.gestor.controller.filtro;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.cfc.gestor.model.enuns.TipoDespesaEnum;

public class ContaFiltro implements Serializable{

	private static final long serialVersionUID = -2962573692503502843L;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate inicio;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate fim;
	
	private TipoDespesaEnum tipoDespesa;

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFim() {
		return fim;
	}

	public void setFim(LocalDate fim) {
		this.fim = fim;
	}

	public TipoDespesaEnum getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesaEnum tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}
}
