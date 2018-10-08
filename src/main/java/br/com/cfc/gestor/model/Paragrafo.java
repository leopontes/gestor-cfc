package br.com.cfc.gestor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="paragrafo")
public class Paragrafo implements Serializable{

	private static final long serialVersionUID = -5477537478701145191L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="paragrafo_id", nullable=false)
	private Long id;
	
	@Column(name="ordem", nullable=false)
	private Integer ordem;
	
	@Column(name="texto", nullable=false)
	private String texto;
	
	@Column(name="unico", length=1, nullable=false)
	private boolean unico;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clausula_id")
	private Clausula clausula;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isUnico() {
		return unico;
	}

	public void setUnico(boolean unico) {
		this.unico = unico;
	}

	public Clausula getClausula() {
		return clausula;
	}

	public void setClausula(Clausula clausula) {
		this.clausula = clausula;
	}
}
