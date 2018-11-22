package br.com.cfc.gestor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="secao")
public class Secao implements Serializable{

	private static final long serialVersionUID = 7449038793280585652L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="secao_id", nullable=false)
	private Long id;

	@Column(name="ordem", nullable=false)
	private Integer ordem;
	
	@Column(name="texto", length=200, nullable=false)
	private String descricao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
