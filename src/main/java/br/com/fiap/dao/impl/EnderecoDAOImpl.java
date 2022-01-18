package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.EnderecoDAO;
import br.com.fiap.model.Endereco;

public class EnderecoDAOImpl extends GenericDaoImpl<Endereco, Long> implements EnderecoDAO{
	
	public EnderecoDAOImpl(EntityManager em) {
		super(em);
	}

}
