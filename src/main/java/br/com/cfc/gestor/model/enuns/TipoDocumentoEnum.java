package br.com.cfc.gestor.model.enuns;

public enum TipoDocumentoEnum {

	IDENTIDADE("Identidade"),
	CNH("CNH"),
	CPF("CPF"),
	CONTRATO("Contrato"),
	COMPROVANTE_RESIDENCIAL("Comprovante residencial");
	
	TipoDocumentoEnum(String descricao){
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
