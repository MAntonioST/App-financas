package com.app.financas.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.app.financas.core.IFacade;
import com.app.financas.core.aplicacao.BusinessCase;
import com.app.financas.domain.DomainEntity;

public abstract class BaseController <T extends DomainEntity, R extends Object> {

	@Autowired
	@Qualifier("appFacade")
	protected IFacade<T, R> appFacade;

	@Autowired
	@Qualifier("businessCase")
	protected BusinessCase<T> appBusinessCase;
	
	protected Class<? extends T> clazz;

	public BaseController(Class<? extends T> clazz) {
		this.clazz = clazz;
	}
	
}

