package br.com.cfc.gestor.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.cfc.gestor.model.Instrutor;

public class InstrutorForm extends Form<Instrutor>{

	private static final long serialVersionUID = -1552734775082138357L;
	
	private Long id;
	
	@NotNull(message="O campo nome é obrigatório")
	private String nome;
	
	@NotNull(message="O campo credencial é obrigatório")
	private String credencial;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="O campo valida da credencial é obrigatório")
	private LocalDate validadeCredencial;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCredencial() {
		return credencial;
	}

	public void setCredencial(String credencial) {
		this.credencial = credencial;
	}

	public LocalDate getValidadeCredencial() {
		return validadeCredencial;
	}

	public void setValidadeCredencial(LocalDate validadeCredencial) {
		this.validadeCredencial = validadeCredencial;
	}

	@Override
	public Instrutor toBean() {
		
		Instrutor instrutor = new Instrutor();
		
		instrutor.setId(getId());
		instrutor.setCredencial(getCredencial());
		instrutor.setNome(getNome());
		instrutor.setValidadeCredencial(getValidadeCredencial());
		
		return instrutor;
	}

	@Override
	public void toForm(Instrutor instrutor) {
		setId(instrutor.getId());
		setNome(instrutor.getNome());
		setCredencial(instrutor.getCredencial());
		setValidadeCredencial(instrutor.getValidadeCredencial());
	}

}
