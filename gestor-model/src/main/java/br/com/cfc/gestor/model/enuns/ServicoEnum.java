package br.com.cfc.gestor.model.enuns;

public enum ServicoEnum {

	PRIMEIRA_HABILITACAO("Primeira habilitação"),
	INCLUSAO_CATEGORIA("Inclusão de categoria"),
	RENOVACAO("Renovação"),
	APROVEITAMENTO("Aproveitamento");
	
	ServicoEnum(String descricao){
		this.descricao = descricao;
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
