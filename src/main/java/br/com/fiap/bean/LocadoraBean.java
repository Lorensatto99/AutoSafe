package br.com.fiap.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.fiap.dao.AvaliacaoDAO;
import br.com.fiap.dao.LocadoraDAO;
import br.com.fiap.dao.impl.AvaliacaoDAOImpl;
import br.com.fiap.dao.impl.LocadoraDAOImpl;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.EntityNotFoundException;
import br.com.fiap.model.Avaliacao;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Locadora;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

@Named
@SessionScoped
public class LocadoraBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Locadora locadora = new Locadora();
	private Avaliacao avaliacao = new Avaliacao();

	private EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private LocadoraDAO locadoraDao = new LocadoraDAOImpl(em);
	private AvaliacaoDAO avalicaoDao = new AvaliacaoDAOImpl(em);

	
	private MapModel simpleModel;
	private String local;
	private List<String> images;
	private String imagem = "img1.jpg";

	FacesContext context = FacesContext.getCurrentInstance();

	
	public Cliente getSessionUser(){
    	Cliente cliente = (Cliente) context.getExternalContext().getSessionMap().get("cliente");
    	return cliente;
    }
	
	
	/*
	 * para passar os parametros de um formulario avulso no meio da página para o controler basta passar o parâmetro
	 * @form que todos os valores do formulário serão passados para o "Controler"
	 */
	public String avaliar() throws IOException, EntityNotFoundException, CommitException {
		
		this.avaliacao.setCliente(getSessionUser());
		this.avaliacao.setLocadora(locadora);
		
		avalicaoDao.create(avaliacao);
		List<Avaliacao> avaliacoes = this.locadora.getAvaliacoes();
		avaliacoes.add(this.avaliacao);
		this.locadora.setAvaliacoes(avaliacoes);
		
		return pageLocadora(this.avaliacao.getLocadora().getIdLocadora());		
	}
	
	public String remover(Long id) throws EntityNotFoundException, IOException {
		boolean estado = false;
		
		avalicaoDao.delete(id);
		List<Avaliacao> avaliacoes = this.locadora.getAvaliacoes();
		avaliacoes.removeIf(avaliacao -> avaliacao.getIdAvaliacao().equals(id));
		this.locadora.setAvaliacoes(avaliacoes);
		if(this.locadora.getAvaliacoes().isEmpty()) estado = true;
		context.getExternalContext().getSessionMap().put("commentState", estado);
		
		
		return "locadora?faces-redirect=true";
	}
	
	public void page() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(this.locadora.getLink());
	}

	public List<Locadora> ordemNota() throws EntityNotFoundException{			
		System.out.println("eu rodei");
		return locadoraDao.orderByRate();
	}
	
	
	
	public List<Locadora> indexLocadora() throws EntityNotFoundException{
		List<Locadora> locadoras = locadoraDao.fourRandom();			
		return locadoras;
	}
	
	public String pageLocadora(Long id) throws EntityNotFoundException, IOException {
		boolean estado = false;
		this.avaliacao = new Avaliacao();
		this.local = "";
		this.locadora = locadoraDao.findById(id);
		
		simpleModel = new DefaultMapModel();
        this.local += Double.toString(locadora.getX());
        this.local += ",";
        this.local += Double.toString(locadora.getY());

        //Shared coordinates
        LatLng coord1 = new LatLng(locadora.getX(), locadora.getY());

        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, locadora.getNome()));
        
        if(this.locadora.getAvaliacoes().isEmpty()) estado = true;
		context.getExternalContext().getSessionMap().put("commentState", estado);
		
		return "locadora?faces-redirect=true";
	}
	

    @PostConstruct
    public void init() {        
        images = new ArrayList<String>();
        for (int i = 1; i <= 3; i++) {
            images.add("img" + i + ".jpg");
        }
    }
    
    public List<String> getImages() {
        return images;
    }

	public String getImagem() {
		return imagem;
	}


	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	
	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}

	public MapModel getSimpleModel() {
        return simpleModel;
    }
	public Locadora getLocadora() {
		return locadora;
	}
	public void setLocadora(Locadora locadora) {
		this.locadora = locadora;
	}
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public AvaliacaoDAO getAvalicaoDao() {
		return avalicaoDao;
	}

	public void setAvalicaoDao(AvaliacaoDAO avalicaoDao) {
		this.avalicaoDao = avalicaoDao;
	}
	









}
