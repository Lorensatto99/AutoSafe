package br.com.fiap.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Avaliacao {

	@Id
	@Column(name="cd_avaliacao", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAvaliacao;

	@Column(name="ds_avaliacao", nullable = false)
	private String descricao;
	
	@Column(name="nr_avaliacao", nullable = false)
	private int nota;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_cliente")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_locadora")
	private Locadora locadora;

	public Long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Locadora getLocadora() {
		return locadora;
	}

	public void setLocadora(Locadora locadora) {
		this.locadora = locadora;
	}

	@Override
	public String toString() {
		return "Avaliacao [idAvaliacao=" + idAvaliacao + ", descricao=" + descricao + ", nota=" + nota + ", cliente="
				+ cliente.getNome() + ", locadora=" + locadora.getNome() + "]";
	}
	
}
