package br.com.testeproject.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.primefaces.PrimeFaces;

import br.com.testeproject.beans.ClienteBean;
import br.com.testeproject.dao.ClienteDAO;
import br.com.testeproject.exceptions.AppException;
import br.com.testeproject.interfaces.Crud;
import br.com.testeproject.model.Cliente;
import br.com.testeproject.utilities.MessagesView;

public class ClienteController implements Crud< Cliente >, Serializable {

	private static final long serialVersionUID = 7044840790532469018L;
	
	private final ClienteDAO clienteDAO;
	
	public ClienteController() {
		clienteDAO = new ClienteDAO();
	}
	
	@Override
	public void salvar( Cliente cliente ) throws AppException {
		try {
			if ( (clienteDAO.verificarCadastro(cliente) == Boolean.TRUE) && ( ClienteBean.FLAG_EDITAR == Boolean.FALSE) ) { 
				throw new AppException( "Registro já cadastrado!" );
			}
			
//			if ( new Date().before( cliente.getDataNascimento() ) ) {
//				throw new AppException( "Data de nascimento inválida!");
//			}

			clienteDAO.salvar( cliente );
			PrimeFaces.current().executeScript("PF('salvar-modal').show()");
		} catch ( AppException e ) {
			MessagesView.warn(e.getMessage());
			throw e;
		} catch ( Exception e ) {
			MessagesView.error("ERRO AO SALVAR!");
		}
	}

	@Override
	public void excluir(Cliente cliente) {
		try {
			clienteDAO.excluir( cliente ); 
			PrimeFaces.current().executeScript("PF('excluir-modal').show()");
		} catch ( Exception e ) {
			MessagesView.error("ERRO AO EXCLUIR!");
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List filtrar(Cliente po) {
		return null;
	}

	@Override
	public List<Cliente> filtrarTodos() {
		try {
			return clienteDAO.filtrarTodos();
		} catch ( Exception e ) {
			MessagesView.error("ERRO AO FILTRAR!");
		}
		
		return null;
	}

	@Override
	public Cliente filtrarPorId(Long id) {
		try {
			return clienteDAO.filtrarPorID(id);
		} catch ( Exception e ) {
			MessagesView.error("ERRO AO FILTRAR!");
		}
		
		return null;
	}
	
}
