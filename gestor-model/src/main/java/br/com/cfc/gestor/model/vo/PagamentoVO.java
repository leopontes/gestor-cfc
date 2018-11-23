package br.com.cfc.gestor.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import org.joda.time.LocalDate;

import br.com.cfc.gestor.model.Pagamento;
import br.com.cfc.gestor.model.enuns.FormaDePagamentoEnum;
import br.com.cfc.gestor.model.enuns.TipoPagamentoEnum;

public class PagamentoVO  implements Serializable{

	private static final long serialVersionUID = -5450930667461699836L;

	private Long id;
	
	private TipoPagamentoEnum tipoPagamento;

	private FormaDePagamentoEnum formaPagamento;

	private LocalDate dataVencimento;
	
	private LocalDate dataPagamento;
	
	private BigDecimal valor;

	public PagamentoVO() {
		super();
	}
	
	public PagamentoVO(final Pagamento pagamento) {
		super();
		
		this.id = pagamento.getId();
		this.tipoPagamento  = pagamento.getTipoPagamento();
		this.formaPagamento = pagamento.getFormaPagamento();
		this.dataVencimento = new org.joda.time.LocalDate(pagamento.getDataVencimento().getYear(), pagamento.getDataVencimento().getMonthValue(), pagamento.getDataVencimento().getDayOfMonth());
		
		if(pagamento.getDataPagamento() != null) {
			this.dataPagamento  = new org.joda.time.LocalDate(pagamento.getDataPagamento().getYear(), pagamento.getDataPagamento().getMonthValue(), pagamento.getDataPagamento().getDayOfMonth());
		}
		
		this.valor          = pagamento.getValor();
	}

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
}
