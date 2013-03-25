package br.com.caelum.agiletickets.persistencia;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class EntityManagerCreator implements ComponentFactory<EntityManager>{

	private final EntityManagerFactory factory;
	private EntityManager entityManager;

	public EntityManagerCreator(EntityManagerFactory factory) {
		this.factory = factory;
	}

	@PostConstruct
	public void create() {
		entityManager = factory.createEntityManager();
	}

	@Override
	public EntityManager getInstance() {
		return entityManager;
	}

	@PreDestroy
	public void destroy() {
		entityManager.close();
	}

}
