package br.com.cfc.gestor.model.enuns;

public enum StatusProcessoEnum {
	EM_ANDAMENTO("Em andamento"),
	APROVADO("Aprovado"),
	REPROVADO("Reprovado"),
	EXPIRADO("Expirado");
	
	StatusProcessoEnum(String descricao){
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
