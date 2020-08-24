package com.app.financas.core.business.helper;



import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UsuarioHelper{

	private String email;
	private String nome;
	private String senha;

}
