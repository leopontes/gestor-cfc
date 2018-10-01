package br.com.cfc.gestor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name="pacote", uniqueConstraints= {@UniqueConstraint(name="pacote_uk", columnNames="nome")})
public class Pacote implements Serializable{

	private static final long serialVersionUID = -6690538504975188709L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pacote_id", nullable=false)
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="total_aulas_praticas", nullable=false)
	private Integer totalAulasPraticas;
	
	@Column(name="total_aulas_teoricas", nullable=false)
	private Integer totalAulasTeoricas;
	
	@Column(name="total_aulas_simulador", nullable=false)
	private Integer totalAulasSimulador;
	
	@Column(name="total_simulados", nullable=false)
	private Integer totalSimulados;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="data_inicio", nullable=false)
	private LocalDate dataInicio;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="data_fim", nullable=true)
	private LocalDate dataFim;

	@NumberFormat(style=Style.CURRENCY)
	@Column(name="valor", nullable=false)
	private BigDecimal valor;
	
	@OneToMany(fetch=FetchType.LAZY)
	private Collection<Pagamento> pagamentos;

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

	public Integer getTotalAulasPraticas() {
		return totalAulasPraticas;
	}

	public void setTotalAulasPraticas(Integer totalAulasPraticas) {
		this.totalAulasPraticas = totalAulasPraticas;
	}

	public Integer getTotalAulasTeoricas() {
		return totalAulasTeoricas;
	}

	public void setTotalAulasTeoricas(Integer totalAulasTeoricas) {
		this.totalAulasTeoricas = totalAulasTeoricas;
	}

	public Integer getTotalAulasSimulador() {
		return totalAulasSimulador;
	}

	public void setTotalAulasSimulador(Integer totalAulasSimulador) {
		this.totalAulasSimulador = totalAulasSimulador;
	}

	public Integer getTotalSimulados() {
		return totalSimulados;
	}

	public void setTotalSimulados(Integer totalSimulados) {
		this.totalSimulados = totalSimulados;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Collection<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(Collection<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
}