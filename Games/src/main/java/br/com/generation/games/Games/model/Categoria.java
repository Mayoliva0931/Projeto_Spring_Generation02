package br.com.generation.games.Games.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=5, max= 500)
	private String descricao;
	
	@NotBlank
	@Size(min=5, max= 500)
	private String Genero;
	
	@Size(min = 3 , max = 50)
	private String console;
	
	@NotBlank(message = "Faixa Etaria Obrigatorio")
	@Size(min = 3 , max = 50)
	private String faixaIdade;
	
	@OneToMany(mappedBy= "categorias", cascade=CascadeType.ALL)
	@JsonIgnoreProperties("categorias")
	private List<Games> games;
	

	public List<Games> getGames() {
		return games;
	}
	public void setGames(List<Games> games) {
		this.games = games;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getGenero() {
		return Genero;
	}
	public void setGenero(String genero) {
		Genero = genero;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public String getFaixaIdade() {
		return faixaIdade;
	}
	public void setFaixaIdade(String faixaIdade) {
		this.faixaIdade = faixaIdade;
	}
	
	
}
