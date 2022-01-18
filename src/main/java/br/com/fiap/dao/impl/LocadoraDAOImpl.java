package br.com.fiap.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.LocadoraDAO;
import br.com.fiap.exception.EntityNotFoundException;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Locadora;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class LocadoraDAOImpl extends GenericDaoImpl<Locadora, Long> implements LocadoraDAO{

	EntityManager manager = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public LocadoraDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Locadora> orderByRate() {
		List<Locadora> locadoras = new ArrayList<Locadora>();

		TypedQuery<Locadora> query = manager.createQuery(
				"SELECT u FROM Locadora u ORDER BY nt_locadora DESC"
				, Locadora.class);
		try {
			locadoras = query.getResultList();
			System.out.println(locadoras);
		} catch (NoResultException e) {
			return null;
		}

		return locadoras;
	}

	@Override
	public List<Locadora> fourRandom() throws EntityNotFoundException {
		List<Locadora> locadoras = new ArrayList<Locadora>();

		TypedQuery<Locadora> query = manager.createQuery(
				"SELECT u FROM Locadora u"
				, Locadora.class).setMaxResults(4);
		try {
			locadoras = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}

		return locadoras;
	}
}
