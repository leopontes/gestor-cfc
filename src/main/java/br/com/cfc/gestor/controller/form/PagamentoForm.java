package br.com.cfc.gestor.controller.form;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.cfc.gestor.model.enuns.FormaDePagamentoEnum;
import br.com.cfc.gestor.model.enuns.ServicoEnum;
import br.com.cfc.gestor.model.enuns.TipoPagamentoEnum;

public class PagamentoForm implements Serializable{

	private static final long serialVersionUID = -7246544327119364572L;

	private Long alunoId;
	
	private String matricula;
	
	private String nome;
	
	private ServicoEnum servico;
	
	private Long pacote;
	
	private TipoPagamentoEnum tipoPagamento;
	
	private FormaDePagamentoEnum formaPagamento;
	
	private Integer parcela;
	
	private BigDecimal valorParcela;

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ServicoEnum getServico() {
		return servico;
	}

	public void setServico(ServicoEnum servico) {
		this.servico = servico;
	}

	public Long getPacote() {
		return pacote;
	}

	public void setPacote(Long pacote) {
		this.pacote = pacote;
	}

	public TipoPagamentoEnum getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamentoEnum tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public FormaDePagamentoEnum getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaDePagamentoEnum formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getParcela() {
		return parcela;
	}

	public void setParcela(Integer parcelas) {
		this.parcela = parcelas;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
}
