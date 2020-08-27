package com.app.financas.core.business.helper;

import java.math.BigDecimal;

import com.app.financas.domain.Lancamento;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class LancamentoHelper extends Lancamento{

	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String descricao;
	private Integer mes;
	private Integer ano;
	private BigDecimal valor;
	private Long usuario;
	private String tipo;
	private String status;
}
