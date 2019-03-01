package br.com.blog.model.orm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@Table(name="pedido")
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@OneToMany(mappedBy="pedido", fetch = FetchType.EAGER)
	private Collection<PedidoItens> pedidoItens = new ArrayList<PedidoItens>();
	
	@ManyToOne //(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa",referencedColumnName="id")
	private Pessoa pessoa;
	
	@Column(name="dataAbertura")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd-yyyy")
	private Date dataAbertura;
}
