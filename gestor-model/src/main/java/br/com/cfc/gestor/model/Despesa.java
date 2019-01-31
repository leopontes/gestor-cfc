package br.com.cfc.gestor.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.cfc.gestor.model.enuns.TipoDespesaEnum;

@Entity
@Table(name="despesa")
public class Despesa implements Serializable{

	private static final long serialVersionUID = -9009596304877839487L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="despesa_id", nullable=false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoDespesaEnum tipoDespesa;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@OneToMany(fetch=FetchType.LAZY)
	private Collection<Conta> contas;

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
