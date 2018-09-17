package br.com.cfc.gestor.model.enuns;

public enum FormaDePagamentoEnum {

	PARCELADO("Parcelado"),
	A_VISTA("À vista");
	
	FormaDePagamentoEnum(String descricao){
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
