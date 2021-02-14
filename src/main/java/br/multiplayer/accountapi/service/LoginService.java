package br.multiplayer.accountapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.multiplayer.accountapi.dto.LoginDto;
import br.multiplayer.accountapi.exception.LoginOuSenhaInvalidosException;

import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.repository.UsuarioRepository;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioRepository repoUsuario;

	public Boolean validarLogin(LoginDto loginDto) {
		
		if (loginDto.getLogin() == null || loginDto.getSenha() == null) {
			throw new IllegalArgumentException();
		}
		
		// busca usuário por login
		List<Usuario> lu = repoUsuario.findByLogin(loginDto.getLogin());
		// se retorno algum usuário
		if (!lu.isEmpty()) {
			// pega o usuário retornado
			Usuario u = lu.get(0);
			// compara a senha passada com a senha do usuário cadastrado
			// TODO hash da senha
			if (u.getSenha().equals(loginDto.getSenha())) {
				return true;
			}
		}
		return false;
	}
	
	public Usuario efetuarLogin(String login, String senha) {
		
		if (login == null || senha == null) {
			throw new IllegalArgumentException();
		}
		
		// busca usuário por login
		List<Usuario> lu = repoUsuario.findByLogin(login);
		// se retorno algum usuário
		if (!lu.isEmpty()) {
			// pega o usuário retornado
			Usuario u = lu.get(0);
			// compara a senha passada com a senha do usuário cadastrado
			// TODO hash da senha
			if (u.getSenha().equals(senha)) {
				return u;
			}
		}
		
		throw new LoginOuSenhaInvalidosException();
	}
	
	

}
