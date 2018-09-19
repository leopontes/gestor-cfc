package br.com.cfc.gestor.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name="aula", uniqueConstraints= {@UniqueConstraint(name="aula_uk", columnNames= {"data", "veiculo_id"})})
public class AulaProcessoVeiculo implements Serializable, Comparable<AulaProcessoVeiculo>{

	private static final long serialVersionUID = 3171423919791304083L;

	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="processo_id", nullable=false)
	private Processo processo;
	
	@Column(name="data", nullable=false)
	private LocalDateTime data;

	@Enumerated(EnumType.STRING)
	private TipoAulaEnum tipo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="veiculo_id", nullable=false)
	private Veiculo veiculo;
	
	public AulaProcessoVeiculo() {
		super();
	}
	
	public AulaProcessoVeiculo(Processo processo, LocalDateTime data, TipoAulaEnum tipo, Veiculo veiculo) {
		super();
		
		this.processo = processo;
		this.data = data;
		this.tipo = tipo;
		this.veiculo = veiculo;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
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
	
	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	@Override
	public int compareTo(AulaProcessoVeiculo other) {
		return this.data.compareTo(other.getData());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		AulaProcessoVeiculo other = (AulaProcessoVeiculo) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AulaProcessoVeiculo [processo=" + processo + ", data=" + data + ", tipo=" + tipo + ", veiculo="
				+ veiculo + "]";
	}
	
}
