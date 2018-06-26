/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.teste;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import minhavagaweb.model.cdp.Cartao;
import minhavagaweb.model.cdp.ValidaCartao;
import minhavagaweb.model.cgd.CartaoDAOImpl;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author ISM
 */
public class CadastrarCartao {
    
    CartaoDAOImpl cartaoDAO = new CartaoDAOImpl();

    @Given("^cartão não cadastrado$")
    public void cartão_não_cadastrado() throws Throwable {
        Cartao cartao = (Cartao) cartaoDAO.getById(100);
        assertEquals(null, cartao);
    }

    @When("^eu cadastrar dados de cartão válidos$")
    public void eu_cadastrar_dados_de_cartão_válidos() throws Throwable {
        boolean a = ValidaCartao.validCC("5481282497136540");
        assertEquals(true, a);
    }

    @Then("^devo ver a mensagem \"([^\"]*)\"$")
    public void devo_ver_a_mensagem(String arg1) throws Throwable {
        assertEquals("Cartão inserido com sucesso!", arg1);
    }

    @Then("^serei redirecionado para a tela de reservas$")
    public void serei_redirecionado_para_a_tela_de_reservas() throws Throwable {
        System.out.println("Tela de Reservas");
    }

    
    
    @When("^eu cadastrar dados de cartão inválidos$")
    public void eu_cadastrar_dados_de_cartão_inválidos() throws Throwable {
        boolean a = ValidaCartao.validCC("1111222233334444");
        assertEquals(false, a);
    }

    @Then("^deve me aparecer a mensagem \"([^\"]*)\"$")
    public void deve_me_aparecer_a_mensagem(String arg1) throws Throwable {
        assertEquals("Cartão inválido!", arg1);
    }

    @Then("^serei redirecionado para a tela de inserção de dados$")
    public void serei_redirecionado_para_a_tela_de_inserção_de_dados() throws Throwable {
        System.out.println("Tela de Inserção de dados do cartão");
    }
    
    
}
