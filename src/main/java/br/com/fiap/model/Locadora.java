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
public class Locadora {

	@Id 
	@Column(name="cd_locadora", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLocadora;

	@Column(name="nm_locadora", nullable = false)
	private String nome;
	
	@Column(name="ds_locadora", nullable = false)
	private String descricao;
	
	@Column(name="nt_locadora", nullable = false)
	private int notaLocadora;
	
	@Column(name = "nm_link")
	private String link;
	
	@Column(name = "vl_locadora")
	private double x;
	
	@Column(name = "hl_locadora")
	private double y;

	@OneToMany(mappedBy = "locadora", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Avaliacao> avaliacoes;
	


	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getIdLocadora() {
		return idLocadora;
	}

	public void setIdLocadora(Long idLocadora) {
		this.idLocadora = idLocadora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNotaLocadora() {
		
		if(avaliacoes.size() == 0) return this.notaLocadora;
		
		
		float intMedia = 0;
		int somaNota = 0;
		int contador = 0;
		
		for (Avaliacao avaliacao : avaliacoes) {
			contador += 1;
			somaNota += avaliacao.getNota();
		}
		
		intMedia = somaNota/contador;
		
		return Math.round(intMedia);
	}

	public void setNotaLocadora(int notaLocadora) {
		this.notaLocadora = notaLocadora;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	
}
