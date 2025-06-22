package com.projetoAnderShow.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name= "tb_product")
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	//@Transient //o @Transeiente é como se dissesse que a linha abaixo é transitória, e não é interpretada pelo programa
	
	@ManyToMany //anotação para indicar ao jpa que é uma associação muitos pra muitos
	@JoinTable(name = "tb_product_category", 
	joinColumns = @JoinColumn(name = "product_id"),//essa anotação da join nas tabelas, e o joinColumn indica a chave estrangeira
	inverseJoinColumns = @JoinColumn(name = "category_id"))//o inversejoincolumns é a chave estrangeira da outra categoria, nesse caso a categoria
	private Set<Category> categories = new HashSet<>();
	//dentro do produto temos uma série de categorias
	//uso do set pois não repete os itens
	
	@OneToMany(mappedBy="id.product") //indica que esta sendo mapeado pelo product do order instanciado dentro do orderitempk
	private Set<OrderItem> items = new HashSet<>(); //usaremos essa coleção para mapear as orders associadas a cada produto
	
	public Product() {
		
	}
	
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		for (OrderItem x: items) {
			set.add(x.getOrder());
		}
		return set;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
	


}
