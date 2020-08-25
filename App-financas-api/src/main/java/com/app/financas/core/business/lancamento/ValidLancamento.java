package com.app.financas.core.business.lancamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.financas.core.IStrategy;
import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.core.dao.LancamentoDAO;
import com.app.financas.domain.Lancamento;
import com.app.financas.domain.enun.StatusLancamento;

@Component
public class ValidLancamento implements IStrategy<Lancamento> {

	@Autowired
	private LancamentoDAO lancamentoDAO;

	@Override
	public BusinessCase<Lancamento> process(Lancamento aEntity, BusinessCase<Lancamento> aCase) {

		Optional<Lancamento> lancamentoOptional = Optional.ofNullable(aEntity);
		if (!lancamentoOptional.isPresent()) {
			aCase.setMsg("Não é possível salvar um objeto nulo");
			return aCase;
		}

		if (lancamentoOptional.get().getDescricao() == null
				|| lancamentoOptional.get().getDescricao().trim().equals("")) {
			aCase.setMsg("Informe uma Descrição válida.");
			return aCase;
		}

		if (lancamentoOptional.get().getMes() == null || lancamentoOptional.get().getMes() < 1
				|| lancamentoOptional.get().getMes() > 12) {
			aCase.setMsg("Informe um Mês válido.");
			return aCase;
		}

		if (lancamentoOptional.get().getAno() == null || lancamentoOptional.get().getAno().toString().length() != 4) {
			aCase.setMsg("Informe um Ano válido.");
			return aCase;
		}
          
		if (lancamentoOptional.get().getUsuario() == null || lancamentoOptional.get().getUsuario().getId() == null) {
			aCase.setMsg("Informe um Usuário.");
			return aCase;
		}

		if (lancamentoOptional.get().getValor() == null
				|| lancamentoOptional.get().getValor().compareTo(BigDecimal.ZERO) < 1) {
			aCase.setMsg("Informe um Valor válido.");
			return aCase;
		}

		if (lancamentoOptional.get().getTipo() == null) {
			aCase.setMsg("Informe um tipo de Lançamento.");
			return aCase;
		}
		if (aEntity != null && aEntity.getId() != null) {

			Optional<Lancamento> lancamentoId = lancamentoDAO.findById(aEntity.getId());

			if (lancamentoId.isPresent()) {
				lancamentoDAO.update(lancamentoOptional.get());
				aCase.setEntity(lancamentoOptional);
				return aCase;
			}
		} else {
			    lancamentoOptional.get().setDataCadastro(LocalDate.now());
				lancamentoOptional.get().setStatus(StatusLancamento.PENDENTE);
			    lancamentoDAO.save(lancamentoOptional.get());
				aCase.setEntity(lancamentoOptional);
				return aCase;
		}
		return aCase;
	}

}
