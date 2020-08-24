package com.app.financas.core.business.usuario;



import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.financas.core.IStrategy;
import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.core.dao.UsuarioDAO;
import com.app.financas.domain.Usuario;


@Component
public class ValidUsuario implements IStrategy<Usuario> {
  
	@Autowired
	private  UsuarioDAO usuarioDAO ;
	
	@Override
	public BusinessCase<Usuario> process(Usuario aEntity, BusinessCase<Usuario> aCase) {
              
        
        Optional<Usuario> userOptional = Optional.ofNullable(aEntity);
        if(!userOptional.isPresent()) {
        	aCase.setMsg( "Não é possível salvar um objeto nulo");
        	 return aCase;
        }
        
               
        if (userOptional.get().getNome() == null || userOptional.get().getNome().isEmpty()) {
        	aCase.setMsg( "Informe um nome válido ");
        	return aCase;
        }
       
        if (userOptional.get().getEmail() == null || userOptional.get().getEmail().isEmpty()) {
        	aCase.setMsg( "Informe um Email válido");
        	return aCase;
        }
      
        if (userOptional.get().getSenha() == null || userOptional.get().getSenha().isEmpty()) {
        	aCase.setMsg("Informe uma senha válida");
        	return aCase;
        }
        
        if(usuarioDAO.validarEmail(aEntity)){
        	aCase.setMsg("Já existe um usuário cadastrado com esse email!");
        	return aCase;
        }
        
       
        if (aEntity != null && aEntity.getId() != null) {
			
			Optional<Usuario>	usuarioId = usuarioDAO.findById(aEntity.getId());
			
			if(usuarioId.isPresent()) {
				usuarioDAO.update(userOptional.get());
			    aCase.setEntity(userOptional);
			    return aCase;
			}
        }
        else {
        	    usuarioDAO.save(userOptional.get());
			    aCase.setEntity(userOptional);
			    return aCase;
			}
		return aCase;
       
	}

}
