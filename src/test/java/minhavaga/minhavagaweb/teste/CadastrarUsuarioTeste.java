/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavaga.minhavagaweb.teste;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import minhavaga.minhavagaweb.cdp.Pessoa;
import minhavaga.minhavagaweb.cgt.AplCliente;

/**
 *
 * @author 20142bsi0070
 */
public class CadastrarUsuarioTeste {


    /*
     Given usuario informa nome, email, CPF e senha
     When eu cadastrar dados válidos
     Then eu devo ver a mensagem "Registro realizado com sucesso!"
     And eu devo ver uma mensagem de saudação personalizada
    
     Given cliente informa conta, senha e agencia valida
     When cliente solicitar o saldo
     Then o sistema informa o saldo
     */
    private AplCliente aplCliente = new AplCliente();

    @Given("^usuario informa nome, email, CPF e senha$")
    public void usuarioInformaNomeEmailCPFESenha() throws Throwable {

        Pessoa p = new Pessoa();

        p.setNome("Helen Franca Medeiros");
        p.setEmail("helen@gmail.com");
        p.setCpf("98765432198");
        p.setSenha("123456");
        aplCliente.cadastrarCliente(p);
    }

    @Given("^cliente informa conta, senha ou agencia invalida$")
    public void clienteInformaContaSenhaOuAgenciaInvalida() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("^cliente solicitar o saldo$")
    public void clienteSolicitarOSaldo() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^o sistema informa o saldo$")
    public void oSistemaInformaOSaldo() throws Throwable {

    }

    @Then("^o sistema informa um erro$")
    public void oSistemaInformaUmErro() throws Throwable {

    }

}
