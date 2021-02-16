package br.multiplayer.accountapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.multiplayer.accountapi.dto.LoginDto;
import br.multiplayer.accountapi.exception.LoginOuSenhaInvalidosException;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.repository.UsuarioRepository;

@Service
public class LoginService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository repoUsuario;

	public Usuario validarLogin(LoginDto loginDto) {
		
		if (loginDto.getLogin() == null || loginDto.getSenha() == null) {
			throw new IllegalArgumentException();
		}
		
		// busca usuário por login
		Optional<Usuario> usuarioPorLogin = repoUsuario.findFirstByLogin(loginDto.getLogin());
		// se retorno algum usuário
		if (usuarioPorLogin != null) {
			// pega o usuário retornado
			Usuario usuario = usuarioPorLogin.get();
			
			// Comparação de senhas com BCrypt
			boolean validPassword = passwordEncoder.matches(loginDto.getSenha(), usuario.getSenha());
			
			// se as senha são iguais retorna o usuário
			if (validPassword) {
				return usuario;
			}
		}
		// senão acho o usuário ou a senha estiver errada
		throw new LoginOuSenhaInvalidosException();
	}
}
