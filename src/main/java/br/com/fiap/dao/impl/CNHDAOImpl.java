package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.CNHDAO;
import br.com.fiap.model.CNH;

public class CNHDAOImpl extends GenericDaoImpl<CNH, Long> implements CNHDAO{
	
	public CNHDAOImpl(EntityManager em) {
		super(em);
	}

}
