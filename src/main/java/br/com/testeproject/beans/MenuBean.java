package br.com.testeproject.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MenuBean {

	public String redirecionarIndex() {
		return "/index.xhtml?faces-redirect=true";
	}
	
	public String redirecionarCliente() {
		return "/pages/clientes.xhtml?faces-redirect=true";
	}
	
}
