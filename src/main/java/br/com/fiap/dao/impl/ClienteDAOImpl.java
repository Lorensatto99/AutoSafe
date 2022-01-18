package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.model.Cliente;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class ClienteDAOImpl extends GenericDaoImpl<Cliente, Long> implements ClienteDAO{
	
	EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();
	
	public ClienteDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public Cliente exist(Cliente cliente) {
		try {
			TypedQuery<Cliente> query = manager.createQuery(
					"SELECT u FROM Cliente u WHERE "
					+ "u.email = :email AND "
					+ "u.senha = :senha", Cliente.class);
			query.setParameter("email", cliente.getEmail());
			query.setParameter("senha", cliente.getSenha());
			cliente = query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return cliente;
		
	}
	
	@Override
	public long findBymail(String email) {
		TypedQuery<Cliente> query = manager.createQuery("SELECT u FROM Cliente u WHERE "+ "u.email = :email", Cliente.class);
		query.setParameter("email", email);
		return query.getSingleResult().getIdCliente();
	}

}
