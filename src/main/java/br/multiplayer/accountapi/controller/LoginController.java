package br.multiplayer.accountapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.multiplayer.accountapi.dto.LoginDto;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired 
	private LoginService loginService;

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Usuario loginUsuario(@Validated @RequestBody LoginDto loginDto) {
		 
		return loginService.validarLogin(loginDto);
	}
}
