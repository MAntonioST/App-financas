package com.app.financas.core.business.usuario;




import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.financas.core.IStrategy;
import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.core.dao.UsuarioDAO;
import com.app.financas.domain.Usuario;



@Component
public class FindByUsuario implements IStrategy<Usuario> {
  
	@Autowired
    private UsuarioDAO uDAO ;
	
	
	@Override
	public BusinessCase<Usuario> process(Usuario aEntity, BusinessCase<Usuario> aCase) {
             
        
              Optional<Usuario> userOptional = Optional.ofNullable(aEntity);
              if(userOptional.isPresent() && userOptional.get().getId() != null) {
      			Optional<Usuario> usuarioId = uDAO.findById(aEntity.getId());
      			if(usuarioId.isPresent())
      			    aCase.setEntity(usuarioId);
      			else 
      			    aCase.setMsg("Não foi encontrado cadastrado desse usuário");
      			
      		  }else { 
      			Optional<List<Usuario>> listUsuario =  uDAO.findAll();		
				if(listUsuario.isPresent()) 
      			    aCase.setEntities(listUsuario);
				else
					aCase.setMsg("Não há usuarios cadastrados!");	
      		  }
              return aCase;
	}
}
