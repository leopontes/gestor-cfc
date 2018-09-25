package br.com.cfc.gestor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cfc.gestor.model.enuns.FormaDePagamentoEnum;
import br.com.cfc.gestor.model.enuns.TipoPagamentoEnum;

@Entity
@Table(name="pagamento")
public class Pagamento implements Serializable{

	private static final long serialVersionUID = 3117909466368518900L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pagamento_id", nullable=false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_pagamento", nullable=false)
	private TipoPagamentoEnum tipoPagamento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="forma_pagamento", nullable=false)
	private FormaDePagamentoEnum formaPagamento;
	
	@Column(name="data_vencimento", nullable=false)
	private LocalDate dataVencimento;
	
	@Column(name="data_pagamento", nullable=true)
	private LocalDate dataPagamento;
	
	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="processo_id")
	private Processo processo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
}
