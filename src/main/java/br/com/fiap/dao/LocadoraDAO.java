package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.exception.EntityNotFoundException;
import br.com.fiap.model.Locadora;

public interface LocadoraDAO extends GenericDao<Locadora, Long>{
	
	List<Locadora> orderByRate() throws EntityNotFoundException;
	
	List<Locadora> fourRandom() throws EntityNotFoundException;

}
