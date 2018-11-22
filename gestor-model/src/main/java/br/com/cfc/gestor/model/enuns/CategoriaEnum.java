package br.com.cfc.gestor.model.enuns;

public enum CategoriaEnum {

	A("A - Motocicleta"),
	B("B - Automóvel, caminhonete, camioneta, utilitário"),
	C("C - Caminhão"),
	D("D - Microônibus, Ônibus."),
	E("E - Veículo com dois reboques acoplados"),
	ACC("ACC - Ciclomotores"),
	AB("AB - Automóvel & Motocicleta");
	
	CategoriaEnum(String descricao){
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
