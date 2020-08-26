package com.app.financas.core.business.helper;



import com.app.financas.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class UsuarioHelper extends Usuario{

	private static final long serialVersionUID = 1L;
	
	
	private String email;
	private String nome;
	private String senha;

}
