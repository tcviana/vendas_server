package br.com.blog.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import br.com.blog.model.orm.PedidoItens;

public interface PedidoItensRepository extends Repository<PedidoItens, Long>{
	
	void save(PedidoItens pedidoItens);
	
	void delete(PedidoItens pedidoItens);
	
	@Modifying @Transactional
	void deleteById(Long id);
	
	PedidoItens findById(Long id);
	
	List<PedidoItens> findAll();
	
}
