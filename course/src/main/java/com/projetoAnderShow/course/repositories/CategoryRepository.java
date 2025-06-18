package com.projetoAnderShow.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoAnderShow.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
