package br.com.cfc.gestor.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Conta;
import br.com.cfc.gestor.model.enuns.TipoDespesaEnum;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long>{

	@Query("select c from Conta c where c.dataVencimento >= :inicio and c.dataVencimento <= :fim")
	Iterable<Conta> findByPeriodo(@Param("inicio") LocalDate inicio,  @Param("fim") LocalDate fim);
	
	@Query("select c from Conta c where c.despesa.tipoDespesa = :tipoDespesa")
	Iterable<Conta> findByTipoDespesa(@Param("tipoDespesa") TipoDespesaEnum tipoDespesa);
	
	@Query("select c from Conta c where c.dataVencimento >= :inicio and c.dataVencimento <= :fim and c.despesa.tipoDespesa = :tipoDespesa")
	Iterable<Conta> findByTipoDespesaPeriodo(@Param("inicio") LocalDate inicio,  @Param("fim") LocalDate fim, @Param("tipoDespesa") TipoDespesaEnum tipoDespesa);
}
