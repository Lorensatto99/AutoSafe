package br.com.fiap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Endereco {

	@Id 
	@Column(name="cd_endereco", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEndereco;
	
	@Column(name="nm_endereco", nullable = false)
	private String nome;
	
	@Column(name="nr_endereco", nullable = false)
	private String numero;
	
	@Column(name="nm_bairro", nullable = false)
	private String bairro;
	
	@Column(name="nr_cep", nullable = false)
	private String cep;
	
	@OneToOne(mappedBy = "endereco")
	private Cliente cliente;

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Endereco [idEndereco=" + idEndereco + ", nome=" + nome + ", numero=" + numero + ", bairro=" + bairro
				+ ", cep=" + cep + ", cliente=" + cliente + "]";
	}
	
}
