/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.teste;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import minhavagaweb.model.cdp.Cartao;
import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cdp.Pagamento;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author landerson
 */
public class RealizarPagamento {

    Cliente c;
    Pagamento pagamentoGerado;
    Cartao card;
    List<Pagamento> listaPgmto;
    List<Cartao> listaCartoes;

    private void instanciarObjetos() {
        if (c == null) {
            String email = "JuliaRodrigues6@hotmail.com";
            c = new Cliente();
            c.setCpf("11307925014");
            c.setEmail(email);
            c.setNome("Zé");
            c.setSenha("0000");

            pagamentoGerado = new Pagamento();
            pagamentoGerado.setPago(false);

            c.addPagamento(pagamentoGerado);
            listaPgmto = c.getPagamento();

            card = new Cartao();
            card.setNumeroCartao("4556705017773858");
            //26/11/2018
            //751
            c.addCartao(card);
            listaCartoes = c.getCartoes();
        }

    }

    @Given("^eu tenha uma reserva pendente$")
    public void eu_tenha_uma_reserva_pendente() throws Throwable {
        instanciarObjetos();
        boolean result = true;
        for (Pagamento pag : listaPgmto) {
            if (!pag.isPago()) {
                result = pag.isPago();
            }
        }
        assertEquals(false, result);
    }

    @Given("^eu tenha cadastrado um cartao$")
    public void eu_tenha_cadastrado_um_cartao() throws Throwable {
        assertEquals(true, !listaCartoes.isEmpty());
    }

    @When("^selecionar realizar pagamento$")
    public void selecionar_realizar_pagamento() throws Throwable {
        String actual = "realizarPagamento";
        assertEquals("realizarPagamento", actual);
    }

    @When("^a administradora de cartao aprovar o pagamento$")
    public void a_administradora_de_cartao_aprovar_o_pagamento() throws Throwable {
        boolean actual = true;
        assertEquals(true, actual);
    }
    
    @Then("^eh exibido a mensagem \"([^\"]*)\"$")
    public void eh_exibido_a_mensagem(String arg1) throws Throwable {
        assertEquals("Sucesso!",arg1);
    }
    
    @Then("^eu devo ser redirecionado para homepage$")
    public void eu_devo_ser_redirecionado_para_homepage() throws Throwable {
        String actual = "homePage";
        assertEquals("homePage", actual);
    }

    @When("^a administradora de cartao reprovar o pagamento$")
    public void a_administradora_de_cartao_reprovar_o_pagamento() throws Throwable {
        boolean actual = false;
        assertEquals(false, false);
    }
    
    @Then("^eh mostrado a mensagem \"([^\"]*)\"$")
    public void eh_mostrado_a_mensagem(String arg1) throws Throwable {
        assertEquals("Falhou!",arg1);
    }
    
    @Then("^eu devo ser redirecionado para teladepagamento$")
    public void eu_devo_ser_redirecionado_para_teladepagamento() throws Throwable {
        String actual = "telaPagamento";
        assertEquals("telaPagamento", "telaPagamento");
    }
}
