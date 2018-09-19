package br.com.cfc.gestor.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="aluno", uniqueConstraints= {@UniqueConstraint(name="aluno_cpf_uk", columnNames="cpf")})
public class Aluno implements Serializable, Comparable<Aluno>{

	private static final long serialVersionUID = 1536751681511240271L;

	@Id
	@GeneratedValue
	@Column(name="aluno_id", nullable=false)
	private Long id;
	
	@NotBlank(message="O campo nome é obrigatório")
	@Length(max=200, message="O campo nome precisa ter no máximo 200 caracteres")
	@Column(name="nome", nullable=false, length=200)
	private String nome;
	
	@Length(max=200, message="O pai nome precisa ter no máximo 200 caracteres")
	@Column(name="pai", nullable=true, length=200)
	private String pai;
	
	@Length(max=200, message="O campo mãe precisa ter no máximo 200 caracteres")
	@Column(name="mae", nullable=true, length=200)
	private String mae;
	
	@NotBlank(message="O endereco nome é obrigatório")
	@Length(max=200, message="O campo endereço precisa ter no máximo 500 caracteres.")
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
	private String cep;
	
	@NotBlank(message="O campo UF é obrigatório")
	@Column(name="uf", nullable=false, length=2)
	private String uf;
	
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
	private LocalDate dataEmissao;

	@NotBlank(message="O campo CPF é obrigatório")
	@Column(name="cpf", nullable=false, length=14)
	private String cpf;
	
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

	public Collection<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(Collection<Processo> processos) {
		this.processos = processos;
	}
	
	public Integer getPrazo() {
		return this.cadastradoEm.getYear() - 12;
	}

	@Override
	public int compareTo(Aluno other) {
		return this.nome.compareTo(other.getNome());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
	}
}
