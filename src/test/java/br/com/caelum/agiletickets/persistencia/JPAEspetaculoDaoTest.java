package br.com.caelum.agiletickets.persistencia;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.caelum.agiletickets.models.Sessao;

public class JPAEspetaculoDaoTest extends AbstractDaoTest {

	@Test
	public void nao_deve_trazer_sessoes_do_passado() {
		Sessao sessaoAntiga = new Sessao();
		sessaoAntiga.setInicio(new DateTime().minusDays(1));

		Sessao sessaoFutura = new Sessao();
		sessaoFutura.setInicio(new DateTime().plusDays(1));

		espetaculos().agende(Arrays.asList(sessaoAntiga, sessaoFutura));


		assertThat(espetaculos().proximasSessoes(10), not(hasItem(sessaoAntiga)));
		assertThat(espetaculos().proximasSessoes(10), hasItem(sessaoFutura));
	}

}
