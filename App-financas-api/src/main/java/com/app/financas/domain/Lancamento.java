package com.app.financas.domain;



import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import com.app.financas.domain.enun.StatusLancamento;
import com.app.financas.domain.Usuario;
import com.app.financas.domain.enun.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "lancamento", schema = "financas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Lancamento extends DomainEntity {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LANCAMENTO")
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "MES")
	private Integer mes;
	
	@Column(name = "ANO")
	private Integer ano;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@Column(name = "VALOR")
	private BigDecimal valor;
	
	@Column(name = "DATA_CADASTRO")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCadastro;
	
	@Column(name = "TIPO")
	@Enumerated(value = EnumType.STRING)
	private TipoLancamento tipo;
	
	@Column(name = "STATUS")
	@Enumerated(value = EnumType.STRING)
	private StatusLancamento status;

}
