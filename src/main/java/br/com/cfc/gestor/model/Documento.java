package br.com.cfc.gestor.model;

import java.io.Serializable;

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
import javax.persistence.UniqueConstraint;

import br.com.cfc.gestor.model.enuns.TipoDocumentoEnum;

@Entity
@Table(name="documento", uniqueConstraints= {@UniqueConstraint(name="documento_nome_uk", columnNames= {"nome"})})
public class Documento implements Serializable{

	private static final long serialVersionUID = -5298146236310332447L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contrato_id", nullable=false)
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="path", nullable=false)
	private String path;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_documento", nullable=false)
	private TipoDocumentoEnum tipoDocumento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="aluno_id")
	private Aluno aluno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}