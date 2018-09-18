package br.com.cfc.gestor.model.enuns;

public enum TipoAulaEnum {

	PRATICA("Aula prática"),
	TEORICA("Aula teórica");
	
	TipoAulaEnum(String descricao){
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
