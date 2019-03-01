package br.com.blog.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.blog.model.orm.Produto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProdutoBuilder {
	private List<Produto> produtos = new ArrayList<>();
		
	private static Produto create(final Long num) {
		Produto produto = new Produto();
		produto.setId(num);
		produto.setNome("Produto "+num);
		produto.setValor(10);
		
		return produto;
	}	
	
	public ProdutoBuilder add(final int quantidade) {
		for (Long i=1l;i<=quantidade;i++) {
			produtos.add(create(i));
		}
		return this;
	}
	
	public List<Produto> buildAll(){
		return produtos;
	}
	
	public Produto buildOne() {
		return produtos.get(0);
	}
}
