	package br.com.cfc.gestor.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="instrutor")
public class Instrutor implements Serializable{

	private static final long serialVersionUID = -2329184233194434132L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="instrutor_id", nullable=false)
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="credencial", nullable=false)
	private String credencial;
	
	@Column(name="validade_credencial", nullable=false)
	private LocalDate validadeCredencial;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCredencial() {
		return credencial;
	}

	public void setCredencial(String credencial) {
		this.credencial = credencial;
	}

	public LocalDate getValidadeCredencial() {
		return validadeCredencial;
	}

	public void setValidadeCredencial(LocalDate validadeCredencial) {
		this.validadeCredencial = validadeCredencial;
	}
}
