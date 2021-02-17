package br.multiplayer.accountapi.dto;

import java.util.Date;

public class SessaoDto {
	private String login;
	private String token;
	private Date dataInicio;
	private Date dataFim;
	
//	Default 
	public SessaoDto() {
		
	}
	
	public SessaoDto(String login, String token, Date dataInicio, Date dataFim) {
		super();
		this.login = login;
		this.token = token;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
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
