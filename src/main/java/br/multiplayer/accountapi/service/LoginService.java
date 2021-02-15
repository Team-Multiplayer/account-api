package br.multiplayer.accountapi.service;

import java.util.List;

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
		List<Usuario> lu = repoUsuario.findByLogin(loginDto.getLogin());
		// se retorno algum usuário
		if (!lu.isEmpty()) {
			// pega o usuário retornado
			Usuario u = lu.get(0);
			
			// Comparação de senhas com BCrypt
			boolean validPassword = passwordEncoder.matches(loginDto.getSenha(), u.getSenha());
			
			if (validPassword) {
				return u;
			}
		}
		return null;
	}
	
	public Usuario efetuarLogin(LoginDto loginDto) {
		
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
			
			String hashedPassword = passwordEncoder.encode(loginDto.getSenha());
			
			if (u.getSenha().equals(hashedPassword)) {
				return u;
			}
		}
		
		throw new LoginOuSenhaInvalidosException();
	}
}
