package com.multiplayer.projetoaccountjpa.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@OneToMany(targetEntity=, mappedBy="", fetch=FetchType.EAGER)
@Entity
public class Lancamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String numeroConta;
	
	@Column(nullable = false)
	private LocalDate data;
	
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = true)
	private String contaDestino;
	
//	mudar tipagem
	@Column(nullable = false)
	private String tipo;
	
//	mudar tipagem
	@Column(nullable = false)
	private String categoria;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
