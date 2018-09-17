package br.com.cfc.gestor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.cfc.gestor.model.enuns.CombustivelEnum;
import br.com.cfc.gestor.model.enuns.MarcaEnum;

@Entity
@Table(name="veiculo")
public class Veiculo implements Serializable{

	private static final long serialVersionUID = 8611375234072136657L;

	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private MarcaEnum marca;
	
	@Column(name="modelo", length=100, nullable=false)
	private String modelo;
	
	@Column(name="cor", length=40, nullable=false)
	private String cor;
	
	@Column(name="versao", length=100, nullable=false)
	private String versao;
	
	@Column(name="ano", nullable=false)
	private Integer ano;
	
	@Column(name="placa", length=100, nullable=false)
	private String placa;
	
	@Column(name="renavan", length=100, nullable=false)
	private String renavan;
	
	@Enumerated(EnumType.STRING)
	private CombustivelEnum combustivel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MarcaEnum getMarca() {
		return marca;
	}

	public void setMarca(MarcaEnum marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRenavan() {
		return renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}

	public CombustivelEnum getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(CombustivelEnum combustivel) {
		this.combustivel = combustivel;
	}

	@Override
	public String toString() {
		return marca + " " + modelo + " " + cor + " " + ano + " - " + placa;
	}
	
	
}
