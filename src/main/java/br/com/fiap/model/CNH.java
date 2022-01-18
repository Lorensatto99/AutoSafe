package br.com.fiap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CNH {

	@Id 
	@Column(name="cd_cnh", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCnh;
	
	@Column(name="nr_cnh", nullable = false)
	private String numero;
	
	@Column(name="tp_cnh", nullable = false)
	private String tipo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_validade", nullable = false)
	private Date validade;
	
	@Column(name="is_pcd", nullable = false)
	private int isPcd;
	
	@OneToOne(mappedBy = "cnh")
	private Cliente cliente;

	public Long getIdCnh() {
		return idCnh;
	}

	public void setIdCnh(Long idCnh) {
		this.idCnh = idCnh;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public int getIsPcd() {
		return isPcd;
	}

	public void setIsPcd(int isPcd) {
		this.isPcd = isPcd;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "CNH [idCnh=" + idCnh + ", numero=" + numero + ", tipo=" + tipo + ", validade=" + validade + ", isPcd="
				+ isPcd + ", cliente=" + cliente + "]";
	}

}
