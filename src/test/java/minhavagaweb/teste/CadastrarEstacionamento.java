package minhavagaweb.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalTime;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import minhavagaweb.model.cdp.Estacionamento;
import minhavagaweb.model.cdp.Localizacao;
import minhavagaweb.model.cgd.EstacionamentoDAOImpl;
import minhavagaweb.model.cgd.LocalizacaoDAOImpl;

public class CadastrarEstacionamento {
	EstacionamentoDAOImpl<Estacionamento> parkDAO = new EstacionamentoDAOImpl<>();
	LocalizacaoDAOImpl<Localizacao> localDAO = new LocalizacaoDAOImpl<>();
	Estacionamento park;
	@Given("^estacionamento ainda não cadastrado$")
	public void estacionamento_ainda_não_cadastrado() throws Throwable {
	    Estacionamento park = parkDAO.getById(1000000);
	    assertEquals(null,park);
	}

	@When("^eu cadastrar dados validos$")
	public void eu_cadastrar_dados_validos() throws Throwable {
		Estacionamento park = new Estacionamento();
		park.setCapacidade(100);
		park.setHorarioAbertura(LocalTime.now());
		park.setHorarioFechamento(LocalTime.now().plusHours(1));
		park.setNome("Shopping dos Telhados");
		park.setValorPorHora(5);
		
		Localizacao local = new Localizacao();
		local.setLatitude(12);
		local.setLongitude(12);
		localDAO.insert(local);
		local.setId(localDAO.getAll().get(0).getId());
		park.setLocal(local);
		
		assertEquals(false,parkDAO.insert(park));
	}

	@Then("^eu verei a mensagem \"([^\"]*)\"$")
	public void eu_verei_a_mensagem(String arg1) throws Throwable {
	    assertEquals("Estacionamento cadastrado!", arg1);
	}
	
	@Given("^um estacionamento cadastrado$")
	public void um_estacionamento_cadastrado() throws Throwable {
	    park = parkDAO.getById(parkDAO.getNextId("select * from estacionamento where nome ='Shopping dos Telhados'", "id_estacionamento")-1);
	    assertEquals("Shopping dos Telhados", park.getNome());
	}

	@When("^eu atualizar seus dados corretamente$")
	public void eu_atualizar_seus_dados_corretamente() throws Throwable {
	    park.setNome("Shopping do automovel");
	    parkDAO.update(park);
	    assertEquals("Shopping do automovel", parkDAO.getById(parkDAO.getNextId("select * from estacionamento where nome ='Shopping do automovel'", "id_estacionamento")-1).getNome());
	}

	@Then("^eu contemplarei a mensagem \"([^\"]*)\"$")
	public void eu_contemplarei_a_mensagem(String arg1) throws Throwable {
	    assertEquals("Estacionamento atualizado!",arg1);
	}

	@Given("^um ja estacionamento cadastrado$")
	public void um_ja_estacionamento_cadastrado() throws Throwable {
		park = parkDAO.getById(parkDAO.getNextId("select * from estacionamento where nome ='Shopping do automovel'", "id_estacionamento")-1);
		assertEquals("Shopping do automovel", park.getNome());
	}

	@When("^eu deletar este estacionamento$")
	public void eu_deletar_este_estacionamento() throws Throwable {
	    parkDAO.delete(park);
	    assertNull(parkDAO.getById(parkDAO.getNextId("select * from estacionamento where nome ='Shopping do automovel'", "id_estacionamento")-1));
	}

	@Then("^eu visualizarei a mensagem \"([^\"]*)\"$")
	public void eu_visualizarei_a_mensagem(String arg1) throws Throwable {
		assertEquals("Estacionamento deletado!",arg1);
	}
}
