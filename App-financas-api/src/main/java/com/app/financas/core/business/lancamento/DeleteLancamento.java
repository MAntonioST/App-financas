package com.app.financas.core.business.lancamento;





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
public class DeleteLancamento implements IStrategy<Lancamento> {
  
	@Autowired
    private LancamentoDAO lancamentoDAO ;
	@Autowired
    private UsuarioDAO usuarioDAO ;
	
	@Override
	public BusinessCase<Lancamento> process(Lancamento aEntity, BusinessCase<Lancamento> aCase) {
             
		Optional<Lancamento> lancamentoOptional = Optional.ofNullable(aEntity);
		if (!lancamentoOptional.isPresent()) {
			aCase.setMsg("Digite o Lancamento a ser deletado");
			return aCase;
		}	
		
		 Optional<Lancamento> lancamentoId = lancamentoDAO.findById(aEntity.getId());
		
		if(lancamentoId.isPresent()) {
			lancamentoDAO.delete(lancamentoId.get());
			aCase.setMsg("Excluido com sucesso");
	        return aCase;
		}else {
			aCase.setMsg("Não foi possível encontrar Lancamentos, tente novamente");
			return aCase;
		}
	}
}
