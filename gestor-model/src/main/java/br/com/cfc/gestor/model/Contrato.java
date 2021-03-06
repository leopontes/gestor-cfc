package br.com.cfc.gestor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.cfc.gestor.model.enuns.CategoriaEnum;

@Entity
@Table(name="contrato")
public class Contrato implements Serializable{

	private static final long serialVersionUID = 5854436999730204432L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contrato_id", nullable=false)
	private Long id;
	
	@Column(name="nome", length=90, nullable=false)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name="categoria", length=3, nullable=false)
	private CategoriaEnum categoria;
	
	@Column(name="texto", length=500, nullable=false)
	private String texto;
	
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public CategoriaEnum getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEnum categoria) {
		this.categoria = categoria;
	}
}
