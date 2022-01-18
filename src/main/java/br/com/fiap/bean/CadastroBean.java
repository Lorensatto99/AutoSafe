package br.com.fiap.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.event.FlowEvent;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.exception.CommitException;
import br.com.fiap.model.CNH;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Endereco;
import br.com.fiap.singleton.EntityManagerFactorySingleton;


@Named
@ViewScoped
public class CadastroBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean skip = false;
	private Cliente cliente = new Cliente();
	private Endereco endereco = new Endereco();
	private CNH cnh = new CNH();

	private boolean isPcd;
	private EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private ClienteDAO clienteDao = new ClienteDAOImpl(em);
	FacesContext context = FacesContext.getCurrentInstance();


	public String cadastrar() throws CommitException {

		cliente.setEndereco(endereco);
		cliente.setCnh(cnh);

		clienteDao.create(cliente);
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage("Usuário cadastrado com sucesso"));
		context.getExternalContext().getSessionMap().put("cliente", cliente);

		return "index?faces-redirect=true";
	}

	public String onFlowProcess(FlowEvent event) {
		if(skip) {
			skip = false;	//reset in case user goes back
			return "confirm";
		}
		else {
			return event.getNewStep();
		}
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public CNH getCnh() {
		return cnh;
	}

	public void setCnh(CNH cnh) {
		this.cnh = cnh;
	}

	public boolean isPcd() {
		return isPcd;
	}

	public void setPcd(boolean isPcd) {
		this.isPcd = isPcd;
	}

	public ClienteDAO getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(ClienteDAO clienteDao) {
		this.clienteDao = clienteDao;
	}
	
	
	
	

}
