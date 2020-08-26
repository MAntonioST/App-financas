package com.app.financas.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.core.business.helper.UsuarioHelper;
import com.app.financas.domain.Usuario;

@RestController
@RequestMapping("/api")
public class UsuarioController extends BaseController<Usuario, String> {

	public UsuarioController() {
		super(Usuario.class);

	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveOrUpdate(@RequestBody UsuarioHelper usuarioHelper) {
		
		Usuario aEntity = new Usuario();
		aEntity.setNome(usuarioHelper.getNome());
		aEntity.setEmail(usuarioHelper.getEmail());
		aEntity.setSenha(usuarioHelper.getSenha());
		appBusinessCase = appFacade.save(aEntity);
		if (appBusinessCase.hasMsg())
			return ResponseEntity.badRequest().body(appBusinessCase.getMsg());
		else if (!appBusinessCase.hasMsg()) {
			return ResponseEntity.ok().body(appBusinessCase.getEntity());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@RequestMapping(value = "findbyUsuarios", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> findByUsuario(@RequestBody UsuarioHelper usuarioHelpe) {

		BusinessCase<?> appBusinessCase = appFacade.find(usuarioHelpe);
		if (appBusinessCase.hasMsg())
			return ResponseEntity.badRequest().body(appBusinessCase.getMsg());
		if (appBusinessCase.hasEntities()) {
			return ResponseEntity.ok().body(appBusinessCase.getEntities());
		} else if (appBusinessCase.hasEntity()) {
			return ResponseEntity.ok().body(appBusinessCase.getEntity());
		} else
			return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> authenticatedUsuario(@RequestBody UsuarioHelper usuarioHelper) {
			Usuario aEntity = new Usuario();
			aEntity.setEmail(usuarioHelper.getEmail());
			aEntity.setSenha(usuarioHelper.getSenha());
			appBusinessCase = appFacade.update(aEntity);
			if (!appBusinessCase.hasMsg())
			return ResponseEntity.ok().body(appBusinessCase.getEntity());
			else
			return ResponseEntity.badRequest().body(appBusinessCase.getMsg());

	}
	
	@RequestMapping(value = "{id}/saldo", method = RequestMethod.GET)
	public ResponseEntity<?> obterSaldo(@PathVariable("id") Long id) {
		Usuario  aEntity = new Usuario();
		aEntity.setId(id);
		appBusinessCase = appFacade.findAll(aEntity);
		if (appBusinessCase.hasMsg())
			return ResponseEntity.badRequest().body(appBusinessCase.getMsg());
		else if (!appBusinessCase.hasMsg()) {
			return ResponseEntity.ok().body(appBusinessCase.getEntity());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

}
