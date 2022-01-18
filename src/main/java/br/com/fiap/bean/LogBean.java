package br.com.fiap.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.event.FlowEvent;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.model.CNH;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Endereco;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

@Named
@RequestScoped
public class LogBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente = new Cliente();
	private Endereco endereco = new Endereco();
	private CNH cnh = new CNH();
	
	private boolean isPcd; 
	
	private EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private ClienteDAO clienteDao = new ClienteDAOImpl(em);
	FacesContext context = FacesContext.getCurrentInstance();
	
	
	
	
	public String login() {
		Cliente cliente = clienteDao.exist(this.cliente);
		if (cliente != null) {
			context.getExternalContext().getSessionMap().put("cliente", cliente);
			context.getExternalContext().getSessionMap().put("email", cliente.getEmail());
			return "index?faces-redirect=true";
		}
		
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido", ""));
		return "login?faces-redirect=true";
	
	}
	
	public boolean verifyLog() {
		Cliente cliente = (Cliente) context.getExternalContext().getSessionMap().get("cliente");
		if(cliente != null) {
			this.cliente = cliente;
			return true;
		}
		
		return false;
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("cliente");
		return "login?faces-redirect=true";

	}
	
	public boolean isPcd() {
		return isPcd;
	}

	public void setPcd(boolean isPcd) {
		if(isPcd) {
			cnh.setIsPcd(1);
		}else {
			cnh.setIsPcd(0);
		}
		this.isPcd = isPcd;
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
	
	
	

}

