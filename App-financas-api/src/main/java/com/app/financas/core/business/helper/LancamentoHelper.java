package com.app.financas.core.business.helper;

import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;




@Data
@EqualsAndHashCode(callSuper=false)
public class LancamentoHelper {

	
	private Long id;
	private String descricao;
	private Integer mes;
	private Integer ano;
	private BigDecimal valor;
	private Long usuario;
	private String tipo;
	private String status;
}
