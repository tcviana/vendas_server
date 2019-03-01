package br.com.blog.builder;

import java.util.ArrayList;
import java.util.Collection;

import br.com.blog.model.orm.Pedido;
import br.com.blog.model.orm.PedidoItens;
import br.com.blog.model.orm.Produto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PedidoItensBuilder {
	
	Collection<PedidoItens> itens = new ArrayList<>();
	
	public PedidoItensBuilder(final Pedido pedido) {
		PedidoItens item = create(pedido);
		itens.add(item);
	}
	
	private Produto getProduto() {
		return new ProdutoBuilder().add(1).buildOne();		
	}
	
	private Pedido getPedido() {
		return new PedidoBuilder().buildOne();
	}
	
	private PedidoItens create() {
		PedidoItens item = new PedidoItens();
		item.setPedido(getPedido());
		item.setProduto(getProduto());
		item.setQuantidade(5);
		
		return item;
	}
	
	private PedidoItens create(final Pedido pedido) {
		PedidoItens item = new PedidoItens();
		item.setPedido(pedido);
		item.setProduto(getProduto());
		item.setQuantidade(5);
		
		return item;
	}	
	
	public PedidoItens buildOne() {
		return create();
	}
	
	public Collection<PedidoItens> buildAll(){
		return itens;
	}

}
