package com.app.financas.core.business.lancamento;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.financas.core.IStrategy;
import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.core.dao.LancamentoDAO;
import com.app.financas.domain.Lancamento;



@Component
public class AtualizaStatusLancamento implements IStrategy<Lancamento> {

	@Autowired
	private LancamentoDAO lancamentoDAO;

	@Override
	public BusinessCase<Lancamento> process(Lancamento aEntity, BusinessCase<Lancamento> aCase) {

		if (aEntity != null && aEntity.getId() != null) {

			Optional<Lancamento> lancamentoId = lancamentoDAO.findById(aEntity.getId());

			if (lancamentoId.isPresent()) {			
				lancamentoId.get().setStatus(aEntity.getStatus());
				lancamentoDAO.update(lancamentoId.get());
				aCase.setEntity(lancamentoId);
				return aCase;
			}else 
				aCase.setMsg("Não foi possível atualizar o status do lançamento, envie um status válido." );
			    return aCase;
			}

		return aCase;
	}

}
