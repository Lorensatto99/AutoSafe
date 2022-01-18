package br.com.fiap.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.EntityNotFoundException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public abstract class GenericDaoImpl<E,K> implements GenericDao<E, K> {
	
	private EntityManager em ;
	
	private Class<E> clazz; //Armazena o .class da Entidade

	@SuppressWarnings("unchecked")
	public GenericDaoImpl(EntityManager em) {
		this.em = em;
		this.clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public void create(E entidade) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(entidade);
		em.flush();
		t.commit();
	}

	@Override
	public E findById(K id) throws EntityNotFoundException {
		E entidade = em.find(clazz, id);
		if(entidade == null) {
			throw new EntityNotFoundException();
		}
		return entidade;
	}

	@Override
	public void update(E entidade) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.merge(entidade);
		em.flush();
		t.commit();
	}

	@Override
	public void delete(K id) throws EntityNotFoundException {
		E entidade = findById(id);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		System.out.println("Ativei o delete");
		E margedEntity = em.merge(entidade);
		em.remove(margedEntity);
		em.flush();
		tx.commit();
	}
	
	@Override
	public List<E> getAll() throws EntityNotFoundException {
		return em.createQuery(String.format("from %s", clazz.getName())).getResultList();
	}

}
