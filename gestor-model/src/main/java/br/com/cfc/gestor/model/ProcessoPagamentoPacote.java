package br.com.cfc.gestor.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="processo_pagamento_pacote", uniqueConstraints= {@UniqueConstraint(name="processo_pagamento_pacote_uk", columnNames= {"processo_id", "pagamento_id", "pacote_id"})})
public class ProcessoPagamentoPacote implements Serializable{

	private static final long serialVersionUID = -8367571394259504098L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="processo_pagamento_pacote_id", nullable=false)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="processo_id")
	private Processo processo;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="pagamento_id")
	private Pagamento pagamento;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="pacote_id")
	private Pacote pacote;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}
}
