package br.com.cfc.gestor.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cfc.gestor.model.enuns.CategoriaEnum;
import br.com.cfc.gestor.model.enuns.ServicoEnum;
import br.com.cfc.gestor.model.enuns.StatusProcessoEnum;

@Entity
@Table(name="processo")
public class Processo implements Serializable, Comparable<Processo>{

	private static final long serialVersionUID = 3503914035781250871L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="processo_id", nullable=false)
	private Long id;
	
	@Column(name="renach", length=40, nullable=true)
	private String renach;
	
	@Enumerated(EnumType.STRING)
	private ServicoEnum servico;
	
	@Enumerated(EnumType.STRING)
	private CategoriaEnum categoria;

	@Column(name="data_inicio", nullable=true)
	private LocalDate dataInicio;
	
	@Column(name="data_termino", nullable=true)
	private LocalDate dataTermino;
	
	@Column(name="observacao", length=255, nullable=false)
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=true)
	private StatusProcessoEnum status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contrato_id")
	private Contrato contrato;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRenach() {
		return renach;
	}

	public void setRenach(String renach) {
		this.renach = renach;
	}

	public ServicoEnum getServico() {
		return servico;
	}

	public void setServico(ServicoEnum servico) {
		this.servico = servico;
	}

	public CategoriaEnum getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEnum categoria) {
		this.categoria = categoria;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public StatusProcessoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusProcessoEnum status) {
		this.status = status;
	}
	
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	@Override
	public int compareTo(Processo other) {
		return this.dataInicio.compareTo(other.getDataInicio());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Processo other = (Processo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Processo [renach=" + renach + ", servico=" + servico + ", categoria=" + categoria + ", dataInicio="
				+ dataInicio + ", dataTermino=" + dataTermino + "]";
	}
	
	
}
