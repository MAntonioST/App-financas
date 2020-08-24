package com.app.financas.core.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.financas.domain.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);
	public List<Usuario> findAll();
	Optional<Usuario> findById(Long id);
	boolean existsBySenha(String email);
	Optional<Usuario> findByEmail(String email);
	
}
