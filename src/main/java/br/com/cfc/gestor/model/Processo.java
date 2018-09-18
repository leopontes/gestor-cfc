package br.com.cfc.gestor.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.cfc.gestor.model.enuns.CategoriaEnum;
import br.com.cfc.gestor.model.enuns.ServicoEnum;

@Entity
@Table(name="processo")
public class Processo implements Serializable{

	private static final long serialVersionUID = 3503914035781250871L;

	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="renach", length=40, nullable=true)
	private String renach;
	
	@Enumerated(EnumType.STRING)
	private ServicoEnum servico;
	
	@Enumerated(EnumType.STRING)
	private CategoriaEnum categoria;

	@Column(name="dataInicio", nullable=true)
	private LocalDate dataInicio;
	
	@Column(name="dataTermino", nullable=true)
	private LocalDate dataTermino;
	
	@Column(name="observacao", length=255, nullable=false)
	private String observacao;
	
	@OneToMany(fetch=FetchType.LAZY)
	private Collection<Pagamento> pagamentos;
	
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

	public Collection<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(Collection<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
}
