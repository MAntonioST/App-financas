package com.app.financas.core;


import com.app.financas.core.aplicacao.BusinessCase;



public interface IStrategy<T> {

	public BusinessCase<T> process(T aEntity, BusinessCase<T> aCase);
	
}
