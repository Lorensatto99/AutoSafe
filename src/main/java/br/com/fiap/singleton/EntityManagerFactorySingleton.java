package br.com.fiap.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	//1 - Atributo estático que armazena a única instância
	private static EntityManagerFactory fabrica;
	
	//2 - Método estático que cria e retorna a única instância
	public static EntityManagerFactory getInstance() {
		if (fabrica == null) {
			fabrica = Persistence.createEntityManagerFactory("autosafe-persistence-unit");
		}
		return fabrica;
	}
	
	//3 - Construtor privado -> não é possível instanciar o Singleton
	private EntityManagerFactorySingleton() {}
	

}
