package br.multiplayer.accountapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.multiplayer.accountapi.model.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {
	List<Lancamento> findAllByDataBetween(LocalDate inicio, LocalDate fim);
}
