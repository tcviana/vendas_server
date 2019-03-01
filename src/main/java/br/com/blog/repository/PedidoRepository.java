package br.com.blog.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import br.com.blog.model.orm.Pedido;

public interface PedidoRepository extends Repository<Pedido, Long>{
	
	void save(Pedido pedido);
	
	void delete(Pedido pedido);
	
	@Modifying @Transactional
	void deleteById(Long id);
	
	Pedido findById(Long id);
	
	List<Pedido> findAll();

}
