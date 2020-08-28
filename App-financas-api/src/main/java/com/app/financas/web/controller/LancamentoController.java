package com.app.financas.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.core.business.helper.AtualizaStatusHelper;
import com.app.financas.domain.Lancamento;
import com.app.financas.domain.Usuario;
import com.app.financas.domain.enun.StatusLancamento;


@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController extends BaseController<Lancamento, String> {

	public LancamentoController() {
		super(Lancamento.class);

	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveOrUpdate(@RequestBody Lancamento aEntity) {
		appBusinessCase = appFacade.save(aEntity);
		if (appBusinessCase.hasMsg())
			return ResponseEntity.badRequest().body(appBusinessCase.getMsg());
		else if (!appBusinessCase.hasMsg()) {
			return ResponseEntity.ok().body(appBusinessCase.getEntity());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping
	public ResponseEntity<?> findByLancamentos(
					@RequestParam(value ="descricao" , required = false) String descricao,
					@RequestParam(value = "mes", required = false) Integer mes,
					@RequestParam(value = "ano", required = false) Integer ano,
					@RequestParam("usuario") Long idUsuario
					) {
		
		Lancamento aEntityFiltro = new Lancamento();
		aEntityFiltro.setDescricao(descricao);
		aEntityFiltro.setMes(mes);
		aEntityFiltro.setAno(ano);
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		aEntityFiltro.setUsuario(usuario);

		BusinessCase<?> appBusinessCase = appFacade.findAll(aEntityFiltro);
		if (appBusinessCase.hasMsg())
			return ResponseEntity.badRequest().body(appBusinessCase.getMsg());
		if (appBusinessCase.hasEntities()) {
			return ResponseEntity.ok().body(appBusinessCase.getEntities());
		} else if (appBusinessCase.hasEntity()) {
			return ResponseEntity.ok().body(appBusinessCase.getEntity());
		} else
			return ResponseEntity.noContent().build();

	}
	
	@PutMapping("{id}/atualiza-status")
	public @ResponseBody ResponseEntity<?> updateStatus( @PathVariable("id") Long id , @RequestBody AtualizaStatusHelper statuHelper) {
		Lancamento aEntity = new Lancamento();
	    StatusLancamento statusLancamento = StatusLancamento.valueOf(statuHelper.getStatus());
	    aEntity.setId(id);
	    aEntity.setStatus(statusLancamento);
	    
	    appBusinessCase = appFacade.update(aEntity);
		if (appBusinessCase.hasMsg())
			return ResponseEntity.badRequest().body(appBusinessCase.getMsg());
		else if (!appBusinessCase.hasMsg()) {
			return ResponseEntity.ok().body(appBusinessCase.getEntity());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@DeleteMapping("{id}/deletar")
	public  @ResponseBody ResponseEntity<?> deletar( @PathVariable("id") Long id ) {
		Lancamento aEntity = new Lancamento();
		 aEntity.setId(id);
		 appBusinessCase = appFacade.delete(aEntity);
			if (appBusinessCase.hasMsg())
				return ResponseEntity.badRequest().body(appBusinessCase.getMsg());
			else if (!appBusinessCase.hasMsg()) {
				return ResponseEntity.ok().body(appBusinessCase.getEntity());
			} else {
				return ResponseEntity.noContent().build();
			}
		
		
	}
	
	
	
}
