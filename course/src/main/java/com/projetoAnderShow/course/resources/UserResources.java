package com.projetoAnderShow.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetoAnderShow.course.entities.User;
import com.projetoAnderShow.course.servicies.UserService;

//Define que esta classe é um controlador REST, ou seja, irá responder a requisições HTTP
@RestController

//Define o caminho base para todas as requisições deste controlador: "/users"
@RequestMapping("/users")
public class UserResources {

	// Injeta automaticamente uma instância de UserService (injeção de dependência)
	@Autowired
	private UserService service;

	// Também é possível realizar a injeção de dependência via construtor
	// criando um construtor dentro desta classe

	// Mapeia requisições GET para o caminho "/users"
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		// Chama o serviço para buscar todos os usuários
		List<User> list = service.findAll();
		// Retorna a lista de usuários com status HTTP 200 (OK)
		return ResponseEntity.ok().body(list);
	}

	// Mapeia requisições GET para o caminho "/users/{id}"
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		// Busca um usuário pelo ID passado na URL
		User obj = service.findById(id);
		// Retorna o usuário encontrado com status HTTP 200 (OK)
		return ResponseEntity.ok().body(obj);
	}

	// Mapeia requisições POST para o caminho "/users"
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		// Insere um novo usuário no banco de dados
		obj = service.insert(obj);
		// Cria a URI do novo recurso criado, com base no ID gerado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		// Retorna o usuário criado com status HTTP 201 (Created) e a URI no cabeçalho
		return ResponseEntity.created(uri).body(obj);
	}

	// Mapeia requisições DELETE para o caminho "/users/{id}"
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		// Exclui o usuário com o ID especificado
		service.delete(id);
		// Retorna status HTTP 204 (No Content), indicando que a operação foi
		// bem-sucedida
		return ResponseEntity.noContent().build();
	}

	// Mapeia requisições PUT para o caminho "/users/{id}"
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		// Atualiza os dados do usuário com o ID especificado
		obj = service.update(id, obj);
		// Retorna o usuário atualizado com status HTTP 200 (OK)
		return ResponseEntity.ok().body(obj);
	}
}
