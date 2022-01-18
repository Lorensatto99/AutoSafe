package br.com.fiap.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente {
	
	@Id 
	@Column(name="cd_cliente", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;

	@Column(name="nm_cliente", nullable = false)
	private String nome;
	
	@Column(name="ds_email", nullable = false)
	private String email;
	
	@Column(name="nr_cpf", nullable = false)
	private String cpf;
	
	@Column(name = "nr_senha", nullable = false)
	private String senha;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="cd_endereco", nullable = false)
	private Endereco endereco;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="cd_cnh", nullable = false)
	private CNH cnh;
	

	@OneToMany(mappedBy = "cliente",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Avaliacao> avaliacoes;
	
	

	public Cliente() {
		super();
	}
	
	

	public Cliente(Long idCliente, String nome, String email, String cpf, String senha, Endereco endereco, CNH cnh,
			List<Avaliacao> avaliacoes) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		this.endereco = endereco;
		this.cnh = cnh;
		this.avaliacoes = avaliacoes;
	}
	
	



	public Cliente(String nome, String email, String cpf, String senha, Endereco endereco, CNH cnh,
			List<Avaliacao> avaliacoes) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		this.endereco = endereco;
		this.cnh = cnh;
		this.avaliacoes = avaliacoes;
	}



	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public CNH getCnh() {
		return cnh;
	}



	public void setCnh(CNH cnh) {
		this.cnh = cnh;
	}



	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}



	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf
				+ ", endereco=" + endereco + "]";
	}

	
}
