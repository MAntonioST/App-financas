package com.app.financas.core;


import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.domain.DomainEntity;
import com.app.financas.domain.IEntity;


public interface IFacade<T extends DomainEntity, R extends Object> extends IEntity {
	
	public BusinessCase<T> save(T aEntity);
	public BusinessCase<T> update(T aEntity);
	public BusinessCase<T> delete(T aEntity);
	public BusinessCase<T> findAll(T aEntity);
	public BusinessCase<T> find(T aEntity);



}
