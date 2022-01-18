package br.com.fiap.dao;

import br.com.fiap.model.Cliente;

public interface ClienteDAO extends GenericDao<Cliente, Long>{

	Cliente exist(Cliente cliente);

	long findBymail(String email);

}
