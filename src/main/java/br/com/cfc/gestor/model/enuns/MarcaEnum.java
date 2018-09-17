package br.com.cfc.gestor.model.enuns;

public enum MarcaEnum {

	FIAT("Fiat"),
	FORD("Ford"),
	HONDA("Honda"),
	HYUNDAI("Hyundai"),
	TOYOTA("Toyota"),
	VOLKSWAGEN("Volkswagen");
	
	MarcaEnum(String nome){
		this.nome = nome;
	}
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
