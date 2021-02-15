package br.multiplayer.accountapi.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
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
	
	@MockBean
	private UsuarioRepository repoUsuario;

	private String nome;
	private String cpf;
	private String login;
	private String senha;
	private Usuario usuario;

	@BeforeEach
	void antesDeCadaTeste() {
		// inicia dados do usuário
		nome = "Danilo Elias";
		cpf = "37115975382";
		login = "danilo";
		senha = "pass1234";
		usuario = new Usuario(nome,cpf, login, senha);
		
		
	}

	@Test
	@DisplayName("Nome nulo, esperado NullPointerException")
	public void nomeNulo() {
		assertThrows(NullPointerException.class, () -> {
			usuario.setNome(null);
			// cadastra um usuário com login nulo
			usuarioService.cadastrarUsuario(usuario);
		});
		
	}

	@Test
	@DisplayName("CPF nulo, esperado NullPointerException")
	public void cpfNulo() {
		assertThrows(NullPointerException.class, () -> {
			usuario.setCpf(null);
			// cadastra um usuário com login nulo
			usuarioService.cadastrarUsuario(usuario);
		});
		
	}

	@Test
	@DisplayName("Login nulo, esperado NullPointerException")
	public void loginNulo() {
		assertThrows(NullPointerException.class, () -> {
			usuario.setLogin(null);
			// cadastra um usuário com login nulo
			usuarioService.cadastrarUsuario(usuario);
		});
		
	}

	@Test
	@DisplayName("Senha nula, esperado NullPointerException")
	public void SenhaNula() {
		assertThrows(NullPointerException.class, () -> {
			usuario.setSenha(null);
			// cadastra um usuário com senha nula
			usuarioService.cadastrarUsuario(usuario);
		});
		
	}

	@Test
	@DisplayName("Login fora do padrão, esperado IllegalArgumentException")
	public void loginForaDoPadrao() {
		assertThrows(IllegalArgumentException.class, () -> {
			// login com mais de 20 caracteres
			usuario.setLogin("123456789012345678901");
			// cadastra um usuário com login fora do padrão desejado
			usuarioService.cadastrarUsuario(usuario);
		});
	}

	@Test
	@DisplayName("CPF fora do padrão, esperado IllegalArgumentException")
	public void cpfForaDoPadrao() {
		assertThrows(IllegalArgumentException.class, () -> {
			// cpf com mais de 11 caracteres
			usuario.setCpf("999999999999");
			// cadastra um usuário com cpf fora do padrão desejado
			usuarioService.cadastrarUsuario(usuario);
		});
	}

	@Test
	@DisplayName("Cadastrar novo usuário, esperado usuário com login passado")
	public void cadastrarNovoUsuario() {
		Mockito.when(repoUsuario.findByLogin(usuario.getLogin())).thenReturn(new ArrayList<Usuario>());
		Mockito.when(repoUsuario.save(usuario)).thenReturn(usuario);
		// cadastra um usuário
		usuario = usuarioService.cadastrarUsuario(usuario);
		// deve retornar um usuário (não deve ser nulo)
		assertNotNull(usuario);
		assertEquals(usuario.getNome(), nome);
		assertEquals(usuario.getCpf(), cpf);
		assertEquals(usuario.getLogin(), login);
		assertEquals(usuario.getSenha(), senha);
		// pega a conta gerada para o usuário
		Conta conta = usuario.getContaCorrente();
		assertNotNull(conta);
		// a conta criada deve ter o login como identificador
		assertEquals(conta.getNumero(), login);
	}

	@Test
	@DisplayName("Cadastrar novo usuário com login já existente, esperado LoginJaCadastradoException")
	void loginJaCadastrado() {
		assertThrows(LoginJaCadastradoException.class, () -> {
			Mockito.when(repoUsuario.findByLogin(usuario.getLogin())).thenReturn(List.of(usuario));
			// tenta cadastrar um usuário com o mesmo login
			usuarioService.cadastrarUsuario(usuario);
		});
	}

}
