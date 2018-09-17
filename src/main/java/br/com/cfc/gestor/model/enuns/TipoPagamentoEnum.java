package br.com.cfc.gestor.model.enuns;

public enum TipoPagamentoEnum {

	DINHEIRO("Dinheiro"),
	CARTAO("Cartão"),
	CARNE("Carnê");
	
	TipoPagamentoEnum(String descricao){
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
