package br.multiplayer.accountapi.dto;

public class ExtratoDto {
	private String login;
	private ContaDto contaCorrente;
	private ContaDto contaCredito;
	public ExtratoDto(String login, ContaDto contaCorrente, ContaDto contaCredito) {
		super();
		this.login = login;
		this.contaCorrente = contaCorrente;
		this.contaCredito = contaCredito;
	}
	public ExtratoDto() {
		super();
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public ContaDto getContaCorrente() {
		return contaCorrente;
	}
	public void setContaCorrente(ContaDto contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
	public ContaDto getContaCredito() {
		return contaCredito;
	}
	public void setContaCredito(ContaDto contaCredito) {
		this.contaCredito = contaCredito;
	}
	
	
}
