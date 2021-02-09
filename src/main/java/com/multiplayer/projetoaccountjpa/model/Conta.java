package com.multiplayer.projetoaccountjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String numero;
	
	@Column(nullable = false)
	private Lancamento[] lancamentos;
	
	@Column(nullable = false)
	private String tipoConta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	
}
