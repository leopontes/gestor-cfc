package br.com.cfc.gestor.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="aluno", uniqueConstraints= {@UniqueConstraint(name="aluno_cpf_uk", columnNames="cpf")})
public class Aluno {

	@Id
	@GeneratedValue
	@Column(name="matricula", nullable=false)
	private Long matricula;
	
	@NotBlank(message="O campo nome é obrigatório")
	@Column(name="nome", nullable=false, length=200)
	private String nome;

	@Column(name="pai", nullable=true, length=200)
	private String pai;
	
	@Column(name="mae", nullable=true, length=200)
	private String mae;
	
	@NotBlank(message="O endereco nome é obrigatório")
	@Column(name="endereco", nullable=false, length=500)
	private String endereco;
	
	@Column(name="numero", nullable=false)
	private Integer numero;
	
	@Column(name="complemento", nullable=true, length=200)
	private String complemento;
	
	@NotBlank(message="O bairro nome é obrigatório")
	@Column(name="bairro", nullable=false, length=200)
	private String bairro;

	@NotBlank(message="O campo cidade é obrigatório")
	@Column(name="cidade", nullable=false, length=200)
	private String cidade;
	
	@NotBlank(message="O CEP nome é obrigatório")
	@Column(name="cep", nullable=false, length=20)
	private String CEP;
	
	@NotBlank(message="O campo UF é obrigatório")
	@Column(name="uf", nullable=false, length=2)
	private String UF;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="O campo data de nascimento é obrigatório")
	@Column(name="data_nacimento", nullable=false)
	private LocalDate dataNascimento;
	
	@NotBlank(message="O campo identidade é obrigatório")
	@Column(name="identidade", nullable=false, length=200)
	private String identidade;
	
	@NotBlank(message="O campo órgão emissor é obrigatório")
	@Column(name="orgao_emissor", nullable=false, length=200)
	private String orgaoEmissor;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="O campo data de expedição é obrigatório")
	@Column(name="data_expedicao", nullable=false)
	private LocalDate dataExpedicao;

	@NotBlank(message="O campo CPF é obrigatório")
	@Column(name="cpf", nullable=false, length=14)
	private String CPF;
	
	@Column(name="email", nullable=true, length=200)
	private String email;
	
	@NotBlank(message="O campo contato é obrigatório")
	@Column(name="contato1", nullable=false, length=20)
	private String contato1;
	
	@Column(name="contato2", nullable=false, length=20)
	private String contato2;
	
	@Column(name="contato3", nullable=false, length=20)
	private String contato3;
	
	@Column(name="cadastrado_em", nullable=false)
	private LocalDate cadastradoEm;
	
	@OneToMany(fetch=FetchType.LAZY)
	private Collection<Processo> processos;
	
	public Aluno() {
		super();
		this.cadastradoEm = LocalDate.now();
	}
	
	public Long getId() {
		return matricula;
	}

	public String getCodigoMatricula() {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy");
		
		return matricula + "/" +cadastradoEm.format(format);
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
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

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
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

	public LocalDate getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(LocalDate dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
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

	public Collection<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(Collection<Processo> processos) {
		this.processos = processos;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public Long getPrazo() {
		LocalDate hoje = LocalDate.now();
		return ChronoUnit.DAYS.between(hoje, this.cadastradoEm.plusMonths(12));
	}
	
	
}
