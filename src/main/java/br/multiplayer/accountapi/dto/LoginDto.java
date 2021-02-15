package br.multiplayer.accountapi.dto;

public class LoginDto {
	private String login;
	private String senha;
	 
//	Default constructor
	public LoginDto() {}
	
	public LoginDto(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
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
