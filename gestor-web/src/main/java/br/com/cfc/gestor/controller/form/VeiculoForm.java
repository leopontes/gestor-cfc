package br.com.cfc.gestor.controller.form;

import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.model.enuns.CombustivelEnum;
import br.com.cfc.gestor.model.enuns.MarcaEnum;

public class VeiculoForm extends Form<Veiculo>{

	private static final long serialVersionUID = 482342993975920487L;

	private Long id;
	
	private MarcaEnum marca;
	
	private String modelo;
	
	private String cor;
	
	private String versao;
	
	private String ano;
	
	private String placa;
	
	private String renavan;
	
	private CombustivelEnum combustivel;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MarcaEnum getMarca() {
		return marca;
	}

	public void setMarca(MarcaEnum marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRenavan() {
		return renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}

	public CombustivelEnum getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(CombustivelEnum combustivel) {
		this.combustivel = combustivel;
	}

	@Override
	public Veiculo toBean() {
		
		Veiculo veiculo = new Veiculo();
		
		veiculo.setId(getId());
		veiculo.setMarca(getMarca());
		veiculo.setModelo(getModelo());
		veiculo.setCor(getCor());
		veiculo.setVersao(getVersao());
		veiculo.setAno(getAno());
		veiculo.setPlaca(getPlaca());
		veiculo.setRenavan(getRenavan());
		veiculo.setCombustivel(combustivel);
		
		return veiculo;
	}

	@Override
	public void toForm(Veiculo veiculo) {
		setAno(veiculo.getAno());
		setCombustivel(veiculo.getCombustivel());
		setCor(veiculo.getCor());
		setId(veiculo.getId());
		setMarca(veiculo.getMarca());
		setModelo(veiculo.getModelo());
		setPlaca(veiculo.getPlaca());
		setRenavan(veiculo.getRenavan());
		setVersao(veiculo.getVersao());
	}

}
