package br.multiplayer.accountapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import br.multiplayer.accountapi.enums.TipoConta;
import br.multiplayer.accountapi.exception.LoginJaCadastradoException;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
    private UserDetailsManager userDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	public List<Usuario> buscarTodos() {
		return repoUsuario.findAll();
	}
	
	public Optional<Usuario> buscarPorId(Integer id) {
		return repoUsuario.findById(id);
	}

	public List<Usuario> buscaPorLogin(String login) {
		return repoUsuario.findByLogin(login);
	}
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		
		// faz as validações do usuário
		// verifica se os dados foram passados
		if (usuario.getNome() == null || usuario.getCpf() == null || usuario.getLogin() == null ||	usuario.getSenha() == null ) {
			throw new NullPointerException("Usuário não pode ser criado.");
		}
		// verifica o tamanho do login
		if (usuario.getLogin().length() > 20) {
			throw new IllegalArgumentException("O login não deve conter mais que 20 caracteres.");
		}
		// verifica o tamanho do CPF
		if (usuario.getCpf().length() > 11) {
			throw new IllegalArgumentException("O CPF é inválido.");
		}
		
		// busca um usuário com o login passado
		if (!repoUsuario.findByLogin(usuario.getLogin()).isEmpty()) {
			// se encontrou um usuário
			// lançar uma exceção
			throw new LoginJaCadastradoException();
		}
		
		// TODO hash da senha
		
		String hashedPassword = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(hashedPassword);
		usuario.setContaCorrente(new Conta(usuario.getLogin(), TipoConta.CORRENTE));
		usuario.setContaCredito(new Conta(usuario.getLogin() + "-1", TipoConta.CREDITO));
		
		// se tudo correu bem cria o usuário
		// salva no repositório
		return repoUsuario.save(usuario);
	}
}
