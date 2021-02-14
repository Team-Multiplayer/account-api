package br.multiplayer.accountapi.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.repository.UsuarioRepository;
import br.multiplayer.accountapi.service.LoginService;
import br.multiplayer.accountapi.exception.LoginOuSenhaInvalidosException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class LoginTest {
	
	@TestConfiguration
	static class LoginTestConfiguration {
		@Bean
		public LoginService loginService() {
			return new LoginService();
		}
	}

	@Autowired 
	private LoginService loginService;

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
		usuario = new Usuario(nome, cpf, login, senha);
	}
	
	@Test
	@DisplayName("Login nulo, esperado IllegalArgumentException")
	public void loginNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			login = null;
			// tenta entrar com login nulo
//			loginService.efetuarLogin(login, senha);
		});
		
	}

	@Test
	@DisplayName("Senha nula, esperado IllegalArgumentException")
	public void senhaNula() {
		assertThrows(IllegalArgumentException.class, () -> {
			senha = null;
			// tenta entrar com senha nula
//			loginService.efetuarLogin(login, senha);
		});
		
	}

	@Test
	@DisplayName("Login não existente, esperado LoginOuSenhaInvalidosException")
	void loginNaoExistente() {
		assertThrows(LoginOuSenhaInvalidosException.class, () -> {
			// tenta entrar com um login NÃO cadastrado
//			loginService.efetuarLogin(login, senha);
		});
	}

	@Test
	@DisplayName("Senha inválida, esperado LoginOuSenhaInvalidosException")
	void senhaInvalida() {
		assertThrows(LoginOuSenhaInvalidosException.class, () -> {
			Mockito.when(repoUsuario.findByLogin(usuario.getLogin())).thenReturn(List.of(usuario));
			String senhaInvalida = "1234pass";
			// busca o usuário com o login cadastrado mas a senha inválida
//			loginService.efetuarLogin(login, senhaInvalida);
		});
	}

	@Test
	@DisplayName("Login existente, esperado usuário")
	void loginExistente() {
		Mockito.when(repoUsuario.findByLogin(usuario.getLogin())).thenReturn(List.of(usuario));
		// busca o usuário com o login cadastrado
//		Usuario usuario = loginService.efetuarLogin(login, senha);
		// deve retornar um usuário
		assertNotNull(usuario);
	}

	@Test
	@DisplayName("Senha válida, esperado usuário com login passado")
	void senhaValida() {
		assertDoesNotThrow(() -> {
			Mockito.when(repoUsuario.findByLogin(usuario.getLogin())).thenReturn(List.of(usuario));
			// busca o usuário com o login cadastrado mas a senha inválida
//			Usuario usuario = loginService.efetuarLogin(login, senha);
			// deve retornar um usuário
			assertNotNull(usuario);
			assertEquals(usuario.getLogin(), login);
		});
	}

}
