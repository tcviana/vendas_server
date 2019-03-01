package br.com.blog.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.blog.model.orm.Pessoa;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class PessoaBuilder {
	private List<Pessoa> pessoas = new ArrayList<>();
		
	private static Pessoa create(final Long num) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(num);
		pessoa.setNome("Pessoa "+num);
		pessoa.setCpf("12345678910");
		pessoa.setEndereco("Rua teste");
		pessoa.setAtivo(true);
		
		return pessoa;
	}	
	
	public PessoaBuilder add(final int quantidade) {
		for (Long i=1l;i<=quantidade;i++) {
			pessoas.add(create(i));
		}
		return this;
	}
	
	public List<Pessoa> buildAll(){
		return pessoas;
	}
	
	public Pessoa buildOne() {
		return pessoas.get(0);
	}
}
