package br.com.cfc.gestor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="usuario", uniqueConstraints={@UniqueConstraint(name="usuario_uk", columnNames="username")})
public class Usuario implements Serializable{

	private static final long serialVersionUID = -3062821197028921723L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="usuario_id", nullable=false)
	private Long id;
	
	@Column(name="username", length=80, nullable=false)
	private String username;
	
	@Column(name="password", length=200, nullable=false)
	private String password;
	
	@Column(name="enabled", length=1, nullable=false)
	private boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
