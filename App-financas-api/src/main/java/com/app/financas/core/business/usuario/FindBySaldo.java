package com.app.financas.core.business.usuario;




import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.financas.core.IStrategy;
import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.core.dao.LancamentoDAO;
import com.app.financas.core.dao.UsuarioDAO;
import com.app.financas.domain.Usuario;



@Component
public class FindBySaldo implements IStrategy<Usuario> {
  
	@Autowired
    private UsuarioDAO usuarioDAO ;
	@Autowired
    private LancamentoDAO lancamentoDAO ;
	
	
	@Override
	public BusinessCase<Usuario> process(Usuario aEntity, BusinessCase<Usuario> aCase) {
             
		
		 Optional<Usuario> usuarioId = usuarioDAO.findById(aEntity.getId());
		 if(!usuarioId.isPresent()) {
			aCase.setMsg("Não foi possível realizar a consulta do saldo. Usuário não encontrado para o Id informado.");
			return aCase;
		}
		BigDecimal saldo = lancamentoDAO.obterSaldoPorUsuario(aEntity.getId());
		Optional<BigDecimal> saldoOptional = Optional.ofNullable(saldo);
		if(saldoOptional.isPresent()) {
			aCase.addBigDecimal(saldoOptional);
	        return aCase;
		}else {
			aCase.setMsg("Não foi possível calcular o saldo, tente novamente");
			return aCase;
		}
	}
}
