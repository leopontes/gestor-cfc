package br.com.cfc.gestor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="usuario_permissao", uniqueConstraints= {@UniqueConstraint(name="usuario_permissao_uk", columnNames= {"usuario_id", "permissao_id"})})
public class UsuarioPermissao implements Serializable{

	private static final long serialVersionUID = 8638317259898690072L;

	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="permissao_id", nullable=false)
	private Permissao permissao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
}
