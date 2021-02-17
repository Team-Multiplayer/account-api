package br.multiplayer.accountapi.dto;

import java.util.Date;

import br.multiplayer.accountapi.model.Usuario;

public class SessaoDto {
	private Usuario usuario;
	private String login;
	private String token;
	private Date dataInicio;
	private Date dataFim;
	
//	Default 
	public SessaoDto() {
		
	}
	
	public SessaoDto(Usuario usuario, String login, String token, Date dataInicio, Date dataFim) {
		super();
		this.usuario = usuario;
		this.login = login;
		this.token = token;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
