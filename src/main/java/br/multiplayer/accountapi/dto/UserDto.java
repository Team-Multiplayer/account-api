package br.multiplayer.accountapi.dto;

public class UserDto {
	private String cpf;
	private String nome;
	private String login;
	private String senha;
	
//	Default constructor
	public UserDto() {
		
	}
	
	public UserDto(String cpf, String nome, String login, String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
}
