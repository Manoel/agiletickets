package br.com.caelum.agiletickets.persistencia;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class EntityManagerFactoryCreator implements ComponentFactory<EntityManagerFactory>{

	private EntityManagerFactory factory;
	private final String ambiente;

	public EntityManagerFactoryCreator() {
		this("default");
	}

	public EntityManagerFactoryCreator(String ambiente) {
		this.ambiente = ambiente;
	}

	@PostConstruct
	public void create() {
		factory = Persistence.createEntityManagerFactory(ambiente);
	}

	@Override
	public EntityManagerFactory getInstance() {
		return factory;
	}

	@PreDestroy
	public void destroy() {
		factory.close();
	}

}