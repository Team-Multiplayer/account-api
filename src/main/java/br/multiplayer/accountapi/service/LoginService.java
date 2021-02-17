package br.multiplayer.accountapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.multiplayer.accountapi.configuration.JWTConstants;
import br.multiplayer.accountapi.dto.LoginDto;
import br.multiplayer.accountapi.dto.SessaoDto;
import br.multiplayer.accountapi.exception.LoginOuSenhaInvalidosException;
import br.multiplayer.accountapi.model.Conta;
import br.multiplayer.accountapi.model.Usuario;
import br.multiplayer.accountapi.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository repoUsuario;

	public SessaoDto validarLogin(LoginDto loginDto) {
		
		if (loginDto.getLogin() == null || loginDto.getSenha() == null) {
			throw new IllegalArgumentException();
		}
		
		// busca usuário por login
		Optional<Usuario> usuarioPorLogin = repoUsuario.findFirstByLogin(loginDto.getLogin());
		// se retorno algum usuário
		if (usuarioPorLogin.isPresent()) {
			// pega o usuário retornado
			Usuario usuario = usuarioPorLogin.get();
			
			// Comparação de senhas com BCrypt
			boolean validPassword = passwordEncoder.matches(loginDto.getSenha(), usuario.getSenha());
			
			if (!validPassword) {
				throw new RuntimeException("Senha inválida para o login: " + loginDto.getLogin());
			}
			
			// Geração do JWT
			long tempoToken = 1 * 60 * 60 * 1000;
			SessaoDto sessao = new SessaoDto();
			
			sessao.setDataInicio(new Date(System.currentTimeMillis()));
			sessao.setDataFim(new Date(System.currentTimeMillis() + tempoToken));
			sessao.setLogin(usuario.getLogin());
			sessao.setToken(JWTConstants.PREFIX + getJWTToken(sessao));
			usuario.setSenha(null);
			sessao.setUsuario(usuario);

			return sessao;
			
		}
		// se não acho o usuário ou a senha estiver errada
		throw new LoginOuSenhaInvalidosException();
	}
	
	private String getJWTToken(SessaoDto sessao) {
		String role = "ROLE_USER";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

		String token = Jwts.builder().setSubject(sessao.getLogin())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(sessao.getDataInicio()).setExpiration(sessao.getDataFim())
				.signWith(SignatureAlgorithm.HS512, JWTConstants.KEY.getBytes()).compact();

		return token;
	}
}
