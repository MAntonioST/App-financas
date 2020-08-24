package com.app.financas.core.business.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.financas.core.IStrategy;
import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.core.dao.UsuarioDAO;
import com.app.financas.domain.Usuario;


@Component
public class AuthenticateUsuario implements IStrategy<Usuario> {

	@Autowired
    private UsuarioDAO uDAO ;
	
	
	
	@Override
	public BusinessCase<Usuario> process(Usuario aEntity, BusinessCase<Usuario> aCase) {
		
		Optional<Usuario> userOptional = Optional.ofNullable(aEntity);
        if(!userOptional.isPresent()) {
        	 aCase.setMsg("Usuário não pode estar nulo!");
       	     return aCase;
        }
       
        if (userOptional.get().getEmail() == null || userOptional.get().getEmail().isEmpty()) {
        	aCase.setMsg( "Informe o Email");
        	return aCase;
        }
      
        if (userOptional.get().getSenha() == null || userOptional.get().getSenha().isEmpty()) {
        	aCase.setMsg("Informe a senha");
        	return aCase;
        }
        
        Optional<Usuario> usuarioOptional = uDAO.findByEmail(aEntity.getEmail());
        if(!usuarioOptional.isPresent()){
        	aCase.setMsg("Usuário não encontrado para o email informado!");
        	return aCase;
        }
        
        if(!usuarioOptional.get().getSenha().equals(aEntity.getSenha())){
        	aCase.setMsg("Senha inválida!");
        	return aCase;
        }
   	    
        aCase.setEntity(usuarioOptional);
        return aCase;
	}

}
