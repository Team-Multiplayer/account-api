package br.multiplayer.accountapi.dto;

import java.time.LocalDate;

public class ExtratoRequestDto {
	
	private String login;
	private LocalDate inicio;
	private LocalDate fim;
	public ExtratoRequestDto() {
		super();
	}
	public ExtratoRequestDto(String login, LocalDate inicio, LocalDate fim) {
		super();
		this.login = login;
		this.inicio = inicio;
		this.fim = fim;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public LocalDate getInicio() {
		return inicio;
	}
	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}
	public LocalDate getFim() {
		return fim;
	}
	public void setFim(LocalDate fim) {
		this.fim = fim;
	}
	
	

}
