package br.com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.model.ResponseModel;
import br.com.blog.model.orm.Produto;
import br.com.blog.repository.ProdutoRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/service/produto/")
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository; 
	
	@PostMapping
	public @ResponseBody ResponseModel post(@RequestBody final Produto produto){		
		try {
			
			this.repository.save(produto);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
			
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());			
		}
	}
	
	@PutMapping
	public @ResponseBody ResponseModel put(@RequestBody final Produto produto){
		
		try {
			
			this.repository.save(produto);		
			
			return new ResponseModel(1,"Registro atualizado com sucesso!");
		
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	@GetMapping
	public @ResponseBody List<Produto> getAll(){
		
		return this.repository.findAll();
	}
	
	@GetMapping("{id}")	
	public @ResponseBody Produto get(@PathVariable final Long id){
		
		return this.repository.findById(id);
	}
	
	@DeleteMapping("{id}")
	public @ResponseBody ResponseModel delete(@PathVariable final Long id) {
		try {
			
			repository.deleteById(id);
			
			return new ResponseModel(1, "Registro excluido com sucesso!");
			
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}		
	}
	
}
