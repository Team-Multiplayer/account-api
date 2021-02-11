package com.multiplayer.projetoaccountjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiplayer.projetoaccountjpa.exception.LoginJaCadastradoException;
import com.multiplayer.projetoaccountjpa.model.Usuario;
import com.multiplayer.projetoaccountjpa.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	public List<Usuario> buscarTodos() {
		return repoUsuario.findAll();
	}
	
	public Optional<Usuario> buscarPorId(Integer id) {
		return repoUsuario.findById(id);
	}

	public Usuario cadastrarUsuario(String nome, String cpf, String login, String senha) {
		
		// faz as validações do usuário
		// verifica se os dados foram passados
		if (nome == null || cpf == null || login == null ||	senha == null ) {
			throw new NullPointerException("Usuário não pode ser criado.");
		}
		// verifica o tamanho do login
		if (login.length() > 20) {
			throw new IllegalArgumentException("O login não deve conter mais que 20 caracteres.");
		}
		// verifica o tamanho do CPF
		if (cpf.length() > 11) {
			throw new IllegalArgumentException("O CPF é inválido.");
		}
		
		// busca um usuário com o login passado
		if (!repoUsuario.findByLogin(login).isEmpty()) {
			// se encontrou um usuário
			// lançar uma exceção
			throw new LoginJaCadastradoException();
		}
		
		// se tudo correu bem cria o usuário
		// TODO hash da senha
		Usuario u = new Usuario(nome, cpf, login, senha);
		// se conseguiu criar o usuário
		if (u != null) {
			// salva no repositório
			return u = repoUsuario.save(u);
		}
		// em caso de algum problema na criação retorna nulo
		return null;
	}
}
