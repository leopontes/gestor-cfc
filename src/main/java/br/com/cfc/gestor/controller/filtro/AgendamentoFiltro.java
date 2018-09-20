package br.com.cfc.gestor.controller.filtro;

import java.io.Serializable;

public class AgendamentoFiltro implements Serializable{

	private static final long serialVersionUID = 7449171409092050843L;
	
	private Long matricula;
	
	private Long veiculo;
	
	private String mesAno;

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
}
