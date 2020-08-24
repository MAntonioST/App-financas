package com.app.financas.core.dao;



import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.financas.core.IDAO;
import com.app.financas.domain.DomainEntity;



@Service
@Transactional
public abstract class AbstractDAO<T extends DomainEntity, R extends Object> implements IDAO<T, R>   {
	
	
	@Autowired
	private JpaRepository<T, R> repository;
	

	
	@Override
	public Optional<T> save(T aEntity) {
		aEntity = repository.save(aEntity);
		return Optional.of(aEntity);
	}
	
	@Override
	public Optional<T> update(T aEntity) {
		return save(aEntity);
	}
	
	@Override
	public Optional<T> findById(R id) {
		return repository.findById(id);
	}
	
	
	public Optional<List<T>> findAll() {	
		List<T> listEntity = repository.findAll();
		return Optional.ofNullable(listEntity);
	}

	@Override
	public Optional<T> delete(T aEntity) {
		repository.delete(aEntity);
		return Optional.of(aEntity);
	}

	
	
	public JpaRepository<T, R> getRepository() {
		return repository;
		
	}
}
