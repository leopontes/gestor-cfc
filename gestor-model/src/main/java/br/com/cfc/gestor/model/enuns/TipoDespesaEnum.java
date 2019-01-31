package br.com.cfc.gestor.model.enuns;

public enum TipoDespesaEnum {

	DESPESA_FIXA("Despesa Fixa"),
	DESPESA_VARIAVEL("Despesa Variável");
	
	private String descricao;
	
	TipoDespesaEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
