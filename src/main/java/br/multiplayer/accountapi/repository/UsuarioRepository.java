package br.multiplayer.accountapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.multiplayer.accountapi.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	boolean existsByLogin(String login);
	List<Usuario> findByLogin(String login);
}
