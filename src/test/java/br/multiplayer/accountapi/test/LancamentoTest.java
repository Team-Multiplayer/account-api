package br.multiplayer.accountapi.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.multiplayer.accountapi.enums.TipoLancamento;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.PlanoConta;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.service.LancamentoService;
import br.multiplayer.accountapi.service.UsuarioService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class LancamentoTest {
	
	@TestConfiguration
	static class LancamentoTestConfiguration {
		@Bean
		public LancamentoService lancamentoService() {
			return new LancamentoService();
		}
		@Bean
		public UsuarioService usuarioService() {
			return new UsuarioService();
		}
	}

	@Autowired
	private LancamentoService lancamentoService;
	@Autowired
	private UsuarioService usuarioService;
	
	// Lançamento
	Date dataLancamento;
	String descricao;
	Double valor;
	String conta;
	TipoLancamento tipo;
	String contaDestino;

	// Usuário
	Usuario usuario;
	String nome;
	String cpf;
	String login;
	String senha;

	@BeforeEach
	void antesDeTodosTestes() {
		nome = "Danilo Elias";
		cpf = "37115975382";
		login = "danilo";
		senha = "pass1234";
		usuario = usuarioService.cadastrarUsuario(nome, cpf, login, senha);
	}
	
	@BeforeEach
	void antesDeCadaTeste() {
		// inicia dados do usuário
		dataLancamento = new Date();
		descricao = "Pgto Conta Luz";
		valor = 56.80;
		conta = "danilo";
		tipo = TipoLancamento.DEBITO;
		contaDestino = null;
	}

	@Test
	void cadastrarLancamento() {
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
		
		
		lancamentoService.novoLancamento(login, valor, descricao, tipo, null, contaDestino);
	}

}
