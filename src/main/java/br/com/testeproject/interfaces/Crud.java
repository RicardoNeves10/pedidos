package br.com.testeproject.interfaces;

import java.util.List;

import br.com.testeproject.exceptions.AppException;

public interface Crud < T > {
	
	void salvar( T po ) throws AppException;

	void excluir( T po ) throws AppException;

	@SuppressWarnings("rawtypes")
	List filtrar( T po ) throws AppException;

	@SuppressWarnings("rawtypes")
	List filtrarTodos() throws AppException;

	T filtrarPorId( Long id ) throws AppException;

}
