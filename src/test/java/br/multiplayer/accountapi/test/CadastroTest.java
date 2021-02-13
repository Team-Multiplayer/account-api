package br.multiplayer.accountapi.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.multiplayer.accountapi.exception.LoginJaCadastradoException;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.repository.UsuarioRepository;
import br.multiplayer.accountapi.service.UsuarioService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CadastroTest {
	
	@TestConfiguration
	static class CadastroTestConfiguration {
		@Bean
		public UsuarioService usuarioService() {
			return new UsuarioService();
		}
	}

	@Autowired
	private UsuarioService usuarioService;
	
	String nome;
	String cpf;
	String login;
	String senha;

	@BeforeEach
	void antesDeCadaTeste() {
		// inicia dados do usuário
		nome = "Danilo Elias";
		cpf = "37115975382";
		login = "danilo";
		senha = "pass1234";
	}

	@Test
	@DisplayName("Nome nulo, esperado NullPointerException")
	public void nomeNulo() {
		assertThrows(NullPointerException.class, () -> {
			nome = null;
			// cadastra um usuário com login nulo
			usuarioService.cadastrarUsuario(nome, cpf, login, senha);
		});
		
	}

	@Test
	@DisplayName("CPF nulo, esperado NullPointerException")
	public void cpfNulo() {
		assertThrows(NullPointerException.class, () -> {
			cpf = null;
			// cadastra um usuário com login nulo
			usuarioService.cadastrarUsuario(nome, cpf, login, senha);
		});
		
	}

	@Test
	@DisplayName("Login nulo, esperado NullPointerException")
	public void loginNulo() {
		assertThrows(NullPointerException.class, () -> {
			login = null;
			// cadastra um usuário com login nulo
			usuarioService.cadastrarUsuario(nome, cpf, login, senha);
		});
		
	}

	@Test
	@DisplayName("Senha nula, esperado NullPointerException")
	public void SenhaNula() {
		assertThrows(NullPointerException.class, () -> {
			senha = null;
			// cadastra um usuário com senha nula
			usuarioService.cadastrarUsuario(nome, cpf, login, senha);
		});
		
	}

	@Test
	@DisplayName("Login fora do padrão, esperado IllegalArgumentException")
	public void loginForaDoPadrao() {
		assertThrows(IllegalArgumentException.class, () -> {
			// login com mais de 20 caracteres
			login = "123456789012345678901";
			// cadastra um usuário com login fora do padrão desejado
			usuarioService.cadastrarUsuario(nome, cpf, login, senha);
		});
	}

	@Test
	@DisplayName("CPF fora do padrão, esperado IllegalArgumentException")
	public void cpfForaDoPadrao() {
		assertThrows(IllegalArgumentException.class, () -> {
			// cpf com mais de 11 caracteres
			cpf = "999999999999";
			// cadastra um usuário com cpf fora do padrão desejado
			usuarioService.cadastrarUsuario(nome, cpf, login, senha);
		});
	}

	@Test
	@DisplayName("Cadastrar novo usuário, esperado usuário com login passado")
	public void cadastrarNovoUsuario() {
		// cadastra um usuário
		Usuario usuario = usuarioService.cadastrarUsuario(nome, cpf, login, senha);
		// deve retornar um usuário (não deve ser nulo)
		assertNotNull(usuario);
		assertEquals(usuario.getNome(), nome);
		assertEquals(usuario.getCpf(), cpf);
		assertEquals(usuario.getLogin(), login);
		assertEquals(usuario.getSenha(), senha);
		// pega a conta gerada para o usuário
		Conta conta = usuario.getContas();
		assertNotNull(conta);
		// a conta criada deve ter o login como identificador
		assertEquals(conta.getNumero(), login);
	}

	@Test
	@DisplayName("Cadastrar novo usuário com login já existente, esperado LoginJaCadastradoException")
	void loginJaCadastrado() {
		assertThrows(LoginJaCadastradoException.class, () -> {
			// cadastra um usuário
			usuarioService.cadastrarUsuario(nome, cpf, login, senha);
			// tenta cadastrar um usuário com o mesmo login
			usuarioService.cadastrarUsuario(nome, cpf, login, senha);
		});
	}

}
