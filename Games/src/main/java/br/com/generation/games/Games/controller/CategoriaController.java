package br.com.generation.games.Games.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.generation.games.Games.model.Categoria;
import br.com.generation.games.Games.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	

	@GetMapping
	public ResponseEntity<List<Categoria>> GetAll(){
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> GetById(@PathVariable Long id) {
	
		return categoriaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	
	}	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> getByDescricao(@PathVariable String descricao) {
		
		return ResponseEntity.ok(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	@PostMapping
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria descricao) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(descricao));
		
	}
	@PutMapping
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria descricao){
		
		return categoriaRepository.findById(descricao.getId()).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(descricao))); 
		
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		Optional<Categoria>categorias = categoriaRepository.findById(id);
		
		if(categorias.isEmpty()) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
	}
		categoriaRepository.deleteById(id);
		
}

	
}
