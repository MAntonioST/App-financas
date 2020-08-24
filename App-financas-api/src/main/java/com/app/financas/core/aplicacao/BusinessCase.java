package com.app.financas.core.aplicacao;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;


@Component
public class BusinessCase<T> extends ApplicationEntity {

	private String msg;
	private Optional<List<T>> entities;
	private Optional<T> entity;

	
	
	public BusinessCase() {
	
	}


	public BusinessCase(String msg, Optional<List<T>> entities, Optional<T> entity) {
		this.msg = msg;
		this.entities = entities;
		this.entity = entity;
	}

	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Optional<T> getEntity() {
		return entity;
	}
	public void setEntity(Optional<T> entity) {
		this.entity = entity;
	}

	public Optional<List<T>> getEntities() {
		return entities;
	}

	public void setEntities(Optional<List<T>> listUsuario) {
		this.entities = listUsuario;

	}

	public boolean hasMsg() {
		return !(msg == null) && !(msg.isEmpty());
	}
	
	public boolean hasEntities() {
		return !(entities == null) && !(entities.isEmpty());
	}
	
	public boolean hasEntity() {
		return !(entity == null) && !(entity.isEmpty());
	}


	@SuppressWarnings("unchecked")
	public void addBigDecimal(Optional<BigDecimal> entity) {
		this.entity = (Optional<T>) entity;
		
	}
	
}