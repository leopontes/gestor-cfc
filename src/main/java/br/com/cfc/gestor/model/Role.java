package br.com.cfc.gestor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="app_role", uniqueConstraints= {@UniqueConstraint(name="app_role_uk", columnNames="role_name")})
public class Role implements Serializable{

	private static final long serialVersionUID = -1864020182865868936L;

	@Id
	@GeneratedValue
	@Column(name="role_id", nullable=false)
	private Long id;
	
	@Column(name="role_name", length=40, nullable=false)
	private String roleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
