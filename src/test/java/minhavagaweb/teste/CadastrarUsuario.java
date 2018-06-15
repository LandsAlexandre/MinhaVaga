/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.teste;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import minhavagaweb.controller.AplCliente;
import minhavagaweb.model.cdp.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author ISM
 */
public class CadastrarUsuario {

    @Given("^eu tenha escolhido me cadastrar$")
    public void eu_tenha_escolhido_me_cadastrar() throws Throwable {
        System.out.println("Tela de cadastro");
    }

    @When("^eu cadastrar dados válidos$")
    public void eu_cadastrar_dados_válidos() throws Throwable {
        boolean a = CPF.isCPFValido("14302380705");
        assertEquals(true, a);

        boolean b = Email.isEmailValido("helenfranca93@gmail.com");
        assertEquals(true, b);

    }

    @Then("^eu devo ver a mensagem \"([^\"]*)\"$")
    public void eu_devo_ver_a_mensagem(String arg1) throws Throwable {
        assertEquals("Registro realizado com sucesso!", arg1);

    }

    @Then("^eu devo ver uma mensagem de saudação personalizada$")
    public void eu_devo_ver_uma_mensagem_de_saudação_personalizada() throws Throwable {
        System.out.println("Bem-vindo, 'Fulano'!");
    }

    @When("^eu cadastre um e-mail que já está cadastrado$")
    public void eu_cadastre_um_e_mail_que_já_está_cadastrado() throws Throwable {
        String email = "JuliaRodrigues6@hotmail.com";
        AplCliente apl = new AplCliente();
        Cliente c = new Cliente();
        c.setCpf("11307925014");
        c.setEmail(email);
        c.setNome("Zé");
        c.setSenha("0000");

        assertEquals("emailRegistrado", apl.cadastrarCliente(c, "20/01/2001", null));

    }

    @Then("^eu devo ser informado que o e-mail já está registrado$")
    public void eu_devo_ser_informado_que_o_e_mail_já_está_registrado() throws Throwable {
        System.out.println("Email já registrado!");
    }

    @Then("^eu devo ver a tela de recuperar minha senha$")
    public void eu_devo_ver_a_tela_de_recuperar_minha_senha() throws Throwable {
        System.out.println("Redirecionando para recuparar senha..");
    }

}
