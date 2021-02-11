package com.multiplayer.projetoaccountjpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Dashboard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Double saldo;
	
	@Column(nullable = false)
	private String nome;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contaBanco", referencedColumnName = "id")
	private Conta contaBanco;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contaCredito", referencedColumnName = "id")
	private Conta contaCredito;
	
//	Default constructor
	public Dashboard() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Conta getContaBanco() {
		return contaBanco;
	}

	public void setContaBanco(Conta contaBanco) {
		this.contaBanco = contaBanco;
	}

	public Conta getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(Conta contaCredito) {
		this.contaCredito = contaCredito;
	}
}
