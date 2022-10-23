package br.com.testeproject.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testeproject.controller.ClienteController;
import br.com.testeproject.exceptions.AppException;
import br.com.testeproject.model.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 8556338577270740328L;
	
	public Cliente cliente;
	public ClienteController clienteController;
	public ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	
	public static Boolean FLAG_EDITAR;
	
	public ClienteBean() {
		
	}
	
	@PostConstruct
	public void init() {
		if (cliente == null && clienteController == null) {
			cliente = new Cliente();
			clienteController = new ClienteController();
		}
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> getListaClientes() {
		return clienteController.filtrarTodos();
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public void salvar() throws AppException { 
		cliente.setDataNascimento(new Date());
		clienteController.salvar(cliente);
		limparCampos();
	}
	
	public void editar(Cliente cliente) throws AppException {
		this.cliente = cliente;
		FLAG_EDITAR = Boolean.TRUE;
	}
	
	public void excluir() throws AppException {
		clienteController.excluir(cliente);
		limparCampos();
	}
	
	public void limparCampos() {
		cliente = new Cliente();
		FLAG_EDITAR = Boolean.FALSE;
	}

}
