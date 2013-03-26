package br.com.caelum.agiletickets.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import br.com.caelum.agiletickets.domain.Relogio;
import br.com.caelum.agiletickets.domain.RelogioDoSistema;

public abstract class AbstractDaoTest {

	private static EntityManagerFactory factory;
	private static Relogio relogio;
	protected static EntityManager manager;

	@BeforeClass
	public static void before() {
		EntityManagerFactoryCreator factoryCreator = new EntityManagerFactoryCreator("teste");
		factoryCreator.create();

		factory = factoryCreator.getInstance();
		manager = factory.createEntityManager();
		relogio = new RelogioDoSistema();
	}

	@Before
	public void setup() {
		manager.getTransaction().begin();
	}

	@After
	public void tearDown() {
		if (manager.isOpen() && manager.getTransaction().isActive()) {
			manager.getTransaction().rollback();
		}
	}

	@AfterClass
	public static void after() {
		if (manager.isOpen()) {
			manager.close();
		}
		factory.close();
	}

	protected JPAEspetaculoDao espetaculos() {
		return new JPAEspetaculoDao(manager, relogio);
	}

}
