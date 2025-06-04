package com.projetoAnderShow.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoAnderShow.course.entities.User;

@RestController
@RequestMapping("/users")
public class UserResources {
	
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Maria", "mariaggmail.com", "559555999", "12345");
		return ResponseEntity.ok().body(u);
	}
	
	
	
}
