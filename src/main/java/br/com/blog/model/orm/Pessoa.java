package br.com.blog.model.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name="pessoa")
@Entity
@NoArgsConstructor
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="nome", length=255)
	private String  nome;
			
	@Column(name="ativo", columnDefinition="BIT")
	private boolean ativo;
	
	@Column(name="endereco", length=255)
	private String endereco;
	
	@Column(name="cpf", length=20)
	private String cpf;
		
}
