package br.com.blog.model.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@Table(name="pedidoitens")
@Entity
@NoArgsConstructor
public class PedidoItens {
	
	@Id 
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name="id_produto", referencedColumnName = "id")
	private Produto produto;
	
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name="id_pedido", referencedColumnName="id")
	@JsonIgnore // ignorar join recursivo
	private Pedido pedido;
	
	@Column(name="quantidade")
	private int quantidade;
}
