package br.com.cfc.gestor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="app_user", uniqueConstraints={@UniqueConstraint(name="app_user_uk", columnNames="user_name")})
public class Usuario implements Serializable{

	private static final long serialVersionUID = -3062821197028921723L;

	@Id
	@GeneratedValue
	@Column(name="user_id", nullable=false)
	private Long id;
	
	@Column(name="user_name", length=80, nullable=false)
	private String username;
	
	@Column(name="encrypted_password", length=200, nullable=false)
	private String encryptedPassword;
	
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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
