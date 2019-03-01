package br.com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.model.ResponseModel;
import br.com.blog.model.orm.Pedido;
import br.com.blog.repository.PedidoRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/service/pedido/")
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	
	@GetMapping("{id}")
	public @ResponseBody Pedido getById(@PathVariable final Long id) {
		return this.repository.findById(id);
	}
	
	@GetMapping
	public @ResponseBody List<Pedido> getAll(){
		return this.repository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseModel post(@RequestBody final Pedido pedido) {
		try {
			
			this.repository.save(pedido);		
			
			return new ResponseModel(1,"Registro inserido com sucesso!");
		
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());
		}		
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseModel put(@RequestBody final Pedido pedido) {
		try {
			
			this.repository.save(pedido);		
			
			return new ResponseModel(1,"Registro atualizado com sucesso!");
		
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());
		}		
	}
	
	@DeleteMapping("{id}")
	public @ResponseBody ResponseModel delete(@PathVariable final Long id) {
		try {
			
			this.repository.deleteById(id);
			
			return new ResponseModel(1, "Registro excluido com sucesso!");
			
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}		
	}	

}
