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

import br.com.generation.games.Games.model.Games;
import br.com.generation.games.Games.repository.GamesRepository;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GamesController {

	@Autowired
	private GamesRepository gamesRepository;
	
 
	@GetMapping
	public ResponseEntity<List <Games>> GetAll(){
		return ResponseEntity.ok(gamesRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Games> GetById(@PathVariable Long id) {
	
		return gamesRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Games>> getByNome(@PathVariable String nome) {
		
		return ResponseEntity.ok(gamesRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	@PostMapping
	public ResponseEntity<Games> post(@Valid @RequestBody Games  games) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(gamesRepository.save(games));
		
	}
	@PutMapping
	public ResponseEntity<Games> put(@Valid @RequestBody Games games){
		
		return gamesRepository.findById(games.getId()).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.OK).body(gamesRepository.save(games))); 
		
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		Optional<Games>produtos = gamesRepository.findById(id);
		
		if(produtos.isEmpty()) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
		}
		gamesRepository.deleteById(id);
		
	}
	
}
