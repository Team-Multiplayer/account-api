package br.multiplayer.accountapi.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.multiplayer.accountapi.enums.TipoLancamento;

@Entity
public class Lancamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 20)
	private String numeroConta;
	
	@Column(nullable = false, length = 10)
	private LocalDate data;
	
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false, length = 100)
	private String descricao;
	
	@Column(nullable = true, length = 20)
	private String contaDestino;
	
	@Column(nullable = false)
	private TipoLancamento tipo;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria", referencedColumnName = "id")
	private PlanoConta categoria;
	
	@ManyToOne
    @JoinColumn(name="conta_id", nullable=false)
	private Conta conta;

	// Default constructor
	public Lancamento() {};
	
	public Integer getId() {
		return id;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public PlanoConta getCategoria() {
		return categoria;
	}

	public void setCategoria(PlanoConta categoria) {
		this.categoria = categoria;
	}
}
