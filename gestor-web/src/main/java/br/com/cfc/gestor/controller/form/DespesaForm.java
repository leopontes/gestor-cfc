package br.com.cfc.gestor.controller.form;

import java.util.Collection;

import br.com.cfc.gestor.model.Conta;
import br.com.cfc.gestor.model.Despesa;
import br.com.cfc.gestor.model.enuns.TipoDespesaEnum;

public class DespesaForm extends Form<Despesa> {
	
	private static final long serialVersionUID = 1324293765857499027L;

	private Long id;
	
	private TipoDespesaEnum tipoDespesa;
	
	private String descricao;
	
	private Collection<Conta> contas;

	@Override
	public Despesa toBean() {
		
		Despesa despesa = new Despesa();
		
		despesa.setId(getId());
		despesa.setDescricao(getDescricao());
		despesa.setTipoDespesa(getTipoDespesa());
		despesa.setContas(getContas());
		
		return despesa;
	}

	@Override
	public void toForm(Despesa despesa) {
		setId(despesa.getId());
		setDescricao(despesa.getDescricao());
		setTipoDespesa(despesa.getTipoDespesa());
		setContas(despesa.getContas());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDespesaEnum getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesaEnum tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Collection<Conta> getContas() {
		return contas;
	}

	public void setContas(Collection<Conta> contas) {
		this.contas = contas;
	}
}
