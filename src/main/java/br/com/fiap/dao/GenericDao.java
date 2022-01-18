package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.EntityNotFoundException;

//GenericDao<Produto,Integer>

public interface GenericDao<E, K> {
	
	void create(E entidade);
	
	E findById(K id) throws EntityNotFoundException;
	
	void update(E entidade);
	
	void delete(K id) throws EntityNotFoundException;
	
	List<E> getAll() throws EntityNotFoundException;
	
	
}
