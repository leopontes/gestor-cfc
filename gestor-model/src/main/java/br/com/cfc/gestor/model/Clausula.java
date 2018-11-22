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
@Table(name="clausula")
public class Clausula implements Serializable{

	private static final long serialVersionUID = 2260955570413677453L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="clausula_id", nullable=false)
	private Long id;
	
	@Column(name="ordem", nullable=false)
	private Long ordem;
	
	@Column(name="texto", length=1000, nullable=false)
	private String texto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="secao_id")
	private Secao secao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contrato_id")
	private Contrato contrato;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrdem() {
		return ordem;
	}

	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
}
