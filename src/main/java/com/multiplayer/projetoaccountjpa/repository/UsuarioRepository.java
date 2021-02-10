package com.multiplayer.projetoaccountjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multiplayer.projetoaccountjpa.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Boolean existsByLogin(String login);
	List<Usuario> findByLogin(String login);
}
