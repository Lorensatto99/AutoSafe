package br.com.fiap.singleton;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	//1 - Atributo estático que armazena a única instância
	
	private static EntityManagerFactory fabrica;
	
	//2 - Método estático que cria e retorna a única instância
	public static EntityManagerFactory getInstance() {
		if (fabrica == null) {
			Map<String, String> env = System.getenv();
			Map<String, Object> configOverrides = new HashMap<String, Object>();
			for(String keyString : env.keySet()) {
				if(keyString.contains("DB_URL")) configOverrides.put("javax.persistence.jdbc.url", env.get("DB_URL"));
				if(keyString.contains("DB_USER")) configOverrides.put("javax.persistence.jdbc.user", env.get("DB_USER"));
				if(keyString.contains("DB_PASS")) configOverrides.put("javax.persistence.jdbc.password", env.get("DB_PASS"));
			}			
			fabrica = Persistence.createEntityManagerFactory("autosafe-persistence-unit", configOverrides);
		}
		return fabrica;
	}
	
	//3 - Construtor privado -> não é possível instanciar o Singleton
	private EntityManagerFactorySingleton() {}
	

}
