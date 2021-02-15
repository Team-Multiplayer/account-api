package br.multiplayer.accountapi.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.multiplayer.accountapi.enums.TipoConta;

@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 20)
	private Integer numero;
	
	@Column(nullable = false, length = 20)
	private TipoConta tipoConta;
	
	@Column(nullable = false)
	private Double saldo;

	@OneToMany(mappedBy="conta")
	private Set<Lancamento> lancamentos;
	
	// Default constructor
	public Conta() {}
	
	public Conta(TipoConta tipo) {
		this.numero = 000;
		this.tipoConta = tipo;
		this.saldo = 0D;
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Set<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(Set<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	
}
