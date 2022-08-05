package br.com.generation.games.Games.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_Games")
public class Games {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome Obrigatorio")
	@Size(min = 3 , max = 50)
	private String nome;
	
	@NotNull(message = "Preço Obrigatorio")
	private Integer preco;
	
	@Size(min = 3 , max = 50)
	private String produtora;
	
	@NotBlank(message = "Faixa Etaria Obrigatorio")
	@Size(min = 3 , max = 50)
	private String produto;
	
	@ManyToOne
	@JsonIgnoreProperties("categorias")
	private Categoria categorias;
	
	
	public Categoria getCategorias() {
		return categorias;
	}
	public void setCategorias(Categoria categorias) {
		this.categorias = categorias;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPreco() {
		return preco;
	}
	public void setPreco(Integer preco) {
		this.preco = preco;
	}
	public String getProdutora() {
		return produtora;
	}
	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}

}
