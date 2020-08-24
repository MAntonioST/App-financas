package com.app.financas.core.business.lancamento;




import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.financas.core.IStrategy;
import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.core.dao.LancamentoDAO;
import com.app.financas.core.dao.UsuarioDAO;
import com.app.financas.domain.Lancamento;
import com.app.financas.domain.Usuario;




@Component
public class FindByLancamento implements IStrategy<Lancamento> {
  
	@Autowired
    private LancamentoDAO lancamentoDAO ;
	@Autowired
    private UsuarioDAO usuarioDAO ;
	
	@Override
	public BusinessCase<Lancamento> process(Lancamento aEntity, BusinessCase<Lancamento> aCase) {
             
		Optional<Lancamento> lancamentoOptional = Optional.ofNullable(aEntity);
		if (!lancamentoOptional.isPresent()) {
			aCase.setMsg("Digite a busca");
			return aCase;
		}	
		
		 Optional<Usuario> usuarioId = usuarioDAO.findById(aEntity.getUsuario().getId());
		 if(!usuarioId.isPresent()) {
			aCase.setMsg("Não foi possível realizar a consulta. Usuário não encontrado para o Id informado.");
			return aCase;
		}else {
			aEntity.setUsuario(usuarioId.get());
		}
		if(aEntity.getTipo() != null) {
			aEntity.setTipo(aEntity.getTipo());
		}
		if(aEntity.getStatus() != null) {
			aEntity.setStatus(aEntity.getStatus());
		}
		List<Lancamento> filtro = lancamentoDAO.findFiltro(aEntity);
		Optional<List<Lancamento>> filtroOptional = Optional.ofNullable(filtro);
		if(filtroOptional.isPresent()) {
			aCase.setEntities(filtroOptional);
	        return aCase;
		}else {
			aCase.setMsg("Não foi possível encontrar Lancamentos, tente novamente");
			return aCase;
		}
	}
}
