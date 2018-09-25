package br.com.cfc.gestor.controller.filtro;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.cfc.gestor.model.enuns.NavigationEnum;

public class AgendamentoFiltro implements Serializable{

	private static final long serialVersionUID = 7449171409092050843L;
	
	private Long matricula = Long.valueOf(0);
	
	private Long veiculo = Long.valueOf(0);
	
	private String mesAno = "";
	
	private String nome = "";
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate inicio;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate fim;
	
	private NavigationEnum navigation;

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public Long getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Long veiculo) {
		this.veiculo = veiculo;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFim() {
		return fim;
	}

	public void setFim(LocalDate fim) {
		this.fim = fim;
	}

	public NavigationEnum getNavigation() {
		return navigation;
	}

	public void setNavigation(NavigationEnum navigation) {
		this.navigation = navigation;
	}
}
