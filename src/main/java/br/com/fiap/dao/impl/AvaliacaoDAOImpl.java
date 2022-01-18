package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.AvaliacaoDAO;
import br.com.fiap.model.Avaliacao;

public class AvaliacaoDAOImpl extends GenericDaoImpl<Avaliacao, Long> implements AvaliacaoDAO{
	
	public AvaliacaoDAOImpl(EntityManager em) {
		super(em);
	}

}
