/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.teste;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import junit.framework.Assert;
import minhavagaweb.model.cdp.Cliente;
import minhavagaweb.model.cdp.Estacionamento;
import minhavagaweb.model.cdp.Pagamento;
import minhavagaweb.model.cdp.TipoVaga;
import minhavagaweb.model.cdp.Vaga;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author ISM
 */
public class RealizarReserva {

    @Given("^eu não tenha pendência de pagamento$")
    public void eu_não_tenha_pendência_de_pagamento() throws Throwable {
        Cliente c = new Cliente();
        Pagamento p = new Pagamento();
        p.setPago(true);
        c.setPagamento(p);
        assertEquals(false, c.estahPendente());
    }

    @When("^eu selecionar o estacionamento listado e o tipo da vaga que desejo$")
    public void eu_selecionar_o_estacionamento_listado_e_o_tipo_da_vaga_que_desejo() throws Throwable {
        System.out.println("Listar");
    }

    @Then("^eu confirmar a solicitação$")
    public void eu_confirmar_a_solicitação() throws Throwable {
        System.out.println("Confirma solicitação");
    }

    @Given("^eu tenha pendência de pagamento$")
    public void eu_tenha_pendência_de_pagamento() throws Throwable {
        Cliente c2 = new Cliente();
        Pagamento p2 = new Pagamento();
        p2.setPago(false);
        c2.setPagamento(p2);
        assertEquals(true, c2.estahPendente());
    }

    @Then("^devo ser redirecionado para a tela inicial$")
    public void devo_ser_redirecionado_para_a_tela_inicial() throws Throwable {
        System.out.println("Redirecionando para HOME");
    }

    @When("^não houver vaga disponível$")
    public void não_houver_vaga_disponível() throws Throwable {
        Vaga vaga = new Vaga();
        vaga.setTipo(TipoVaga.COMUM);
        vaga.setStatus(false);

        Vaga vaga1 = new Vaga();
        vaga1.setTipo(TipoVaga.MOTO);
        vaga1.setStatus(true);

        Estacionamento estacionamento = new Estacionamento();
        estacionamento.adicionarVaga(vaga);
        estacionamento.adicionarVaga(vaga1);

        Vaga vaga2 = estacionamento.getVagaDisponivel(vaga1.getTipo().getValue());
        Assert.assertNull(vaga2);
    }

    @Then("^eu não confirme a solicitação$")
    public void eu_não_confirme_a_solicitação() throws Throwable {
        System.out.println("Não confirma solicitação");
    }

    @Then("^devo receber a mensagem \"([^\"]*)\"$")
    public void devo_receber_a_mensagem(String arg1) throws Throwable {
        assertEquals("Não há vagas disponíveis neste estacionamento", arg1);
    }

    @Then("^devo ter a resposta \"([^\"]*)\"$")
    public void devo_ter_a_resposta(String arg1) throws Throwable {
        assertEquals("Reserva realizada com sucesso!", arg1);
    }

    @Then("^devo ser questionado \"([^\"]*)\"$")
    public void devo_ser_questionado(String arg1) throws Throwable {
        assertEquals("Confimar solicitação?", arg1);
    }

    @Then("^devo ser avisado com a mensagem \"([^\"]*)\"$")
    public void devo_ser_avisado_com_a_mensagem (String arg1) throws Throwable {
        assertEquals("Você possui pendência de pagamento.. Por favor, regularize sua situação na aba Pagamentos.", arg1);
    }

}
