package br.com.cfc.gestor.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.cfc.gestor.model.enuns.TipoAulaEnum;

@Entity
@Table(name="aula", uniqueConstraints= {@UniqueConstraint(name="aula_uk", columnNames= {"aluno_id", "data", "veiculo_id"})})
public class Aula implements Serializable{

	private static final long serialVersionUID = 3171423919791304083L;

	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="aluno_id", nullable=false)
	private Aluno aluno;
	
	@Column(name="data", nullable=false)
	private LocalDate data;

	@Enumerated(EnumType.STRING)
	private TipoAulaEnum tipo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="veiculo_id", nullable=false)
	private Veiculo veiculo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public TipoAulaEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoAulaEnum tipo) {
		this.tipo = tipo;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
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
		Aula other = (Aula) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (tipo != other.tipo)
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}
}
