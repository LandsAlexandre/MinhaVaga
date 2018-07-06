/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.teste;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import minhavagaweb.controller.GenController;
import minhavagaweb.model.cdp.Cartao;
import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cdp.ValidaCartao;
import minhavagaweb.model.cgd.CartaoDAOImpl;
import minhavagaweb.model.cgt.AplCliente;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

/**
 *
 * @author ISM
 */
public class CadastrarCartao {
    
    CartaoDAOImpl cartaoDAO = new CartaoDAOImpl();
    GenController controller = new GenController();
    
    @Given("^cartão não cadastrado$")
    public void cartão_não_cadastrado() throws Throwable {
        Cartao cartao = (Cartao) cartaoDAO.getById(100);
        assertEquals(null, cartao);
    }

    @When("^eu cadastrar dados de cartão válidos$")
    public void eu_cadastrar_dados_de_cartão_válidos() throws Throwable {
        
        String email = "JuliaRodrigues6@hotmail.com";
        Cliente c = new Cliente();
        c.setCpf("11307925014");
        c.setEmail(email);
        c.setNome("Zé");
        c.setSenha("0000");
        
        Cartao card = new Cartao();
        card.setNumeroCartao("4024007122177636");
        card.setNomeTitular(c.getNome());
        
        Calendar cal = Calendar.getInstance();
        cal.set(2019, 05, 28);
        
        card.setDataValidade(cal);
        card.setCvv("326");
        card.setId(300000000);
      
        assertEquals(GenController.TELASOLICITACAO, controller.cadastrarCartao(card));
    }

    @Then("^devo ver a mensagem \"([^\"]*)\"$")
    public void devo_ver_a_mensagem(String arg1) throws Throwable {
        assertEquals("Cartão inserido com sucesso!", arg1);
    }

    @Then("^serei redirecionado para a tela de reservas$")
    public void serei_redirecionado_para_a_tela_de_reservas() throws Throwable {

        Cartao card = new Cartao();
        card.setNumeroCartao("4024007122177636");
        card.setNomeTitular("Jubileu");
        
        Calendar cal = Calendar.getInstance();
        cal.set(2019, 05, 28);
        
        card.setDataValidade(cal);
        card.setCvv("326");
        card.setId(300000000);
      
        assertEquals(GenController.TELASOLICITACAO, controller.cadastrarCartao(card));
    }

    
    
    @When("^eu cadastrar dados de cartão inválidos$")
    public void eu_cadastrar_dados_de_cartão_inválidos() throws Throwable {
    	Cartao card = new Cartao();
        card.setNumeroCartao("123456789456");
        card.setNomeTitular("Jubileu");
        
        Calendar cal = Calendar.getInstance();
        cal.set(2019, 05, 28);
        
        card.setDataValidade(cal);
        card.setCvv("326");
        card.setId(300000000);
        assertEquals(GenController.CARTAO_INVALIDO, controller.cadastrarCartao(card));
    }

    @Then("^deve me aparecer a mensagem \"([^\"]*)\"$")
    public void deve_me_aparecer_a_mensagem(String arg1) throws Throwable {
        assertEquals("Cartão inválido!", arg1);
    }

    @Then("^serei redirecionado para a tela de inserção de dados$")
    public void serei_redirecionado_para_a_tela_de_inserção_de_dados() throws Throwable {
    	Cartao card = new Cartao();
        card.setNumeroCartao("123456789456");
        card.setNomeTitular("Jubileu");
        
        Calendar cal = Calendar.getInstance();
        cal.set(2019, 05, 28);
        
        card.setDataValidade(cal);
        card.setCvv("326");
        card.setId(300000000);
        assertEquals(GenController.CARTAO_INVALIDO, controller.cadastrarCartao(card));
    }
    
    
}
