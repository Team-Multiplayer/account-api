package com.multiplayer.projetoaccountjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.multiplayer.projetoaccountjpa.enums.TipoConta;

@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String numero;
	
	@Column(nullable = false)
	private TipoConta tipoConta;
	
	private Double saldo;

	private Lancamento[] lancamentos;
	
	// Default constructor
	public Conta() {}
	
	public Conta(String numero) {
		this.numero = numero;
		this.tipoConta = TipoConta.CORRENTE;
	}
	
	public void debitar(Double valor) {
		this.saldo -= valor;
	}
	
	public void creditar(Double valor) {
		this.saldo += valor;
	}

	public Double getSaldo() {
		return this.saldo;
	}
	
	public Integer getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Lancamento[] getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(Lancamento[] lancamentos) {
		this.lancamentos = lancamentos;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	
}
