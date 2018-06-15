/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.teste;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import minhavagaweb.model.cdp.ValidaCartao;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author ISM
 */
public class CadatrarCartao {

    @Given("^eu tenha escolhido inserir um novo cartão$")
    public void eu_tenha_escolhido_inserir_um_novo_cartão() throws Throwable {
        System.out.println("Quero cadastrar cartão");
    }

    @Then("^devo ver a mensagem \"([^\"]*)\"$")
    public void devo_ver_a_mensagem(String arg1) throws Throwable {
        assertEquals("Cartão inserido com sucesso!", arg1);
    }

    @Then("^serei redirecionado para a tela de reservas$")
    public void serei_redirecionado_para_a_tela_de_reservas() throws Throwable {
        System.out.println("Tela de Reservas");
    }

    @When("^eu cadastrar dados inválidos$")
    public void eu_cadastrar_dados_inválidos() throws Throwable {
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
