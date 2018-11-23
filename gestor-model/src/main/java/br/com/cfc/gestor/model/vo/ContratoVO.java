package br.com.cfc.gestor.model.vo;

import java.io.Serializable;

public class ContratoVO implements Serializable{

	private static final long serialVersionUID = 8503360915168417042L;

	private String introducao;
	
	private Integer secaoOrdem;
	
	private String secao;
	
	private Integer clausulaOrdem;
	
	private String clausula;
	
	private Integer paragrafoOrdem;
	
	private String paragrafo;
	
	private Boolean unico;

	public String getIntroducao() {
		return introducao;
	}

	public void setIntroducao(String introducao) {
		this.introducao = introducao;
	}

	public Integer getSecaoOrdem() {
		return secaoOrdem;
	}

	public void setSecaoOrdem(Integer secaoOrdem) {
		this.secaoOrdem = secaoOrdem;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public Integer getClausulaOrdem() {
		return clausulaOrdem;
	}

	public void setClausulaOrdem(Integer clausulaOrdem) {
		this.clausulaOrdem = clausulaOrdem;
	}

	public String getClausula() {
		return clausula;
	}

	public void setClausula(String clausula) {
		this.clausula = clausula;
	}

	public Integer getParagrafoOrdem() {
		return paragrafoOrdem;
	}

	public void setParagrafoOrdem(Integer paragrafoOrdem) {
		this.paragrafoOrdem = paragrafoOrdem;
	}

	public String getParagrafo() {
		return paragrafo;
	}

	public void setParagrafo(String paragrafo) {
		this.paragrafo = paragrafo;
	}

	public Boolean getUnico() {
		return unico;
	}

	public void setUnico(Boolean unico) {
		this.unico = unico;
	}
}
