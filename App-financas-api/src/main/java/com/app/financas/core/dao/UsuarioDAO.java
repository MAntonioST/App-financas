package com.app.financas.core.dao;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.financas.core.repository.UsuarioRepository;
import com.app.financas.domain.Usuario;



@Repository("UsuarioDAO")
@Transactional
public class UsuarioDAO extends AbstractDAO<Usuario, Long>{
	
@Autowired
private UsuarioRepository repository;
	

	public boolean validarEmail(Usuario usuario) {
		return repository.existsByEmail(usuario.getEmail());
	}
	
	public boolean validarSenha(Usuario usuario) {
		return repository.existsBySenha(usuario.getSenha());
	}

	public Optional<Usuario> findByEmail(String email) {
		return repository.findByEmail(email);
	}	
}
