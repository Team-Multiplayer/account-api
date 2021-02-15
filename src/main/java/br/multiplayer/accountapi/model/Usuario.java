package br.multiplayer.accountapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.multiplayer.accountapi.enums.TipoConta;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@Column(nullable = false, unique=true, length = 20)
	private String login;
	
	@Column(nullable = false)
	private String senha;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contaCorrente", referencedColumnName = "id")
	private Conta contaCorrente;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contaCredito", referencedColumnName = "id")
	private Conta contaCredito;
	
	// Default constructor
	public Usuario () {}
	
	public Usuario(
			String nome, 
			String cpf, 
			String login, 
			String senha) {
		
		this.nome = nome;
		this.cpf= cpf;
		this.login = login;
		this.senha = senha;
		this.contaCorrente = new Conta(TipoConta.CORRENTE);
		this.contaCredito = new Conta(TipoConta.CREDITO);
	};
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Conta getContaCorrente() {
		return contaCorrente;
	}
	
	public void setContaCorrente(Conta conta) {
		this.contaCorrente = conta;
	}
	
	public Conta getContaCredito() {
		return contaCredito;
	}
	
	public void setContaCredito(Conta conta) {
		this.contaCredito = conta;
	}
	
	
}
