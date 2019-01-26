package br.com.cfc.gestor.controller.form;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.cfc.gestor.model.Aluno;

public class AlunoForm extends Form<Aluno>{

	private static final long serialVersionUID = 705451097516169645L;
	
	private Long id;
	
	@NotBlank(message="O campo nome é obrigatório")
	@Length(max=200, message="O campo nome precisa ter no máximo 200 caracteres")
	private String nome;
	
	@Length(max=200, message="O pai nome precisa ter no máximo 200 caracteres")
	private String pai;
	
	@Length(max=200, message="O campo mãe precisa ter no máximo 200 caracteres")
	private String mae;
	
	@NotBlank(message="O endereco nome é obrigatório")
	@Length(max=200, message="O campo endereço precisa ter no máximo 500 caracteres.")
	private String endereco;
	
	@Column(name="numero", nullable=false)
	private Integer numero;
	
	@Column(name="complemento", nullable=true, length=200)
	private String complemento;
	
	@NotBlank(message="O bairro nome é obrigatório")
	private String bairro;

	@NotBlank(message="O campo cidade é obrigatório")
	private String cidade;
	
	@NotBlank(message="O CEP nome é obrigatório")
	private String cep;
	
	@NotBlank(message="O campo UF é obrigatório")
	private String uf;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="O campo data de nascimento é obrigatório")
	private LocalDate dataNascimento;
	
	@NotBlank(message="O campo identidade é obrigatório")
	private String identidade;
	
	@NotBlank(message="O campo órgão emissor é obrigatório")
	@Column(name="orgao_emissor", nullable=false, length=200)
	private String orgaoEmissor;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="O campo data de expedição é obrigatório")
	private LocalDate dataEmissao;

	@NotBlank(message="O campo CPF é obrigatório")
	private String cpf;
	
	private String email;
	
	@NotBlank(message="O campo contato é obrigatório")
	private String contato1;
	
	private String contato2;
	
	private String contato3;
	
	private LocalDate cadastradoEm;
	
	private String foto;
	
	private String pathFoto;
	
	

	public String getMatricula() {
		return (this.id == null ? "" : this.id) + "/" + (this.cadastradoEm == null ? "" : this.cadastradoEm.getYear());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContato1() {
		return contato1;
	}

	public void setContato1(String contato1) {
		this.contato1 = contato1;
	}

	public String getContato2() {
		return contato2;
	}

	public void setContato2(String contato2) {
		this.contato2 = contato2;
	}

	public String getContato3() {
		return contato3;
	}

	public void setContato3(String contato3) {
		this.contato3 = contato3;
	}

	public LocalDate getCadastradoEm() {
		return cadastradoEm;
	}

	public void setCadastradoEm(LocalDate cadastradoEm) {
		this.cadastradoEm = cadastradoEm;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}
	
	public void toForm(Aluno aluno) {
		setId(aluno.getId());
		setNome(aluno.getNome());
		setPai(aluno.getPai());
		setMae(aluno.getMae());
		setEndereco(aluno.getEndereco());
		setNumero(aluno.getNumero());
		setComplemento(aluno.getComplemento());
		setBairro(aluno.getBairro());
		setCidade(aluno.getCidade());
		setCep(aluno.getCep());
		setUf(aluno.getUf());
		setDataNascimento(aluno.getDataNascimento());
		setIdentidade(aluno.getIdentidade());
		setOrgaoEmissor(aluno.getOrgaoEmissor());
		setDataEmissao(aluno.getDataEmissao());
		setCpf(aluno.getCpf());
		setEmail(aluno.getEmail());
		setContato1(aluno.getContato1());
		setContato2(aluno.getContato2());
		setContato3(aluno.getContato3());
		setCadastradoEm(aluno.getCadastradoEm());
		setPathFoto(aluno.getPathFoto());
	}

	@Override
	public Aluno toBean() {
		Aluno aluno = new Aluno();
		
		aluno.setId(id);
		aluno.setNome(nome);
		aluno.setPai(pai);
		aluno.setMae(mae);
		aluno.setEndereco(endereco);
		aluno.setNumero(numero);
		aluno.setComplemento(complemento);
		aluno.setBairro(bairro);
		aluno.setCidade(cidade);
		aluno.setCep(cep);
		aluno.setUf(uf);
		aluno.setDataNascimento(dataNascimento);
		aluno.setIdentidade(identidade);
		aluno.setOrgaoEmissor(orgaoEmissor);
		aluno.setDataEmissao(dataEmissao);
		aluno.setCpf(cpf);
		aluno.setEmail(email);
		aluno.setContato1(contato1);
		aluno.setContato2(contato2);
		aluno.setContato3(contato3);
		aluno.setCadastradoEm(cadastradoEm);
		
		return aluno;
	}
}
