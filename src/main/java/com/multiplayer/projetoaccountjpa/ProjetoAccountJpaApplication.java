package com.multiplayer.projetoaccountjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.multiplayer.projetoaccountjpa.model.Usuario;
import com.multiplayer.projetoaccountjpa.repository.UsuarioRepository;
import com.multiplayer.projetoaccountjpa.service.UsuarioService;

@SpringBootApplication
public class ProjetoAccountJpaApplication {

	public static void main(String[] args) {
//		 ConfigurableApplicationContext configurableApplicationContext = 
//				 SpringApplication.run(ProjetoAccountJpaApplication.class, args);
//		 UsuarioRepository usuarioRepository = 
//				 configurableApplicationContext.getBean(UsuarioRepository.class);
		SpringApplication.run(ProjetoAccountJpaApplication.class, args);
		
//		 Usuario newUser = new Usuario(1, "11094722421", "Lucas Villarim", "lvillarim", "123456", "contaBanco");
//		 UsuarioService usuarioService = new UsuarioService();
		 
//		 usuarioService.criaUsuario(newUser);
	}
}
