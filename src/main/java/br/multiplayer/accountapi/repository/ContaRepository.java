package br.multiplayer.accountapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.multiplayer.accountapi.enums.TipoConta;
import br.multiplayer.accountapi.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
	
	List<Conta> findByNumero(String numero);
	
	Optional<Conta> findFirstByNumeroAndTipoConta(String numero, TipoConta tipoConta);
	
	List<Conta> findByUsuarioId(Integer usuarioId);


}
