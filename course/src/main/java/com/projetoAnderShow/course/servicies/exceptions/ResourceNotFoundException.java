package com.projetoAnderShow.course.servicies.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException(Object id) {
		super("Resource not found. id: " + id);
		//o super faz com que possamos usar a classe mãe, porém com a nossa mensagem nesse caso
	}
}
