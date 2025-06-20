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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "tb_category")
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //dessa forma o java ira gerar os valores do id conforme a estratégia escolhida
	private Long id;
	
	private String name;
	
	//@Transient //o @Transeiente é como se dissesse que a linha abaixo é transitória, e não é interpretada pelo programa
	@JsonIgnore //evita aquele loop no postman
	@ManyToMany(mappedBy = "categories")//aqui indicamos que category foi mapeado pelo categories (a linha que contém o set na classe product private Set<Category> categories = new HashSet<>();())
	private Set<Product> products = new HashSet<>();
	//Set é interface, não instancia
	
	
	public Category() {
		
	}
	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
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
	public Set<Product> getProducts() {
		return products;
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}


	
}
