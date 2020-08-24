package com.app.financas.domain;

import java.io.Serializable;
import javax.persistence.*;




@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public  class DomainEntity implements IEntity, Serializable  {

	private static final long serialVersionUID = 1L;

	
}
