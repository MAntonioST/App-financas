package com.app.financas.core;

import java.util.List;
import java.util.Optional;
import com.app.financas.domain.DomainEntity;


public interface IDAO<T extends DomainEntity, R extends Object> {
	
	Optional<T> findById(R id);
	Optional<T> save(T aEntity);
	Optional<T> update(T aEntity);
	Optional<T> delete(T aEntity);
	Optional<List<T>> findAll();
}
