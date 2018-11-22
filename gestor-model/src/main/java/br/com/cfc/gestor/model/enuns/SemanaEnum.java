package br.com.cfc.gestor.model.enuns;

public enum SemanaEnum {

	SEGUNDA(1, "Segunda-Feira", "Seg"),
	TERCA  (2, "Terça-feira", "Ter"),
	QUARTA (3, "Quarta-feira", "Qua"),
	QUINTA (4, "Quinta", "Qui"),	
	SEXTA  (5, "Sexta", "Sex"),
	SABADO (6, "Sábado", "Sab"),
	DOMINGO(7, "Domingo", "Dom");
	
	SemanaEnum(Integer codigo, String descricao, String sigla){
		this.codigo = codigo;
		this.descricao = descricao;
		this.sigla = sigla;
	}
	
	private Integer codigo;
	
	private String descricao;
	
	private String sigla;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public static SemanaEnum fromCodigo(Integer codigo) {
		for(SemanaEnum semana : values()) {
			if(semana.getCodigo().equals(codigo)) {
				return semana;
			}
		}
		
		return null;
	}
}
