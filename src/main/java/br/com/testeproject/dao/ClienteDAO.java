package br.com.testeproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.testeproject.connections.ConnectionFactory;
import br.com.testeproject.model.Cliente;

public class ClienteDAO {
	
	public final Cliente salvar(Cliente cliente) {
		EntityManager em = new ConnectionFactory().getConnection();
		
		try {
			em.getTransaction().begin();
			
			if (cliente.getId() == null) {
				em.persist(cliente);
			} else {
				em.merge(cliente);
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
		return cliente;
	}

	public final void excluir(Cliente cliente) {
		EntityManager em = new ConnectionFactory().getConnection();
		
		try {
			em.getTransaction().begin();
			cliente = em.find(Cliente.class, cliente.getId());
			em.remove(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public final Cliente filtrarPorID(Long id) {
		EntityManager em = new ConnectionFactory().getConnection();
		Cliente cliente = null;
		
		try {
			cliente = em.find(Cliente.class, id);
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
		return cliente;
	}
	
	public Boolean verificarCadastro(Cliente cliente) {
		EntityManager em = new ConnectionFactory().getConnection();
		
		try {
			Query query = em.createNativeQuery("SELECT nome FROM clientes WHERE nome like '" + cliente.getNome().toString() + "';");
			
			if (query.getSingleResult() != null) {
				return Boolean.TRUE;
			}
		} catch (NoResultException e) {
			return Boolean.FALSE;
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrarTodos() {
		EntityManager em = new ConnectionFactory().getConnection();
		List<Cliente> listaClientes = null;
		
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
			Root<Cliente> root = criteriaQuery.from(Cliente.class);
			criteriaQuery.select(root);
			Query query = em.createQuery(criteriaQuery);
			listaClientes = query.getResultList();
		} catch (NoResultException e) {
			listaClientes = null;
		} catch (Exception e) {
			listaClientes = null;
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
		return listaClientes;
	}

}
