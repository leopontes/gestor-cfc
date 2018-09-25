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

import br.com.cfc.gestor.model.enuns.TipoDocumentoEnum;

@Entity
@Table(name="documento")
public class Documento implements Serializable{

	private static final long serialVersionUID = -5298146236310332447L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contrato_id", nullable=false)
	private Long id;
	
	@Column(name="path", nullable=false)
	private String path;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_documento", nullable=false)
	private TipoDocumentoEnum tipoDocumento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}