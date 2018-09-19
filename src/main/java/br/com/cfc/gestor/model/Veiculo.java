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
	@Column(name="veiculo_id", nullable=false)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (marca != other.marca)
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}
}
