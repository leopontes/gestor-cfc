package br.com.cfc.gestor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="permissao", uniqueConstraints= {@UniqueConstraint(name="permissao_uk", columnNames="role_name")})
public class Permissao implements Serializable{

	private static final long serialVersionUID = -1864020182865868936L;

	@Id
	@GeneratedValue
	@Column(name="permissao_id", nullable=false)
	private Long id;
	
	@Column(name="role_name", length=40, nullable=false)
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
