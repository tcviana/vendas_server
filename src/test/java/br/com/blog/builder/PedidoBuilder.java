package br.com.blog.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.blog.model.orm.Pedido;
import br.com.blog.model.orm.PedidoItens;
import br.com.blog.model.orm.Pessoa;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PedidoBuilder {
	List<Pedido> pedidos = new ArrayList<>();
	
	private Pessoa getPessoa() {
		return new PessoaBuilder().add(1).buildOne();
	}	
	
	private Pedido create(final Long num) {
		Pedido pedido = new Pedido();
		pedido.setId(num);
		pedido.setPessoa(getPessoa());
		
		return pedido;
	}
	
	public PedidoBuilder add(final int qtde) {
		for(Long i=1l;i<=qtde;i++) {
			pedidos.add(create(i));
		}
		return this;
	}
	
	public PedidoBuilder addItem() {
		for (Pedido pedido : pedidos) {
			Collection<PedidoItens> itens = new PedidoItensBuilder(pedido).buildAll();
			pedido.setPedidoItens(itens);
		}
		return this;
	}
	
	public Pedido buildOne() {
		return pedidos.get(0);
	}
	
	public List<Pedido> buildAll(){
		return pedidos;
	}

}
