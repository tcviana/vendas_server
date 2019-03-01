package br.com.blog.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import br.com.blog.model.orm.Produto;

public interface ProdutoRepository extends Repository<Produto, Long>{
	
	void save(Produto produto);
	
	void delete(Produto produto);
	
	@Modifying @Transactional
	void deleteById(Long id);
	
	List<Produto> findAll();
	
	Produto findById(Long id);
	
	
}
