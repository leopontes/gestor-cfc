package br.com.cfc.gestor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="conta")
public class Conta implements Serializable{

	private static final long serialVersionUID = 7475198635869999285L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="conta_id", nullable=false)
	private Long id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="data_vencimento", nullable=false)
	private LocalDate dataVencimento;
	
	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="despesa_id", nullable=false)
	private Despesa despesa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}
}
