package com.app.financas.core.dao;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.financas.core.repository.LancamentoRepository;
import com.app.financas.domain.Lancamento;
import com.app.financas.domain.enun.StatusLancamento;
import com.app.financas.domain.enun.TipoLancamento;


@Repository("LancamentoDAO")
@Transactional
public class LancamentoDAO extends AbstractDAO<Lancamento, Long> {

	@Autowired
	private LancamentoRepository repository;
	
	@Transactional(readOnly = true)
	public List<Lancamento> findFiltro(Lancamento filtro){
		Example<Lancamento> example = Example.of( filtro, 
				ExampleMatcher.matching()
					.withIgnoreCase()
					.withStringMatcher(StringMatcher.CONTAINING) );
		return repository.findAll(example);
		
	}
	

	public BigDecimal obterSaldoPorUsuario(Long id) {
		
		BigDecimal receitas = repository.obterSaldoPorTipoLancamentoEUsuarioEStatus(id, TipoLancamento.RECEITA, StatusLancamento.EFETIVADO);
		BigDecimal despesas = repository.obterSaldoPorTipoLancamentoEUsuarioEStatus(id, TipoLancamento.DESPESA, StatusLancamento.EFETIVADO);
		
		if(receitas == null) {
			receitas = BigDecimal.ZERO;
		}
		
		if(despesas == null) {
			despesas = BigDecimal.ZERO;
		}
		
		return receitas.subtract(despesas);
	}
	
}