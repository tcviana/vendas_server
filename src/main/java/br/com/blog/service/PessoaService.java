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
import br.com.blog.model.orm.Pessoa;
import br.com.blog.repository.PessoaRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/service/pessoa/")
public class PessoaService {

	@Autowired
	private PessoaRepository repository; 
	
	@PostMapping
	public @ResponseBody ResponseModel post(@RequestBody final Pessoa pessoa){		
		try {
			
			this.repository.save(pessoa);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
			
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());			
		}
	}
	
	@PutMapping
	public @ResponseBody ResponseModel put(@RequestBody final Pessoa pessoa){
		
		try {
			
			this.repository.save(pessoa);		
			
			return new ResponseModel(1,"Registro atualizado com sucesso!");
		
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	public @ResponseBody Pessoa get(@PathVariable final Long id){
		
		return this.repository.findById(id);
		
	}
	
	@GetMapping
	public @ResponseBody List<Pessoa> getAll(){
		
		return this.repository.findAll();
		
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
