package com.projetoAnderShow.course.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projetoAnderShow.course.entities.User;
import com.projetoAnderShow.course.repositories.UserRepository;
import com.projetoAnderShow.course.servicies.exceptions.DatabaseException;
import com.projetoAnderShow.course.servicies.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	public List<User> findAll(){
		return repository.findAll();
	}
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	public User insert(User obj) {
		return repository.save(obj);
	}
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) { //captura a exceção de não ter o id na hora de deletar 
			 throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	public User update(Long id, User obj) {
		try {
		User entity = repository.getReferenceById(id);// prepara o obj monitorado para depois mandar para o banco
		//vamos pegar o obj entity e atualizar com os dados recebidos pela função
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		//nem todos os campos serão atualizados por este método
	}
	
}
