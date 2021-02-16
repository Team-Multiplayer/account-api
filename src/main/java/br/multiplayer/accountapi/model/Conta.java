package br.multiplayer.accountapi.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.multiplayer.accountapi.enums.TipoConta;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"numero", "tipoConta"}, name = "conta_unique_key"))
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 20)
	private String numero;
	
	@Column(nullable = false, length = 20)
	private TipoConta tipoConta;
	
	@Column(nullable = false)
	private Double saldo = 0D;

	@Column(name="usuario_id", nullable=false)
	private Integer usuarioId;
	
	// Default constructor
	public Conta() {}
	
	public Conta(String numero, TipoConta tipo) {
		this.numero = numero;
		this.tipoConta = tipo;
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

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public Integer getUsuarioId() {
		return usuarioId;
	}
	
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
}
