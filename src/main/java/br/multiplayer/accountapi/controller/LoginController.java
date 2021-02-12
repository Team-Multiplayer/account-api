package br.multiplayer.accountapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.multiplayer.accountapi.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired private LoginService loginService;

	@PostMapping()
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Boolean loginUsuario(@Validated @RequestParam String login, @RequestParam String senha) {
		 
		return loginService.validarLogin(login, senha);
	}
	
}
