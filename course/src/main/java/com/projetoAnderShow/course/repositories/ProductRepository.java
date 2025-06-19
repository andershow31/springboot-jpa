package com.projetoAnderShow.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoAnderShow.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
