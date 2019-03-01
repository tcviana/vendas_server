package br.com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.blog.model.orm.Pessoa;

public interface PessoaRepository extends Repository<Pessoa, Long> {

	void save(Pessoa pessoa);
	
	void delete(Pessoa pessoa);
	
	@Modifying @Transactional
	void deleteById(Long id);
	
	List<Pessoa> findAll();
	
	Pessoa findById(Long id);
}
